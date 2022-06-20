package by.tms.bookshop.repository;
import by.tms.bookshop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin (String login);
    User findByUserName(String userName);
    User findByActivationCode(String code);
}
