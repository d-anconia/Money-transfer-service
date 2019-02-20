package com.revolut.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {


    private Long id;
    private AtomicReference<BigDecimal> balance;

    @JsonIgnore
    private ReadWriteLock lock;

    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = new AtomicReference<>(balance);
        this.lock = new ReentrantReadWriteLock();
    }

    public Long getId() {
        return id;
    }

    public AtomicReference<BigDecimal> getBalance() {
        return balance;
    }

    public ReadWriteLock getLock() {
        return lock;
    }
}
