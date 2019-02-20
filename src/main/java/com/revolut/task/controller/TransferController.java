package com.revolut.task.controller;


import com.revolut.task.exception.AccountNotFoundException;
import com.revolut.task.exception.TransferFailedException;
import com.revolut.task.exception.TransferValidationException;
import com.revolut.task.model.Transfer;
import com.revolut.task.service.TransferService;
import org.jooby.Result;
import org.jooby.Status;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import javax.inject.Inject;

public class TransferController {

    private TransferService transferService;

    @Inject
    public TransferController(TransferService transferService) {
        this.transferService = new TransferService();
    }

    @POST
    @Path("/transfer")
    public Result makeTransfer(Transfer transfer) {
        try {
            transferService.transfer(transfer);
            return new Result().status(Status.OK);

        } catch (AccountNotFoundException | TransferValidationException | TransferFailedException e) {
            return new Result().status(Status.BAD_REQUEST);
        }
    }
}
