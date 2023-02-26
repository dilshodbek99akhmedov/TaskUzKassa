package uz.uzkassa.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uz.uzkassa.helper.IntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Dilshodbek Akhmedov, Thu 10:25 AM. 2/26/23
 */

@IntegrationTest
class IntegrationTestCurrencyAPI {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    @DisplayName("SUCCESS: currency found!")
    void it_should_found_currency() throws Exception {
        String code = "USD";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/currency/" + code)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.code").value(code));
    }

    @Test
    @DisplayName("FAILED: currency not found!")
    void it_should_not_found_currency() throws Exception {
        String code = "UKD";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/currency/" + code)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.status").value(400))
                .andExpect(jsonPath("$.error.message").value("Currency not found"));
    }

}