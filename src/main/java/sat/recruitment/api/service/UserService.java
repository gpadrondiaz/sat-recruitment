package sat.recruitment.api.service;

import sat.recruitment.api.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO save(UserDTO userDTO);
    List<UserDTO> findAll();
}
