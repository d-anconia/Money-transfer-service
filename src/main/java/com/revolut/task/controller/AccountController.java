package com.revolut.task.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.task.exception.AccountNotFoundException;
import com.revolut.task.model.Account;
import com.revolut.task.service.AccountService;
import org.jooby.Err;
import org.jooby.Result;
import org.jooby.Status;
import org.jooby.mvc.*;

import javax.inject.Inject;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

@Path("/accounts")
public class AccountController {

    private AccountService accountService;

    @Inject
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @POST
    @Path("/newaccount")
    public Result createAccount(@Body String body) throws IOException {

        JsonNode node = new ObjectMapper().readTree(new StringReader(body));
        accountService.createAccount(new BigDecimal(node.get("balance").asText()));
        return new Result().status(Status.CREATED);
    }


    @GET
    @Path("account/{id}")
    public Account getAccountById(Long id) {
        try {
            return accountService.findAccountById(id);
        } catch (AccountNotFoundException e) {
            throw new Err(Status.NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/list")
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @DELETE
    @Path("account/{id}")
    public Result deleteAccountById(Long id) {
        accountService.deleteAccountById(id);
        return new Result().status(Status.OK);
    }

}
