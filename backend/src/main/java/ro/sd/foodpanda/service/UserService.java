package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.model.User;
import ro.sd.foodpanda.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Iterable<User> all = userRepository.findAll();
        all.forEach(users::add);
        return users;
    }
}
