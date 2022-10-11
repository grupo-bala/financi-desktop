package grupobala.Crypt.Implementations;

import grupobala.Crypt.Encryptor.Encryptor;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BlowFishEncryptor implements Encryptor {

    static final String key = "grupobala";
    static final String algorithm = "Blowfish";

    public String encrypt(String input) throws Exception {
        byte[] keyData = key.getBytes("UTF-8");
        SecretKeySpec keySpec = new SecretKeySpec(keyData, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        return new String(encrypted);
    }
}
