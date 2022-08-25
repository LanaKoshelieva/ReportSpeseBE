package Infocube.ReportSpese.DTO;

import Infocube.ReportSpese.Model.Receipt;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiptDTO
{
    private Integer code;
    private String category;
    private String paymentType;
    private String seller;
    private String product;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private Float total;
    private Integer userId;

    public ReceiptDTO(Receipt r)
    {
        code = r.getCode();
        category = r.getCategory();
        product = r.getProduct();
        paymentType = r.getPaymentType();
        seller = r.getSeller();
        total = r.getTotal();
        date = r.getDate();
        userId = r.getUser().getId();
    }

}
