package org.ADDU.Model;

import org.ADDU.JavaClasses.Loan;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Client {
    private String username;
    private String password;
    private String cardNumber;
    private String firstName;
    private String lastName;
    private String iban;
    private String sold;
    private String managerId;
    private ArrayList<Loan> loans=new ArrayList<Loan>();

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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan){this.loans.add(loan);}


    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", iban='" + iban + '\'' +
                ", sold='" + sold + '\'' +
                ", managerId='" + managerId + '\'' +
                ", loans=" + loans +
                '}';
    }

}
