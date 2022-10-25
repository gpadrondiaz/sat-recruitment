package sat.recruitment.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.dto.UserDTO;
import sat.recruitment.api.model.User;
import sat.recruitment.api.service.UserService;
import sat.recruitment.api.utils.UserMoneyUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMoneyUtils userMoneyUtils;

    @Override
    public List<UserDTO> findAll() {
        List<User> users = new ArrayList<>();
        InputStream fstream;
        try {
            fstream = getClass().getResourceAsStream("/users.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                String[] line = strLine.split(",");
                User user = new User();
                user.setName(line[0]);
                user.setEmail(line[1]);
                user.setPhone(line[2]);
                user.setAddress(line[3]);
                user.setUserType(line[4]);
                user.setMoney(Double.valueOf(line[5]));
                users.add(user);

            }
            fstream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users.stream().map(user -> new UserDTO(user.getName(),user.getEmail(), user.getAddress(),user.getPhone(), user.getUserType(),user.getMoney()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO newUserDTO) {
        List<UserDTO> userDTOS = findAll();
        Boolean isDuplicated = userDTOS.stream().anyMatch(userDTO -> userDTO.getEmail().equalsIgnoreCase(newUserDTO.getEmail()) ||
                userDTO.getPhone().equalsIgnoreCase(newUserDTO.getPhone()) ||
                (userDTO.getName().equals(newUserDTO.getName()) && userDTO.getAddress().equals(newUserDTO.getAddress())));

        if (isDuplicated) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
        }
        if(newUserDTO.getUserType() != null && newUserDTO.getMoney() != null) {
            newUserDTO.setMoney(userMoneyUtils.getUserMoneyByType(newUserDTO.getUserType(), newUserDTO.getMoney()));
        }

        return newUserDTO;
    }
}
