package platform.messagingservice.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sat.recruitment.api.SatRecruitmentApplication;
import sat.recruitment.api.dto.UserDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SatRecruitmentApplication.class)
@AutoConfigureMockMvc
class SatRecruitmentControllerTests {

    private static final String PATH = "/api/v1/user";
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void createUserTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = UserDTO.builder().name("Gregory").address("Caracas")
                .email("greg@gmail.com").money(100d).phone("dd").build();

        this.mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUserInvalidTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = UserDTO.builder().address("Caracas")
                .email("greg@gmail.com").money(100d).phone("dd").build();

        this.mockMvc.perform(post(PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest());
    }
}
