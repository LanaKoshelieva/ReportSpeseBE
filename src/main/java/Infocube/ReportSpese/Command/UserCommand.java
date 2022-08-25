package Infocube.ReportSpese.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand
{
    private Integer id;

    @NotEmpty(message = "Il nome e' obbligatorio")
    @NotNull(message = "Il nome e' obbligatorio")
    private String name;

    @NotEmpty(message = "Il cognome e' obbligatorio")
    @NotNull(message = "Il cognome e' obbligatorio")
    private String surname;

    @NotNull(message = "La mail e' obbligatoria")
    @Email(message = "Il formato della mail non e' valido")
    private String email;

    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$", message="Il formato della password non e' valido (\"Almeno una lettera minuscola, una maiuscola e un numero. Lunghezza 8-20 caratteri\")")
    private String password;

    @NotEmpty(message = "Il genere e' obbligatorio")
    @NotNull(message = "Il genere e' obbligatoria")
    /*@Pattern(regexp = "^[MF]{1}$|^ND$", message = "Sesso puo essere M, F o ND")*/
    private String sex;

    @NotNull(message = "La data di nascita e' obbligatoria")
    private Date birthDate;

    private String role;
}
