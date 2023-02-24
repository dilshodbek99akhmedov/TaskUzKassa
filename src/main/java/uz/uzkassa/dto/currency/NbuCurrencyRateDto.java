package uz.uzkassa.dto.currency;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dilshodbek Akhmedov, Thu 11:16 PM. 2/23/23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NbuCurrencyRateDto {
    private String title;
    private String code;
    @JsonProperty("cb_price")
    private String cbuPrice;
    @JsonProperty("nbu_buy_price")
    private String nbuBuyPrice;
    @JsonProperty("nbu_cell_price")
    private String nbuCellPrice;
    private String date;
}

