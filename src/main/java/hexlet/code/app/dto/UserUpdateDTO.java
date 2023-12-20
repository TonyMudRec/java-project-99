package hexlet.code.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class UserUpdateDTO {

    @NotBlank
    private JsonNullable<String> email;
    @NotBlank
    private JsonNullable<String> firstName;
    @NotBlank
    private JsonNullable<String> lastName;
    @NotBlank
    private JsonNullable<String> password;
}