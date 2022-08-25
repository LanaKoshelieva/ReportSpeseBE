package Infocube.ReportSpese.DTO;

import Infocube.ReportSpese.Model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO
{
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String passwordHash;

    public AuthDTO(User u)
    {
        id = u.getId();
        name = u.getName();
        surname = u.getSurname();
        email = u.getEmail();
        passwordHash = u.getPassword();
    }
}
