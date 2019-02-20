package com.revolut.task.repository;

import com.revolut.task.exception.AccountNotFoundException;
import com.revolut.task.model.Transfer;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class TransferRepository {

    private final Map<Long, Transfer> transferStorage = new ConcurrentHashMap<>();
    private AtomicLong transferId = new AtomicLong(1);


    public List<Transfer> findAll() {
        return new ArrayList<>(transferStorage.values());
    }

    public Transfer findById(Long id) throws AccountNotFoundException {

        Optional<Transfer> transfer = Optional.ofNullable(transferStorage.get(id));
        //TODO добавить  message
        return transfer.orElseThrow(() -> new AccountNotFoundException(""));
    }

    public void delete(Long id) {
        transferStorage.remove(id);
    }

}
