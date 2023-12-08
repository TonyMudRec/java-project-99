package hexlet.code.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {

    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String decodedPassword;
}
