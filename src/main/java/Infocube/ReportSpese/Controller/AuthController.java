package Infocube.ReportSpese.Controller;

import Infocube.ReportSpese.Command.AuthCommand;
import Infocube.ReportSpese.DTO.AuthDTO;
import Infocube.ReportSpese.Model.User;
import Infocube.ReportSpese.Service.interfaces.IAuthService;
import Infocube.ReportSpese.Service.interfaces.IUserService;
import Infocube.ReportSpese.Utility.HTTPUtility;
import Infocube.ReportSpese.Utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

import java.util.List;

@RestController
@RequestMapping(HTTPUtility.CONTROLLER_ROOT)
public class AuthController {
    private final IAuthService authService;
    private final IUserService userService;

    @Autowired
    public AuthController(IAuthService authService, IUserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @PostMapping(HTTPUtility.CONTROLLER_LOGIN)
    public Response logIn(@RequestBody AuthCommand command)
    {
        try
        {
            User user = authService.checkUser(command.getEmail());
            if (user != null)
            {
                if(verifyBCryptPassword(user.getPassword(), command.getPassword()))
                {
                    return new Response(new AuthDTO(user));
                }
                return new Response("Password errata", 401, null);
            }
            else
            {
                return new Response("Utente non esiste", 404, null);
            }
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    public static boolean verifyBCryptPassword(String bCryptPasswordHash, String passwordToVerify) throws Exception {

        WildFlyElytronPasswordProvider provider = new WildFlyElytronPasswordProvider();

        // 1. Create a BCrypt Password Factory
        PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, provider);

        // 2. Decode the hashed user password
        Password userPasswordDecoded = ModularCrypt.decode(bCryptPasswordHash);

        // 3. Translate the decoded user password object to one which is consumable by this factory.
        Password userPasswordRestored = passwordFactory.translate(userPasswordDecoded);

        // Verify existing user password you want to verify
        return passwordFactory.verify(userPasswordRestored, passwordToVerify.toCharArray());

    }
}