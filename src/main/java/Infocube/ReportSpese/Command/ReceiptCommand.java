package Infocube.ReportSpese.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptCommand
{

    private Integer code;

    @NotEmpty(message = "La categoria e' obbligatoria")
    @NotNull(message = "La categoria e' obbligatoria")
    private String category;

    @NotEmpty(message = "Il tipo di pagamento e' obbligatoria")
    @NotNull(message = "Il tipo di pagamento e' obbligatorio")
    private String paymentType;

    @NotEmpty(message = "Il venditore e' obbligatoria")
    @NotNull(message = "Il venditore e' obbligatorio")
    private String seller;

    private String product;

    @NotNull(message = "La data e' obbligatorio")
    private Date date;

    @NotNull(message = "Il totale e' obbligatorio")
    private Float total;

    @NotNull(message = "L'user Id e' obbligatorio")
    private Integer userId;
}
