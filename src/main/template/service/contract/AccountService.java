package template.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface AccountService {
    @Transactional
    boolean payOff(int userId, int sum);

    @Transactional
    void scoreUp(int userId, int sum);

    int current(int userId);

    void addAccount(int userId, int startMoney);
}
