package com.revolut.task.exception;

public class ExceptionMessage {

    public static final String TRANSFER_IS_NULL = "There is no transfer";
    public static final String SAME_ACCOUNTS_EXCEPTION = "Recipient and Sender are the same person";
    public static final String AMOUNT_OF_TRANSFER_IS_NOT_VALID = "Sorry, but we can not send such amount";

    public static final String IMPOSSIBLE_TO_LOCK_ACCOUNT = "Impossible to lock account";
    public static final String NOT_ENOUGH_MONEY = "Sender's balance is less than transfer amount";
}
