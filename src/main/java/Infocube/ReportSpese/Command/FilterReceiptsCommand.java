package Infocube.ReportSpese.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FilterReceiptsCommand
{
    private Integer userId;
    private Date dateFrom;
    private Date dateTo;
    private Double priceMin;
    private Double priceMax;
    private String category;
    private String seller;
    private String paymentType;
    private String product;
}
