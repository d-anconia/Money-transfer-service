package com.revolut.task.validator;

import com.revolut.task.exception.TransferValidationException;
import com.revolut.task.model.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

import static com.revolut.task.exception.ExceptionMessage.*;

public class TransferValidator {

    public TransferValidator() {
    }

    private static Logger logger = LogManager.getLogger(TransferValidator.class);


    public void validate(Transfer transfer) throws TransferValidationException {
        if (transfer == null) {
            logger.error("Missing transfer");
            throw new TransferValidationException(TRANSFER_IS_NULL);
        }
        if (transfer.getSenderId() == transfer.getRecipientId()) {
            logger.error("Same accounts");
            throw new TransferValidationException(SAME_ACCOUNTS_EXCEPTION);
        }
        if (isAmountTransferValid(transfer)) {
            logger.error("Amount of transfer is invalid");
            throw new TransferValidationException(AMOUNT_OF_TRANSFER_IS_NOT_VALID);
        }
    }

    private boolean isAmountTransferValid(Transfer transfer) {
        return transfer.getAmount() == null || transfer.getAmount().compareTo(BigDecimal.ZERO) <= 0;
    }

}
