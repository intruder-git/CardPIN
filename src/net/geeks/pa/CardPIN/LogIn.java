package net.geeks.pa.CardPIN;

import java.util.Scanner;

public class LogIn {
    private static int counter = 0;
    private static int attempt = 3;
    private static boolean success;

    boolean isSuccess() {
        return success;
    }

    static void doLogIn() {
        Scanner sc = new Scanner(System.in);
        while (counter < attempt) {
            System.out.print("Enter pin: ");
            String pin = sc.nextLine();
            counter++;
            loginStatus(pin, counter, attempt);
            if (success) break;
        }
    }

    static void loginStatus(String pin, int cntr, int att) {
        PINCodeVerification pcv = new PINCodeVerification();
        if (pcv.isStateOfVerification(pin)) {
            System.out.println("You have access to your bank account.");
            success = true;
        } else {
            if (cntr < att) {
                System.out.println("Incorrect PIN code. Your have " + (att - cntr) + " attempt(s)");
                success = false;
            } else {
                System.out.println("Incorrect PIN code. Your card has been eaten.");
                success = false;
            }
        }
    }

    public static void main(String[] args) {
        doLogIn();
    }
}
