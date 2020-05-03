package org.ADDU.Model;

import org.ADDU.JavaClasses.Loan;

import java.util.ArrayList;

public class Manager {
    private int id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private ArrayList<String> cards=new ArrayList<String>();
    private ArrayList<Loan> loans=new ArrayList<Loan>();

    public void addLoan(Loan loan){this.loans.add(loan);}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ArrayList<String> getCards() {
        return cards;
    }

    public void setCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", cards=" + cards +
                ", loans=" + loans +
                '}';
    }

    public void addCard(String cardNumber){
        this.cards.add(cardNumber);
    }

}
