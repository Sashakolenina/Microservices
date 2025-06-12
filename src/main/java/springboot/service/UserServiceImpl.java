package springboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.model.User;
import springboot.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void update(Long id, User user) {
      User userById = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
      userById.setFirstName(user.getFirstName());
      userById.setLastName(user.getLastName());
      userById.setEmail(user.getEmail());
      userRepository.save(userById);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User userDel = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        userRepository.delete(userDel);
    }
}
