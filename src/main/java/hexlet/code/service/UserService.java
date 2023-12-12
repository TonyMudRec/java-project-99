package hexlet.code.service;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.UserMapper;
import hexlet.code.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));

        LOG.debug("user with id "+ user.getId() +" was found");

        return userMapper.map(user);
    }

    public List<UserDTO> getAll() {
        var users = userRepository.findAll();

        LOG.info(users.size() + " users found");

        return users.stream()
                .map(userMapper::map)
                .toList();
    }

    public UserDTO create(UserCreateDTO data) {
        var user = userMapper.map(data);
        userRepository.save(user);

        LOG.info("user " + user.getFirstName() + " was saved");

        return userMapper.map(user);
    }

    public UserDTO update(UserUpdateDTO dto, Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        userMapper.update(dto, user);
        userRepository.save(user);

        LOG.info("user " + user.getFirstName() + " was updated");

        return userMapper.map(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);

        LOG.info("user with id " + id + " was deleted");
    }
}
