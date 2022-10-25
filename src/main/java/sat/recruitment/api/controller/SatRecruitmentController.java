package sat.recruitment.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sat.recruitment.api.dto.UserDTO;
import sat.recruitment.api.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/user")
@Validated
public class SatRecruitmentController {

	@Autowired
	public UserService userService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
		UserDTO result = userService.save(userDTO);
		return new ResponseEntity<UserDTO>(result, HttpStatus.CREATED);
	}
}
