package sat.recruitment.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private String email;
    private String address;
    private String phone;
    private String userType;
    private Double money;
}
