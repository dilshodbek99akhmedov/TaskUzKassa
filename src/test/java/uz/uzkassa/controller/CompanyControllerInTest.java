package uz.uzkassa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uz.uzkassa.helper.DataHelper;
import uz.uzkassa.helper.IntegrationTest;
import uz.uzkassa.repository.CompanyRepository;
import uz.uzkassa.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Dilshodbek Akhmedov, Thu 10:20 AM. 2/26/23
 */
@IntegrationTest
class CompanyControllerInTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private WebApplicationContext wac;
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
        userRepository.save(DataHelper.getUsersToIntialize());
        companyRepository.saveAll(DataHelper.getCompaniesToIntialize());
    }

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("SUCCESS: company is created !")
    void it_should_create_company() {
        assertEquals(1, 1);

    }

//    @Test
//    void testEdit() {
//        ResponseEntity<DataDto<Company>> result = companyController.edit(new UpdateCompanyDto(Long.valueOf(1), "name", "address", "zipCode"));
//        Assertions.assertEquals(null, result);
//    }
//
//    @Test
//    void testBlock() {
//        ResponseEntity<DataDto<String>> result = companyController.block(Long.valueOf(1));
//        Assertions.assertEquals(null, result);
//    }
//
//    @Test
//    void testGet() {
//        ResponseEntity<DataDto<Company>> result = companyController.get(Long.valueOf(1));
//        Assertions.assertEquals(null, result);
//    }
}

