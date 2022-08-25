package Infocube.ReportSpese.Service.classes;

import Infocube.ReportSpese.Model.User;
import Infocube.ReportSpese.Repository.IUserRepository;
import Infocube.ReportSpese.Service.interfaces.IAuthService;
import io.quarkus.security.UnauthorizedException;
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
    public void checkUser(String mail, String password, Integer id)
    {
        User u = authRepository.findByEmailAndPassword(mail, password);
        if(u == null)
        {
            throw new UnauthorizedException();
        }
        if(id != null && id !=u.getId())
        {
            throw new UnauthorizedException();
        }
    }

    @Override
    public User checkUser(String mail)
    {
        return authRepository.findByEmail(mail);
    }

}
