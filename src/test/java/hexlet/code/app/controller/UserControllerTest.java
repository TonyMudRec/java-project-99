package hexlet.code.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.app.dto.UserCreateDTO;
import hexlet.code.app.dto.UserUpdateDTO;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.util.PasswordHasher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    private static User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setFirstName("hexlet");
        testUser.setLastName("example");
        testUser.setEmail("hexlet@example.com");
        testUser.setPassword(Arrays.toString(PasswordHasher.getHash("qwerty")));

        LOG.debug("test user with first name " + testUser.getFirstName() + " created");
    }

    @Test
    void indexTest() throws Exception {
        userRepository.save(testUser);

        var requestGet = get("/api/users");
        var result = mockMvc.perform(requestGet)
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThat(body).asList().hasSize(1);
    }

    @Test
    void showTest() throws Exception {
        userRepository.save(testUser);

        var request = get("/api/users/{id}", testUser.getId());
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThat(body).contains(testUser.getEmail());
    }

    @Test
    void createTest() throws Exception {
        userRepository.save(testUser);

        var email = "example_user2@gmail.com";
        var dto = new UserCreateDTO(email,
                "name2_test",
                "last_name2_test",
                testUser.getPassword());
        var request = post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto));
        mockMvc.perform(request)
                .andExpect(status().isCreated());
        var user = userRepository.findByEmail(email).get();

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(2L);
    }

    @Test
    void createWithNotValidEmailTest() throws Exception {
        var dto = new UserCreateDTO("email",
                "name2_test",
                "last_name2_test",
                testUser.getPassword());
        var request = post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateTest() throws Exception {
        userRepository.save(testUser);

        var dto = new UserUpdateDTO();
        var newName = "updated_name2_test";
        dto.setEmail(JsonNullable.of(testUser.getEmail()));
        dto.setFirstName(JsonNullable.of(newName));
        dto.setLastName(JsonNullable.of(null));
        var request = put("/api/users/{id}", testUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto));
        mockMvc.perform(request)
                .andExpect(status().isOk());

        var foundUser = userRepository.findByEmail(testUser.getEmail()).get();

        assertThat(foundUser.getFirstName()).isEqualTo(newName);
        assertThat(foundUser.getLastName()).isNull();
        assertThat(foundUser.getPassword()).isNotNull();
    }

    @Test
    void destroyTest() throws Exception {
        userRepository.save(testUser);

        var request = delete("/api/users/{id}", testUser.getId());
        mockMvc.perform(request)
                .andExpect(status().isOk());

        assertThat(userRepository.existsById(testUser.getId())).isFalse();
    }
}
