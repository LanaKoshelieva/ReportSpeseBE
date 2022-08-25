package Infocube.ReportSpese.Service.classes;

import Infocube.ReportSpese.Model.User;
import Infocube.ReportSpese.Repository.IUserRepository;
import Infocube.ReportSpese.Service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService
{
    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }



    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Integer id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User u, Integer id)
    {
        u.setId(id);
        return userRepository.save(u);
    }

    @Override
    public User createUser(User u)
    {
        return userRepository.save(u);
    }

    @Override
    public List<User> userList()
    {
        return userRepository.findAll();
    }
}
