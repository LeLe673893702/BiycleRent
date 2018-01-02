package com.bicyclerent.feixingbike.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 刺雒 on 2017/2/2.
 */
public class MD5Util {
    public static String ToMD5(String pwd){
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            md5Digest.update(pwd.getBytes("utf-8"));
            byte[] md5s = md5Digest.digest();
            int length = md5s.length;
            char str[] = new char[length * 2];
            int k = 0;
            for(int i = 0; i < length; i++){
                byte b = md5s[i];
                str[k++] = hexDigits[b >>> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            Log.d("password",new String(str));
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
