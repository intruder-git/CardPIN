package net.geeks.pa.CardPIN;

import org.junit.*;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LogInTest extends LogIn{

    public void doLoginCheck(String[] pins) {
        int counter = 0;
        int attempt = 3;
        InputStream stdin = System.in;
        while (counter < attempt) {
            for (String pin : pins) {
                try {
                    System.setIn(new ByteArrayInputStream(pin.getBytes()));
                    Scanner scanner = new Scanner(System.in);
                    counter++;
                    loginStatus(scanner.nextLine(), counter, attempt);
                    if (isSuccess()) break;
                } finally {
                    System.setIn(stdin);
                }
            }
            if (isSuccess()) break;
        }
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void unsuccessLogInCheck() throws Exception {
        String[] pins = {"1111", "2222", "3333"};
        doLoginCheck(pins);
        assertEquals("Incorrect PIN code. Your have 2 attempt(s)\r\n" +
                     "Incorrect PIN code. Your have 1 attempt(s)\r\n"+
                     "Incorrect PIN code. Your card has been eaten.\r\n",
                     outContent.toString());
    }

    @Test
    public void successLogInCheck () throws Exception {
        String[] pins = {"1234"};
        doLoginCheck(pins);
        assertEquals("You have access to your bank account.\r\n", outContent.toString());
    }

    @Test
    public void secondSuccessLogInCheck() throws Exception {
        String[] pins = {"10234", "1234"};
        doLoginCheck(pins);
        assertEquals("Incorrect PIN code. Your have 2 attempt(s)\r\n" +
                     "You have access to your bank account.\r\n",
                     outContent.toString());
    }

    @Test
    public void thirdSuccessLogInCheck() throws Exception {
        String[] pins = {"1478", "1547", "1234"};
        doLoginCheck(pins);
        assertEquals("Incorrect PIN code. Your have 2 attempt(s)\r\n" +
                     "Incorrect PIN code. Your have 1 attempt(s)\r\n" +
                     "You have access to your bank account.\r\n",
                     outContent.toString());
    }
}