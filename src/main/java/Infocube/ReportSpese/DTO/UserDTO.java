package Infocube.ReportSpese.DTO;

import Infocube.ReportSpese.Model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO
{
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private List<ReceiptDTO> receipts;
    private String role;

    public UserDTO(User u)
    {
        id = u.getId();
        name = u.getName();
        surname = u.getSurname();
        email = u.getEmail();
        role = u.getRole();
        sex = u.getSex();
        birthDate = u.getBirthDate();
        receipts = new ArrayList<>();

        if(u.getReceipts() != null)
        {
            for (int i = 0; i < u.getReceipts().size(); i++)
            {
                receipts.add(new ReceiptDTO(u.getReceipts().get(i)));
            }
        }

    }
}
