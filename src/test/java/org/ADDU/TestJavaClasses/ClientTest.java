package org.ADDU.TestJavaClasses;


import org.ADDU.JavaClasses.Loan;
import org.junit.Test;
import org.ADDU.Exceptions.CouldNotFindClient;
import org.ADDU.Model.Client;
import org.ADDU.Model.Manager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ClientTest {

    private Client clt;
    private ArrayList<Loan> loans;
    private Loan l;

    @Test
    public void getUsernameTest() {
        clt = new Client();
        clt.setUsername("Diana");
        assertEquals("Diana",clt.getUsername());
    }

    @Test
    public void getPasswordTest(){
        clt = new Client();
        clt.setPassword("test");;
        assertEquals("test",clt.getPassword());
    }

    @Test
    public void getFirstNameTest(){
        clt = new Client();
        clt.setFirstName("Denis");;
        assertEquals("Denis",clt.getFirstName());
    }

    @Test
    public void getLastNameTest(){
        clt = new Client();
        clt.setLastName("Avram");;
        assertEquals("Avram",clt.getLastName());
    }

    @Test
    public void getIbanTest(){
        clt = new Client();
        clt.setIban("RO12345IBAN67");;
        assertEquals("RO12345IBAN67",clt.getIban());
    }

    @Test
    public void getSoldTest(){
        clt = new Client();
        clt.setSold("900");;
        assertEquals("900",clt.getSold());
    }

    @Test
    public void getManagerIdTest(){
        clt = new Client();
        clt.setManagerId("3");;
        assertEquals("3",clt.getManagerId());
    }

    @Test
    public void addLoansTest(){
        clt = new Client();
        loans = new ArrayList<Loan>();
        l = new Loan();
        loans.add(l);
        clt.setLoans(loans);
        assertEquals("1",String.valueOf(loans.size()));
    }
}
