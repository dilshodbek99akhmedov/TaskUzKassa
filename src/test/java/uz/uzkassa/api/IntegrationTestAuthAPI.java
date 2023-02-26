package uz.uzkassa.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.helper.DataHelper;
import uz.uzkassa.helper.IntegrationTest;
import uz.uzkassa.repository.EmailSettingsRepository;
import uz.uzkassa.repository.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Dilshodbek Akhmedov, Thu 10:30 AM. 2/26/23
 */

@IntegrationTest
class IntegrationTestAuthAPI {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSettingsRepository emailSettingsRepository;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final ObjectMapper mapper;

    private String token;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
        DataHelper.getUsersToIntialize().forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        userRepository.saveAll(DataHelper.getUsersToIntialize());
        emailSettingsRepository.save(DataHelper.getEmailSettingsToIntialize());
        token = DataHelper.getEmailSettingsToIntialize().getToken();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        emailSettingsRepository.deleteAll();
    }

    @Test
    @DisplayName("SUCCESS: user is sing up !")
    void it_should_success_sing_up() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("test", "test1", "dilshooodbek99akhmedov76@gmail.com", null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/sign-up")
                        .content(mapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("A confirmation email has been sent to your email"));
    }

    @Test
    @DisplayName("FAILED: user is already exists !")
    void it_should_return_already_exists() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("user 1", "test1", "dilshooodbek99akhmedov76@gmail.com", null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/sign-up")
                        .content(mapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("User already exists"));
    }

    @Test
    @DisplayName("SUCCESS: user is successfully activated !")
    void it_should_successfully_activated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/confirmation-email/" + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Status activated"));
    }

    @Test
    @DisplayName("FAILED: user is failed to activate successfully !")
    void it_should_failed_to_activate_successfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/confirmation-email/" + "token")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("Invalid token"));
    }

//    @Test
//    @DisplayName("SUCCESS: user is sing in !")
//    void it_should_success_sing_in() throws Exception {
//        Map<String, String> loginDto = new HashMap<>();
//        loginDto.put("username", "admin");
//        loginDto.put("password", "123");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/sign-in")
//                        .content(mapper.writeValueAsString(loginDto))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.success").value(true));
//    }
//
//    @Test
//    @DisplayName("FAILED: user is sing in !")
//    void it_should_failed_sing_in() throws Exception {
//        Map<String, String> loginDto = new HashMap<>();
//        loginDto.put("username", "user 1");
//        loginDto.put("password", "123123");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/sign-in")
//                        .content(mapper.writeValueAsString(loginDto))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.error.status").value(403))
//                .andExpect(jsonPath("$.error.code").value("Forbidden"));
//    }

}