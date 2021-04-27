import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESTest {
    static final String ALGORITHM = "AES";
    /**
     * 生成key函数
     * @return 返回key
     */
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        //要生成什么加密方式的key,生成aes的实例
        KeyGenerator secretGenerator = KeyGenerator.getInstance(ALGORITHM);
        //随机数生成器 Random 每天可以生成864,000个
        SecureRandom secureRandom = new SecureRandom();
        //对key进行初始化
        secretGenerator.init(secureRandom);
        //生成key
        SecretKey secretKey = secretGenerator.generateKey();
        return secretKey;
    }
    final static String charsetName = "UTF-8";
    //使用UTF8,避免中文
    static Charset charset = Charset.forName(charsetName);
    /**
     * 加密函数
     * @param content 传入字符串
     * @param secretKey 加密key
     * @return 返回加密后的byte数组
     */
    public static byte[] encrypt(String content, SecretKey secretKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        //将content转为字节数组,模式为加密操作,密钥
        return aes(content.getBytes(charset),Cipher.ENCRYPT_MODE,secretKey);
    }

    /**
     * 解密函数
     * @param contentArray 加密后的数组
     * @param secretKey 解密key
     * @return 返回解密后的字符串
     */
    public static String decrypt(byte[] contentArray, SecretKey secretKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        //使用字节数组接收aes返回值
        byte[] result = aes(contentArray,Cipher.DECRYPT_MODE,secretKey);
        return new String(result,charsetName);
    }

    /**
     * aes函数
     * @param contentArray 字节数组
     * @param mode 加密/解密 选择
     * @param secretKey 密钥
     * @return
     */
    private static byte[] aes(byte[] contentArray, int mode, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //执行加解密操作需要Cipher对象, 指定类型为AES
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //对cipher进行初始化
        cipher.init(mode,secretKey);
        //数据小直接用doFinal,数据大时,循环,用update,再用doFinal
        byte[] result = cipher.doFinal(contentArray);
        return result;
    }
    public static void main(String[] args){
        String content = "你好中国中国你好";
        try {
            SecretKey secretKey = generateKey();
            byte[] encryptResult = encrypt(content,secretKey);
            System.out.println("加密后的结果为: "+new String(encryptResult,charsetName));

            String decryptResult = decrypt(encryptResult,secretKey);
            System.out.println("解密后的结果为: "+decryptResult);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
