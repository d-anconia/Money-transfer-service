package com.revolut.task.model;


import java.math.BigDecimal;

public class Transfer {

    private Long id;
    private Long senderId;
    private Long recipientId;
    private BigDecimal amount;

    public Transfer(){}

    public Transfer(Long id, Long senderId, Long recipientId, BigDecimal amount) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getSenderId() {
        return senderId;
    }


    public Long getRecipientId() {
        return recipientId;
    }


    public BigDecimal getAmount(){
        return amount;
    }
}
