package ir.maktab.services;

import ir.maktab.base.services.BaseService;
import ir.maktab.domains.User;

import javax.servlet.ServletOutputStream;

public interface UserService extends BaseService<User, Long> {
    boolean login(String userName, String password);

    boolean signIn(String userName, String name, String password);

    boolean follow(String userName);

    User findByUserName(String userName);

    void unFollow(User following);

    void edit(ServletOutputStream servletOutputStream, String... fields);

    void exit();
}
