package sat.recruitment.api.dto;

import lombok.*;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotNull(message = "The name is required")
	private String name;
	@NotNull(message = "The email is required")
	private String email;
	@NotNull(message = "The address is required")
	private String address;
	@NotNull(message = "The phone is required")
	private String phone;

	private String userType;
	private Double money;
}
