package com.revolut.task.repository;


import com.revolut.task.exception.AccountNotFoundException;
import com.revolut.task.model.Account;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Singleton
public class AccountRepository {


    private final Map<Long, Account> accountStorage = new ConcurrentHashMap<>();
    private AtomicLong accountId = new AtomicLong(1);


    public Account createAccount(BigDecimal balance) {

        Long newAccountId = accountId.getAndIncrement();
        Account account = new Account(newAccountId, balance);
        accountStorage.put(newAccountId, account);
        return account;
    }


    public List<Account> findAll() {
        return new ArrayList<>(accountStorage.values());
    }


    public void update(Account account) {
        accountStorage.put(account.getId(), account);
    }


    public Account findById(Long id) throws AccountNotFoundException {

        Optional<Account> account = Optional.ofNullable(accountStorage.get(id));
        //TODO добавить  message
        return account.orElseThrow(() -> new AccountNotFoundException(""));
    }

    public void delete(Long id) {
        accountStorage.remove(id);
    }
}
