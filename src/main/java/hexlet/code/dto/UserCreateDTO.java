package hexlet.code.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateDTO {

    @NotBlank
    private String email;

    private String firstName;

    private String lastName;

    @NotBlank
    private String password;

    public UserCreateDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
