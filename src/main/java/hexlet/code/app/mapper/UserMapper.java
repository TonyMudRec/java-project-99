package hexlet.code.app.mapper;

import hexlet.code.app.dto.UserCreateDTO;
import hexlet.code.app.dto.UserDTO;
import hexlet.code.app.dto.UserUpdateDTO;
import hexlet.code.app.model.User;
import hexlet.code.app.util.PasswordHasher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeforeMapping;

import java.util.Arrays;

/**
 * mapper needs to convert forms of user representations.
 */
@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    /**
     * convert create dto representation to user object.
     *
     * @param dto
     * @return user
     */
    public abstract User map(UserCreateDTO dto);

    /**
     * convert user to user dto representation.
     *
     * @param model
     * @return userDTO
     */
    public abstract UserDTO map(User model);

    /**
     * needs to partial update object
     *
     * @param dto
     * @param model
     */
    public abstract void update(UserUpdateDTO dto, @MappingTarget User model);

    @BeforeMapping
    public void encryptPassword(UserCreateDTO data) {
        var password = data.getPassword();
        data.setPassword(Arrays.toString(PasswordHasher.getHash(password)));
    }
}
