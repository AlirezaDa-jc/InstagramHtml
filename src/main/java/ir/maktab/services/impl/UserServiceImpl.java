package ir.maktab.services.impl;

import ir.maktab.Dao.UserRepository;
import ir.maktab.Dao.impl.UserRepositoryImpl;
import ir.maktab.base.services.impl.BaseServiceImpl;
import ir.maktab.base.services.impl.Service;
import ir.maktab.domains.User;
import ir.maktab.services.UserService;

import java.util.Date;

public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService, Service {
    private static User user = new User();

    public UserServiceImpl() {
        UserRepository repository = new UserRepositoryImpl();
        super.setRepository(repository);
    }

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserServiceImpl.user = user;
    }

    @Override
    public boolean login(String userName, String password) {
        user = baseRepository.userLogin(userName, password);
        return user != null;
    }

    @Override
    public boolean signIn(String userName, String name, String password) {
        try {
            User user = new User();
            user.setUserName(userName);
            user.setName(name);
            user.setPassword(password);
            user.setDate(new Date());
            baseRepository.saveOrUpdate(user);
            setUser(user);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean follow(String userName) {
        try {
            User following = baseRepository.findByUserName(userName);
            following.addFollower(user);
            baseRepository.saveOrUpdate(following);
            baseRepository.saveOrUpdate(user);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

}
