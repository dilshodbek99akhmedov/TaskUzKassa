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
import org.springframework.web.context.WebApplicationContext;
import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.helper.DataHelper;
import uz.uzkassa.helper.IntegrationTest;
import uz.uzkassa.repository.CompanyRepository;
import uz.uzkassa.repository.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author Dilshodbek Akhmedov, Thu 10:20 AM. 2/26/23
 */
@IntegrationTest
class IntegrationTestCompanyAPI {
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
        userRepository.saveAll(DataHelper.getUsersToIntialize());
        companyRepository.saveAll(DataHelper.getCompaniesToIntialize());
    }

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("SUCCESS: company is created !")
    void it_should_create_company() throws Exception {
        CreateCompanyDto companyDto = new CreateCompanyDto("Company 3", "Tashkent 3", "4863486");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/company")
                        .content(mapper.writeValueAsString(companyDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Company 3"))
                .andExpect(jsonPath("$.data.address").value("Tashkent 3"))
                .andExpect(jsonPath("$.data.zip_code").value("4863486"));
    }


    @Test
    @DisplayName("SUCCESS: company is find!")
    void it_should_company_is_find() throws Exception {
        long id = 2L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(id));
    }

    @Test
    @DisplayName("FAILED: company not found!")
    void it_should_company_not_found() throws Exception {
        long id = 3L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("Company not found"));
    }

    @Test
    @DisplayName("SUCCESS: company blocked!")
    void it_should_company_success_blocked() throws Exception {
        long id = 2L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/block/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Company blocked id = " + id));
    }

    @Test
    @DisplayName("FAILED: company not blocked!")
    void it_should_company_not_blocked() throws Exception {
        long id = 3L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/block/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("Company not found"));
    }

    @Test
    @DisplayName("SUCCESS: company update !")
    void it_should_success_company_update() throws Exception {
        UpdateCompanyDto updateCompanyDto = new UpdateCompanyDto(1L, "GoodCompany", "Samarkand", "951357");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/company")
                        .content(mapper.writeValueAsString(updateCompanyDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("GoodCompany"))
                .andExpect(jsonPath("$.data.address").value("Samarkand"))
                .andExpect(jsonPath("$.data.zip_code").value("951357"));
    }

    @Test
    @DisplayName("FAILED: company not update !")
    void it_should_return_company_not_update() throws Exception {
        UpdateCompanyDto updateCompanyDto = new UpdateCompanyDto(10L, "GoodCompany", "Samarkand", "951357");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/company")
                        .content(mapper.writeValueAsString(updateCompanyDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("Company not found"));
    }

}

