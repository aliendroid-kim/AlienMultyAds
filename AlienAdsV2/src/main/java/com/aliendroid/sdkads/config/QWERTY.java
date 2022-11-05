package com.aliendroid.sdkads.config;


import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class QWERTY {
    private static final String ALGORITHM = "AES";
    private static final byte[] SALT = "tHeApAcHe6410111".getBytes();// THE KEY MUST BE SAME
    public static String ALEXA;
    static Key getSalt() {
        return new SecretKeySpec(SALT, ALGORITHM);
    }

    public static String ZXC(String value) {
        if (value == null) {
            return null;
        }

        Key salt = getSalt();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, salt);
            byte[] decodedValue = Base64.decode(value, Base64.DEFAULT);
            byte[] decValue = cipher.doFinal(decodedValue);
            return new String(decValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ONE(String value) {
        try {
            String result = value.replace("dk3f9U34JRCZOyiUo2gTYg=="," ");
            String arr[] = result.split(" ", 2);
            String ALEXA = arr[0];
            return new String(ALEXA);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

    public static String TWO(String value) {
        try {
            String result = value.replace("dk3f9U34JRCZOyiUo2gTYg=="," ");
            String arr[] = result.split(" ", 2);
            String ALEXA = arr[1];
            return new String(ALEXA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
