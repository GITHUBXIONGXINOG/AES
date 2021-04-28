public class AESTester {

    static String key;

    static {
        try {
            key = AESUtils.getSecretKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        encryptFile();
        decryptFile();
//        test();
        long end = System.currentTimeMillis();
        System.err.println("耗时：" + (end-begin)/1000 + "秒");
    }

    static void encryptFile() throws Exception {
        String sourceFilePath = "C:\\Users\\96212\\Desktop\\1\\1.txt";
        String destFilePath = "C:\\Users\\96212\\Desktop\\1\\1_encrypted.txt";
        AESUtils.encryptFile(key, sourceFilePath, destFilePath);
    }

    static void decryptFile() throws Exception {
        String sourceFilePath = "C:\\Users\\96212\\Desktop\\1\\1_encrypted.txt";
        String destFilePath = "C:\\Users\\96212\\Desktop\\1\\1_decrypted.txt";
        key = "o4D34nBWVzXO2zUKpZh7xA==";
        AESUtils.decryptFile(key, sourceFilePath, destFilePath);
    }

    static void test() throws Exception {
        String source = "这是一行测试DES加密/解密的文字，你看完也等于没看，是不是啊？！";
        System.err.println("原文:\t" + source);
        byte[] inputData = source.getBytes();
        inputData = AESUtils.encrypt(inputData, key);
        System.err.println("加密后:\t" + Base64Utils.encode(inputData));
        byte[] outputData = AESUtils.decrypt(inputData, key);
        String outputStr = new String(outputData);
        System.err.println("解密后:\t" + outputStr);
    }

}