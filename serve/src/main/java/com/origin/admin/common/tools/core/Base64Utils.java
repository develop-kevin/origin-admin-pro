package com.origin.admin.common.tools.core;

import org.apache.xmlbeans.impl.util.Base64;

import java.io.*;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/12/1 18:08
 */
public class Base64Utils {
    /**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;

    /**
     * BASE64字符串解码为二进制数据
     * @param base64 base64
     * @return byte[]
     * @throws Exception 异常
     */
    public static byte[] decode(String base64) throws Exception {
        return Base64.decode(base64.getBytes());
    }

    /**
     * 二进制数据编码为BASE64字符串
     * @param bytes bytes
     * @return String
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.encode(bytes));
    }

    /**
     * 将文件编码为BASE64字符串，大文件慎用，可能会导致内存溢出
     * @param filePath 文件绝对路径
     */
    public static String encodeFile(String filePath) throws Exception {
        byte[] bytes = fileToByte(filePath);
        return encode(bytes);
    }

    /**
     * BASE64字符串转回文件
     * @param filePath 文件绝对路径
     * @param base64 编码字符串
     */
    public static void decodeToFile(String filePath, String base64) throws Exception {
        byte[] bytes = decode(base64);
        byteArrayToFile(bytes, filePath);
    }

    /**
     * 文件转换为二进制数组
     * @param filePath 文件路径
     */
    public static byte[] fileToByte(String filePath) throws Exception {
        byte[] data = new byte[0];
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file);
                 ByteArrayOutputStream out = new ByteArrayOutputStream(2048);) {
                byte[] cache = new byte[CACHE_SIZE];
                int nRead = 0;
                while ((nRead = in.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                    out.flush();
                }
                data = out.toByteArray();
            }
        }
        return data;
    }

    /**
     * 二进制数据写文件
     * @param bytes 二进制数据
     * @param filePath 文件生成目录
     */
    public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
        File destFile = new File(filePath);
        try (InputStream in = new ByteArrayInputStream(bytes);
             OutputStream out = new FileOutputStream(destFile);) {
            if (!destFile.getParentFile().exists()) {
               boolean bool1 = destFile.getParentFile().mkdirs();
            }
            boolean bool2 = destFile.createNewFile();
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
        }
    }
}
