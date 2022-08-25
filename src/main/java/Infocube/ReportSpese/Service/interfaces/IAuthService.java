package Infocube.ReportSpese.Service.interfaces;

import Infocube.ReportSpese.Model.User;

public interface IAuthService
{
    void checkUser(String mail, String password, Integer id);

    User checkUser(String mail);
}
