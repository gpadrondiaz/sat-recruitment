package platform.messagingservice.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.dto.UserDTO;
import sat.recruitment.api.service.impl.UserServiceImpl;
import sat.recruitment.api.utils.UserMoneyUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMoneyUtils userMoneyUtils;

    @BeforeEach
    public void before(){
        Mockito.when(userMoneyUtils.getUserMoneyByType(Mockito.anyString(), Mockito.anyDouble())).thenCallRealMethod();
    }

    @Test
    public void testFindAll() {
        List<UserDTO> userDTOS = userService.findAll();
        assertFalse(userDTOS.isEmpty());
    }

    @Test
    void testFailDuplicatedUser() {
        UserDTO userDTO = UserDTO.builder().userType("Type1").name("Gregory").address("Caracas")
                        .email("Agustina@gmail.com").money(100d).phone("dd").build();
        Assertions.assertThrows(ResponseStatusException.class, () -> userService.save(userDTO));
    }

    @Test
    void testSuccessUserCreation() {
        UserDTO userDTO = UserDTO.builder().userType("Type1").name("Gregory").address("Caracas")
                .email("greg@gmail.com").money(100d).phone("dd").build();
        UserDTO savedUserDTO = userService.save(userDTO);

        assertEquals(userDTO.getEmail(), savedUserDTO.getEmail());
    }

    @Test
    void tesCreateNormalUserType() {
        UserDTO userDTO = UserDTO.builder().userType("Normal").name("Gregory").address("Caracas")
                .email("greg@gmail.com").money(120d).phone("dd").build();
        UserDTO savedUserDTO = userService.save(userDTO);
        assertEquals(134.4d, savedUserDTO.getMoney());
    }

    @Test
    void tesCreateSuperUserType() {
        UserDTO userDTO = UserDTO.builder().userType("SuperUser").name("Gregory").address("Caracas")
                .email("greg@gmail.com").money(120d).phone("dd").build();
        UserDTO savedUserDTO = userService.save(userDTO);
        assertEquals(144d, savedUserDTO.getMoney());
    }

    @Test
    void tesCreatePremiumUserType() {
        UserDTO userDTO = UserDTO.builder().userType("Premium").name("Gregory").address("Caracas")
                .email("greg@gmail.com").money(120d).phone("dd").build();
        UserDTO savedUserDTO = userService.save(userDTO);
        assertEquals(360d, savedUserDTO.getMoney());
    }

}
