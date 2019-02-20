package com.revolut.task.service;

import com.revolut.task.exception.AccountNotFoundException;
import com.revolut.task.model.Account;
import com.revolut.task.repository.AccountRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.List;

@Singleton
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    public void createAccount() {
        accountRepository.createAccount(BigDecimal.ZERO);
    }

    public void createAccount(BigDecimal balance) {
        accountRepository.createAccount(balance);
    }

    public Account findAccountById(Long id) throws AccountNotFoundException {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccountById(Long id) {
        accountRepository.delete(id);
    }


}
