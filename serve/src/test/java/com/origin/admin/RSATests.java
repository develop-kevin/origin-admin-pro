package com.origin.admin;

import com.origin.admin.common.tools.core.Base64Utils;
import com.origin.admin.common.tools.core.RSAUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/12/1 18:50
 */
@SpringBootTest
public class RSATests{
    @Test
    void run() throws Exception {
        System.out.println("********************生成公钥、私钥********************");
        Map<String, Object> map = RSAUtils.generateRSAKeyPair();
        System.out.println("RSA公钥：" + RSAUtils.getRSAPublicKey(map));
        System.out.println("RSA私钥：" + RSAUtils.getRSAPrivateKey(map));
        String publicKey = RSAUtils.getRSAPublicKey(map);
        String privateKey = RSAUtils.getRSAPrivateKey(map);
        String json = "中文，abc,！@#";

        //公钥加密
        System.out.println("********************公钥加密、生成数字签名、数字签名校验、私钥解密********************");
        System.out.println("字符串：" + json);
        String encryptPublic = Base64Utils.encode(RSAUtils.encryptByPublicKey(json.getBytes(), publicKey));
        System.out.println("公钥加密后字符串：" + encryptPublic);

        //生成数字签名
        String signKey = RSAUtils.sign(Base64Utils.decode(encryptPublic), privateKey);
        System.out.println("公钥加密后数字签名：" + signKey);
        //校验数字签名
        boolean verify = RSAUtils.verify(Base64Utils.decode(encryptPublic), publicKey, signKey);
        System.out.println("公钥加密后数字签名校验：" + verify);

        //私钥解密
        System.out.println("私钥解密后字符串：" + new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(encryptPublic), privateKey)));

        System.out.println("********************私钥加密、公钥解密********************");
        //私钥加密
        System.out.println();
        System.out.println("字符串：" + json);
        String encryptPrivate = Base64Utils.encode(RSAUtils.encryptByPrivateKey(json.getBytes(), privateKey));
        System.out.println("私钥加密后字符串：" + encryptPrivate);
        //公钥解密
        System.out.println("公钥解密后字符串：" + new String(RSAUtils.decryptByPublicKey(Base64Utils.decode(encryptPrivate), publicKey)));

    }
}
