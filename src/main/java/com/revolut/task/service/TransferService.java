package com.revolut.task.service;

import com.revolut.task.exception.AccountNotFoundException;
import com.revolut.task.exception.TransferFailedException;
import com.revolut.task.exception.TransferValidationException;
import com.revolut.task.model.Account;
import com.revolut.task.model.Transfer;
import com.revolut.task.repository.AccountRepository;
import com.revolut.task.validator.TransferValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.revolut.task.exception.ExceptionMessage.IMPOSSIBLE_TO_LOCK_ACCOUNT;
import static com.revolut.task.exception.ExceptionMessage.NOT_ENOUGH_MONEY;

@Singleton
public class TransferService {

    private AccountRepository accountRepository;
    private TransferValidator validator;

    public TransferService() {

    }

    @Inject
    public TransferService(AccountRepository accountRepository, TransferValidator validator) {
        this.accountRepository = accountRepository;
        this.validator = validator;
    }


    public void transfer(Transfer transfer) throws AccountNotFoundException, TransferFailedException, TransferValidationException {

        validator.validate(transfer);

        Account sender = accountRepository.findById(transfer.getSenderId());
        Account recipient = accountRepository.findById(transfer.getRecipientId());

        List<Account> accounts = getAccountsOrderedById(sender, recipient);

        tryLockAccounts(accounts);

        if (transfer.getAmount().compareTo(sender.getBalance().get()) > 0)
            throw new TransferFailedException(NOT_ENOUGH_MONEY);

        sender.getBalance().updateAndGet(balance -> balance.subtract(transfer.getAmount()));
        recipient.getBalance().updateAndGet(balance -> balance.add(transfer.getAmount()));

        unlockAccounts(accounts);

    }

    /*
        Ordered locking
     */
    private void tryLockAccounts(List<Account> accountList) throws TransferFailedException {

        for (Account account : accountList) {
            boolean isAcquired = account.getLock()
                    .writeLock()
                    .tryLock();

            if (!isAcquired) {
                throw new TransferFailedException(IMPOSSIBLE_TO_LOCK_ACCOUNT);
            }
        }
    }

    private void unlockAccounts(List<Account> accountList) {
        for (Account account : accountList)
            account.getLock().writeLock().unlock();
    }

    private List<Account> getAccountsOrderedById(Account... accounts) {
        return Arrays.stream(accounts)
                .sorted(Comparator.comparingLong(Account::getId))
                .collect(Collectors.toList());
    }

}
