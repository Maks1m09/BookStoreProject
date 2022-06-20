package by.tms.bookshop.service;

import by.tms.bookshop.entity.User;
import by.tms.bookshop.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    public User findUserByLogin(String login) {
        if (login == null) {
            logger.debug("Error.Check login, because it is equal null");
        }
        logger.debug("user was successfully found in method findUserByLogin");
        return userRepository.findByLogin(login);
    }


    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUserName(user.getUsername());
        if (userFromDB != null) {
            logger.debug("Error.This user already exists in the database");
            return false;
        }
        user.setPassword(user.getPassword());
        logger.debug("The user was successfully saved");
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to BookShop. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    public User findUserById(Long id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean createUser(Long id) {
        if (id != null) {
            userRepository.existsById(id);
            return true;
        }
        return false;
    }

    public Optional<User> findUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            logger.debug("The user was not found, it is equal to null");
        }
        logger.debug("The user was successfully found");
        return user;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(code);
        userRepository.save(user);
        return true;
    }
}
