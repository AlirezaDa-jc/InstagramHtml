package ir.maktab.services.impl;

import ir.maktab.Dao.UserRepository;
import ir.maktab.Dao.impl.UserRepositoryImpl;
import ir.maktab.base.services.impl.BaseServiceImpl;
import ir.maktab.base.services.impl.Service;
import ir.maktab.domains.User;
import ir.maktab.services.UserService;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
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

    @Override
    public User findByUserName(String userName) {
        return baseRepository.findByUserName(userName);
    }

    @Override
    public void unFollow(User following) {
        user.removeFollowing(following);
        following.removeFollower(user);
        saveOrUpdate(following);
        saveOrUpdate(user);
        System.out.println("Done");
    }

    @Override
    public void edit(ServletOutputStream out, String... fields) {
        try {
            if (!(fields[0] == null || fields[0].equals("null"))) {
                updateName(fields[0], out);
            }
            if (!(fields[1] == null || fields[1].equals("null"))) {
                updateUserName(fields[1], out);
            }
            if (!(fields[2] == null || fields[2].equals("null") &&
                    !(fields[3] == null || fields[3].equals("null") &&
                            !(fields[4] == null || fields[4].equals("null"))))) {
                updatePassword(fields[2], fields[3], fields[4], out);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private void updatePassword(String currentPassword, String password, String confirmationPassword, ServletOutputStream out) throws IOException {
        if(!confirmationPassword.equals(password)){
            out.println("Wrong Password");
            return;
        }
        if(!currentPassword.equals(user.getPassword())){
            out.println("Wrong Password");
            return;
        }
        user.setPassword(password);
        saveOrUpdate(user);
        out.println("Password Changed Successfully!");
    }

    private void updateName(String name, ServletOutputStream out) throws IOException {
        user.setName(name);
        saveOrUpdate(user);
        out.println("Name Changed Successfully!");
    }

    private void updateUserName(String userName, ServletOutputStream out) throws IOException {
        user.setUserName(userName);
        try{
            saveOrUpdate(user);
        }catch (Exception ex){
            out.println("Duplicate User Name!!!!");
        }
        out.println("UserName Changed Successfully!");
    }

    @Override
    public void exit() {
        user.setDate(new Date());
        baseRepository.saveOrUpdate(user);
    }
}
