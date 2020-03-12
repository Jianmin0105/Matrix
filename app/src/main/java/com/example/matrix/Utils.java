package com.example.matrix;


import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class Utils {
    public static String md5Encryption(String password) {
        String res = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes(Charset.forName("UTF8")));
            byte[] resultByte = messageDigest.digest();
            res = new String(Hex.encodeHex(resultByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
