package com.fengzai.feign.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * @PACKAGE_NAME: com.fengzai.feign.util
 * @author: rhf
 * @description: hash256加密
 * @DATE: 2020/9/11
 **/
public class Sha256Util {

    public static String encode(String text, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bs = messageDigest.digest((text + salt).getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean matches(String text, String salt, String secret) {
        if (secret.equals(encode(text, salt))) {
            return true;
        }
        return false;
    }

}
