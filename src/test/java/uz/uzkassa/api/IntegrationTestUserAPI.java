package uz.uzkassa.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.dto.user.UpdateUserDto;
import uz.uzkassa.entity.Company;
import uz.uzkassa.entity.User;
import uz.uzkassa.helper.DataHelper;
import uz.uzkassa.helper.IntegrationTest;
import uz.uzkassa.repository.CompanyRepository;
import uz.uzkassa.repository.UserRepository;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Dilshodbek Akhmedov, Thu 10:27 AM. 2/26/23
 */

@IntegrationTest
class IntegrationTestUserAPI {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final ObjectMapper mapper;


    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        DataHelper.setSecurityContextHolder();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
        DataHelper.getUsersToIntialize().forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        companyRepository.saveAll(DataHelper.getCompaniesToIntialize());
        userRepository.saveAll(DataHelper.getUsersToIntialize());

        Optional<Company> companyOptional = companyRepository.findById(1L);
        User user = DataHelper.getTestUser();
        user.setCompany(companyOptional.get());
        user.setId(3L);

        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("SUCCESS: user is created !")
    void it_should_create_company_user() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("lazy", "123123", "lazyuser@gmail.com", 2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .content(mapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username").value("lazy"))
                .andExpect(jsonPath("$.data.email").value("lazyuser@gmail.com"));
    }

    @Test
    @DisplayName("FAILED: user no created !")
    void it_should_no_create_company_user() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("user 1", "123123", "lazyuser@gmail.com", 1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .content(mapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("User already exists"));

        CreateUserDto createUserDto2 = new CreateUserDto("lazy", "123123", "lazyuser@gmail.com", 10L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                        .content(mapper.writeValueAsString(createUserDto2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("Company not found"));
    }

    @Test
    @DisplayName("SUCCESS: user found!")
    void it_should_user_found() throws Exception {
        long id = 3L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username").value("u"))
                .andExpect(jsonPath("$.data.email").value("test@gmail.com"));
    }

    @Test
    @DisplayName("FAILED: user not found!")
    void it_should_user_not_found() throws Exception {
        long id = 6L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("User not found"));
    }

    @Test
    @DisplayName("FAILED: company users is found !")
    void it_should_company_users_is_found() throws Exception {
        long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/company-employees/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true));
    }


    @Test
    @DisplayName("SUCCESS: user updated!")
    void it_should_user_success_updated() throws Exception {
        UpdateUserDto updateUserDto = new UpdateUserDto(3L, "us", "123", "usTest@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user")
                        .content(mapper.writeValueAsString(updateUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.username").value("us"))
                .andExpect(jsonPath("$.data.email").value("usTest@gmail.com"));

    }

    @Test
    @DisplayName("SUCCESS: user blocked!")
    void it_should_user_success_blocked() throws Exception {
        long id = 3L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/block/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("User blocked id = " + id));
    }

    @Test
    @DisplayName("FAILED: user not blocked!")
    void it_should_user_not_blocked() throws Exception {
        long id = 10L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/block/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("User not found"));
    }

}
