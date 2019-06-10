package com.chainspower.mytab;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * Description:
 * Date：2019/2/20-18:18
 * Author: cwh
 */
public class EntryUtils {

    public static final String SECRET = "01d1f2736eae4277bb1f4ae6b7ad702c";


    public static final String SMS_KEY = "7d6d9f681e0d445980ba254bd14f76f5";


    public static void main(String[] args){

    }

    /**
     * 参数加密后参数
     *
     * @param params
     * @return
     */
    public static String entrycpt(TreeMap<String, String> params) {
        String paramsStr = sortAndCreateStr(params);
        return sha256_HMAC(paramsStr, SECRET);
    }

    /**
     * 拼接参数
     *
     * @param params
     * @return
     */
    private static String sortAndCreateStr(TreeMap<String, String> params) {
        StringBuffer stringBuffer = new StringBuffer();
        int index = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value!=null && value.length()>0) {
                if (index == 0) {
                    stringBuffer.append(key).append("=").append(value);
                } else {
                    stringBuffer.append("&").append(key).append("=").append(value);
                }
                index++;
            }
        }
        stringBuffer.append("&").append("key").append("=").append(SMS_KEY);
        return stringBuffer.toString();
    }

    /**
     * HMAC-SHA256加密
     *
     * @param message
     * @param secret
     * @return
     */
    private static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }




    /**
     * 将加密后的字节数组转换成字符串(大写)
     *
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

}
