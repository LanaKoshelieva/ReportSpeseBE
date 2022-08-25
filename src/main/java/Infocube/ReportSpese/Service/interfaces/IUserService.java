package Infocube.ReportSpese.Service.interfaces;
import Infocube.ReportSpese.Model.User;

import java.util.List;

public interface IUserService
{
    User getUser(Integer id);

    void deleteUser(Integer id);

    User updateUser(User u, Integer id);

    User createUser(User u);

    List<User> userList();

}
