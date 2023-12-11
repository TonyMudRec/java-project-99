package hexlet.code.mapper;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.model.User;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants;


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
     * @param dto
     * @return user
     */
    public abstract User map(UserCreateDTO dto);

    /**
     * convert user to user dto representation.
     * @param model
     * @return userDTO
     */
    public abstract UserDTO map(User model);

    /**
     * needs to partial update object
     * @param dto
     * @param model
     */
    public abstract void update(UserUpdateDTO dto, @MappingTarget User model);
}
