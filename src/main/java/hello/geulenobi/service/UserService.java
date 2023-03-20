package hello.geulenobi.service;

import hello.geulenobi.domain.User;

public interface UserService {
    User register(User user);
    User login(String email, String password);


}