package fr.eni.team42.enchere.bll;

import fr.eni.team42.enchere.BusinessException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashManager {
    public static String hashMdp(String input) throws BusinessException {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(BLLExceptionCode.ERREUR_HASH);
        }
    }
}