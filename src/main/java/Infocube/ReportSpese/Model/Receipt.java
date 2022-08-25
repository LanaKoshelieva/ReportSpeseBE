package Infocube.ReportSpese.Model;

import Infocube.ReportSpese.Command.ReceiptCommand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Receipt
{

    public Receipt(ReceiptCommand command)
    {
        setCode(command.getCode());
        setCategory(command.getCategory());
        setDate(command.getDate());
        setTotal(command.getTotal());
        setPaymentType(command.getPaymentType());
        setSeller(command.getSeller());
        setProduct(command.getProduct());
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer code;

    @Column(nullable = false)
    private Float total;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, length = 100)
    private String seller;

    @Column()
    private String product;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, length = 20)
    private String paymentType;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;

}
