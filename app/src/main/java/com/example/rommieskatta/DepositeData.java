package com.example.rommieskatta;

public class DepositeData {
    String name;
    String moneyDeposited;
    String remaining;

    public DepositeData(String name, String moneyDeposited, String remaining) {
        this.name = name;
        this.moneyDeposited = moneyDeposited;
        this.remaining = remaining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoneyDeposited() {
        return moneyDeposited;
    }

    public void setMoneyDeposited(String moneyDeposited) {
        this.moneyDeposited = moneyDeposited;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }
}
