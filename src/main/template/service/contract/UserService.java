package template.service.contract;

import template.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {
    @Transactional
    void save(User user);

    User findByLogin(String login);

    User findByEmail(String email);

    List<User> getUsersList();

    @Transactional
    void addInBlackList(int id);

    @Transactional
    void removeFromBlackList(int id);
}
