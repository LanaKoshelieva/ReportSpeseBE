package Infocube.ReportSpese.Service.interfaces;

import Infocube.ReportSpese.Model.User;

public interface IAuthService
{
    User checkUser(String mail, String pass);

    User checkUser(String mail);
}
