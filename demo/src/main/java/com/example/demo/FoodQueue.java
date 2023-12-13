package com.example.demo;

public class FoodQueue {
    private final Customer customer;
    private int queueNumber;

    //constructor

    public FoodQueue(Customer customer, int queueNumber) {
        this.customer = customer;
        this.queueNumber = queueNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String printFile() {
        return customer.getFirstName() + " " + customer.getSecondName() + " required no of burgers: " + customer.getNoOfBurgers();
    }

    public String guiPrint() {
        return customer.getFirstName() + " " + customer.getSecondName() + "\nrequired no of burgers: " + customer.getNoOfBurgers();
    }
}

