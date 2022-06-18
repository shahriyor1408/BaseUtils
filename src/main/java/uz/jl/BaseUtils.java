package uz.jl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class BaseUtils implements Colors {
    public static Gson gson = new GsonBuilder().setDateFormat("dd.MM.YYYY  HH:mm:ss").setPrettyPrinting().create();
    public static Gson gsonWithNulls = (new GsonBuilder()).setPrettyPrinting().serializeNulls().create();
    private static final Scanner readText;
    private static final Scanner readNumerics;

    public BaseUtils() {
    }

    public static String readText() {
        return readText.nextLine();
    }

    public static String readText(String data) {
        print(data, "\u001b[34m");
        return readText.nextLine();
    }

    public static String readText(String data, String color) {
        print(data, color);
        return readText.nextLine();
    }

    public static void print(String data) {
        print(data, "\u001b[34m");
    }

    public static void print(String data, String color) {
        System.out.print(color + data + "\u001b[0m");
    }

    public static void println(Object data) {
        println(data, "\u001b[34m");
    }

    public static void println(Object data, String color) {
        System.out.println(color + data + "\u001b[0m");
    }

    public static void println(String data, Object... args) {
        System.out.printf(data + "%n", args);
    }

    static {
        readText = new Scanner(System.in);
        readNumerics = new Scanner(System.in);
    }

    public static String otp(int bound){
        int min = (int) Math.pow(10,bound-1);
        int max = (int) Math.pow(10,bound);
        return String.valueOf(new Random().nextInt(min,max));
    }

    public static String encryptWithBlowfish(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");

        cipher.init(Cipher.ENCRYPT_MODE, KS);
        byte[] bytes = cipher.doFinal(text.getBytes());
        return new String(Base64.getEncoder().encode(bytes));
    }

    public static String decryptWithBlowfish(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE,KS);
        byte[] decr = cipher.doFinal(Base64.getDecoder().decode(text.getBytes()));
        return new String(decr);
    }

}
