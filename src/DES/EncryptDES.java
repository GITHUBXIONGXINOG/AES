package DES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptDES {
    public static void main(String[] args) {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            //初始化加密
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] text = "DES加密".getBytes();
            System.out.println("原始数据字节数组: "+text);
            System.out.println("原始字符串: "+new String(text));

            //进行转换
            byte[] textEnc = cipher.doFinal(text);
            System.out.println("DES加密后的字节数组: "+textEnc);
            System.out.println("DES加密后的字符串: "+new String(textEnc));

            //初始化界面
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            //进行转换
            byte[] textDec = cipher.doFinal(textEnc);
            System.out.println("DES解密后的字节数组: "+textDec);
            System.out.println("DES界面后的字符串: "+new String(textDec));

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
