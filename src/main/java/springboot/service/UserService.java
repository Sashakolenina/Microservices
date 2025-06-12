package springboot.service;



import springboot.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> listUsers();

    User getUserById(Long id);

    void update(Long id, User user);

    void delete(Long id);
}