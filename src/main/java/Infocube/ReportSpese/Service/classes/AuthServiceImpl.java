package Infocube.ReportSpese.Service.classes;

import Infocube.ReportSpese.Model.User;
import Infocube.ReportSpese.Repository.IUserRepository;
import Infocube.ReportSpese.Service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements IAuthService
{
    private final IUserRepository authRepository;

    @Autowired
    public AuthServiceImpl(IUserRepository authRepository)
    {
        this.authRepository = authRepository;
    }


    @Override
    public User checkUser(String mail, String pass) {
        return authRepository.findByEmailAndPassword(mail, pass);
    }

    @Override
    public User checkUser(String mail)
    {
        return authRepository.findByEmail(mail);
    }

}
