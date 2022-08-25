package Infocube.ReportSpese.Model;

import Infocube.ReportSpese.Command.UserCommand;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.UserDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@UserDefinition
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE user SET active = false WHERE id=?")
@Where(clause = "active=true")
public class User
{
    public User(UserCommand command)
    {
        update(command, true);
    }

    public void update(UserCommand command, boolean updatePassword)
    {
        setId(command.getId());
        setName(command.getName());
        setSurname(command.getSurname());
        setEmail(command.getEmail());
        setBirthDate(command.getBirthDate());
        setSex(command.getSex());

        if(updatePassword)
        {
            setPassword(BcryptUtil.bcryptHash(command.getPassword()));
            setRole(command.getRole());
        }
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private Date birthDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Receipt> receipts;

    @Column(nullable = false)
    private Boolean active = true;

}
