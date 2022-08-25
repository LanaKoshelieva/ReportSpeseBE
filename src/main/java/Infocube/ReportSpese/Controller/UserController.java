package Infocube.ReportSpese.Controller;

import Infocube.ReportSpese.Command.UserCommand;
import Infocube.ReportSpese.DTO.UserDTO;
import Infocube.ReportSpese.Model.User;
import Infocube.ReportSpese.Service.interfaces.IUserService;
import Infocube.ReportSpese.Utility.HTTPUtility;
import Infocube.ReportSpese.Utility.Response;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(HTTPUtility.CONTROLLER_ROOT)
public class UserController
{
    private final IUserService userService;

    public UserController(IUserService userService)
    {
        this.userService = userService;
    }


    @PostMapping(HTTPUtility.CONTROLLER_CREATE_PROFILE)
    public Response createUser(@Valid @RequestBody UserCommand command)
    {
        try
        {
            User newUser = new User(command);
            newUser.setRole("user");
            User u = userService.createUser(newUser);

            return new Response(new UserDTO(u));
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @PutMapping(HTTPUtility.CONTROLLER_UPDATE_PROFILE)
    public Response updateUser(@RequestBody UserCommand command)
    {
        try
        {
            User u = userService.getUser(command.getId());
            u.update(command, false);

            userService.updateUser(u, u.getId());

            return new Response(new UserDTO(u));
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @DeleteMapping(HTTPUtility.CONTROLLER_DELETE_PROFILE)
    public Response deleteUser(@PathVariable Integer id)
    {
        try
        {
            userService.deleteUser(id);
            return new Response("Utente cancellato", 200, null);
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @GetMapping(HTTPUtility.CONTROLLER_USERS)
    public Response usersList()
    {
        try
        {
            List<User> usersBase = userService.userList();
            List<UserDTO> allUsers = new ArrayList<>();

            for (int i = 0; i < usersBase.size(); i++)
            {
                allUsers.add(new UserDTO(usersBase.get(i)));
            }
            return new Response(allUsers);
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @GetMapping(HTTPUtility.CONTROLLER_GET_USER)
    public Response getUser(@PathVariable Integer id)
    {
        try
        {
            User u = userService.getUser(id);
            return new Response(new UserDTO(u));
        }
        catch (Exception e)
        {
            return new Response(e);
        }

    }

}
