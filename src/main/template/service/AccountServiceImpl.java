package template.service;

import template.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import template.model.Account;
import template.service.contract.AccountService;

public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean payOff(int userId, int sum) {
        int currentMoney = accountRepository.findAccountByUserId(userId).getCurrentMoney();
        if (currentMoney < sum) {
            return false;
        }
        accountRepository.setCurrentMoney(userId, currentMoney - sum);
        return true;
    }

    @Override
    public void scoreUp(int userId, int sum) {
        int currentMoney = accountRepository.findAccountByUserId(userId).getCurrentMoney();
        accountRepository.setCurrentMoney(userId, currentMoney + sum);
    }

    @Override
    public int current(int userId) {
        return accountRepository.findAccountByUserId(userId).getCurrentMoney();
    }

    @Override
    public void addAccount(int userId, int startMoney) {
        Account account = new Account(userId, startMoney);
        accountRepository.save(account);
    }
}
