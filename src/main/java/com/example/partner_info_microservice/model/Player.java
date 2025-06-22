package com.example.partner_info_microservice.model;

import java.math.BigDecimal;

public class Player {

    int id;
    String name;
    String partnerId;
    BigDecimal walletAmount;


    public Player(int id, String partnerId, String name, BigDecimal walletAmount) {

        this.id=id;
        this.partnerId=partnerId;
        this.name = name;
        this.walletAmount = walletAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(BigDecimal walletAmount) {
        this.walletAmount = walletAmount;
    }

    public String getPartnerId() {
        return partnerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", walletAmount=" + walletAmount +
                '}';
    }
}
