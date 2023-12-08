package hexlet.code.dto;

import jakarta.validation.constraints.NotBlank;
import org.openapitools.jackson.nullable.JsonNullable;

public class UserUpdateDTO {

    @NotBlank
    private JsonNullable<String> email;
    @NotBlank
    private JsonNullable<String> firstName;
    @NotBlank
    private JsonNullable<String> lastName;
    @NotBlank
    private JsonNullable<String> decodedPassword;
}
