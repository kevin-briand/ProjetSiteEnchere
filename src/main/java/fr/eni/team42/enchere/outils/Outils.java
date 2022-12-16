package fr.eni.team42.enchere.outils;

public class Outils {
    public static boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
}
