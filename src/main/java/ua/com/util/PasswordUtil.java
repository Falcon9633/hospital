package ua.com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PasswordUtil {

    private PasswordUtil() {
        // util class
    }

    private static Stream<Character> getRandomSpecialChars(int count, char from, char to) {
        Random random = new SecureRandom();
        IntStream specialChars = random.ints(count, from, to + 1);
        return specialChars.mapToObj(data -> (char) data);
    }

    public static String generateRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(
                getRandomSpecialChars(2, 'a', 'z'),
                Stream.concat(
                        getRandomSpecialChars(2, 'A', 'Z'),
                        getRandomSpecialChars(2, '0', '9')
                ));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);

//        return charList.stream()
//                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
//                .toString();

        // for demonstration purposes password is always "1"
        return "1";
    }

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        byte[] salt = getSalt();
        byte[] hashedPassword = hashPassword(password, salt);
        return toHex(hashedPassword) + ":" + toHex(salt);
    }

    public static boolean comparePasswords(String password, String storedPassword) throws NoSuchAlgorithmException {
        String[] parts = storedPassword.split(":");
        byte[] storedPwdHash = fromHex(parts[0]);
        byte[] salt = fromHex(parts[1]);

        byte[] pwdHash = hashPassword(password, salt);

        int diff = storedPwdHash.length ^ pwdHash.length;
        for (int i = 0; i < storedPwdHash.length && i < pwdHash.length; i++) {
            diff |= storedPwdHash[i] ^ pwdHash[i];
        }

        return diff == 0;
    }

    private static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        return md.digest(password.getBytes());
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
