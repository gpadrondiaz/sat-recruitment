package sat.recruitment.api.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	@NotEmpty(message = "The name is required")
	private String name;

	@NotEmpty(message = "The email is required")
	@Email(message = "The email is invalid")
	private String email;

	@NotEmpty(message = "The address is required")
	private String address;

	@NotEmpty(message = "The phone is required")
	private String phone;

	private String userType;
	private Double money;
}
