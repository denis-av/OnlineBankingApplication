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

public class ManagerTest {

    private Manager mng;
    private ArrayList<String> cards1;
    private ArrayList<String> cards2;

    @Test
    public void  getIdTest(){
        mng = new Manager();
        mng.setId(1);
        assertEquals(1,mng.getId());
    }

    @Test
    public void  getUsernameTest(){
        mng = new Manager();
        mng.setUsername("mngg");
        assertEquals("mngg",mng.getUsername());
    }

    @Test
    public void  getPasswordTest(){
        mng = new Manager();
        mng.setPassword("password");
        assertEquals("password",mng.getPassword());
    }

    @Test
    public void  getFirstNameTest(){
        mng = new Manager();
        mng.setFirstName("Diana");
        assertEquals("Diana",mng.getFirstName());
    }

    @Test
    public void  getLastNameTest(){
        mng = new Manager();
        mng.setLastName("Udisteanu");
        assertEquals("Udisteanu",mng.getLastName());
    }

    @Test
    public void  getCardsTest(){
        mng = new Manager();
        cards1 = new ArrayList<String>();
        cards1.add("1234");
        cards1.add("5678");
        cards2 = new ArrayList<String>();
        cards2.add("5678");
        mng.setCards(cards2);
        assertNotEquals(cards1,mng.getCards());
    }
}
