package grupobala.Crypt.Implementations;

import grupobala.Crypt.Encryptor.Encryptor;
import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA256Encryptor implements Encryptor {

    public String encrypt(String input) throws Exception {
        MessageDigest sha256digestor = MessageDigest.getInstance("SHA-256");
        byte[] hashData = sha256digestor.digest(input.getBytes("UTF-8"));
        BigInteger noHash = new BigInteger(1, hashData);

        return noHash.toString(16);
    }
}
