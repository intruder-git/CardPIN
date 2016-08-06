package net.geeks.pa.CardPIN;

public class PINCodeVerification {
    private String accountPIN = "1234";

    public boolean isStateOfVerification(String inputtedPIN) {
        return accountPIN.equals(inputtedPIN);
    }
}
