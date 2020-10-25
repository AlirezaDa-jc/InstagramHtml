package ir.maktab.services;

import ir.maktab.Dao.UserRepository;
import ir.maktab.base.services.BaseService;
import ir.maktab.domains.User;

public interface UserService extends BaseService<User,Long> {
    boolean login(String userName , String password);

    boolean signIn(String userName,String name, String password);

    boolean follow(String userName);
}
