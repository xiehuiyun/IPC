package cn.jhc.startdemo.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Shadows on 2017/3/24.
 */

public class StrUtil {

    /**
     * 将字符串des加密并进行url编码
     *
     * @param content
     * @return
     */
    public static String getSign(String content) {
        String sign = StrUtil.MD5(content + StrUtil.getMD5Key());
        return sign;
    }

    /**
     * 将字符串des加密并进行url编码
     *
     * @param content
     * @return
     */
    public static String getDesURLEncodeString(String content) {
        String a = "";
        try {
            a = URLEncoder.encode(DES.encrypt(content, StrUtil.getDesKey()), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    public static native String MD5(String msg);

    public static native String encodeAES(String msg);

    public static native String decodeAES(String msg);

    public static native String encrypt3DES(String msg);

    public static native String decrypt3DES(String msg);

    public static native String decryptRSA(String msg);

    public static native String encryptRSA(String msg);

    public static native String encryptBase64(String msg);

    public static native String decryptBase64(String msg);

    public static native String getDesKey();

    public static native String getMD5Key();

    static {
        System.loadLibrary("native");
    }

}
