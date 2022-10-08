package grupobala.Crypt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import grupobala.Crypt.Implementations.BlowFishEncryptor;

public class TestBlowFishEncryptor {
    BlowFishEncryptor encryptor = new BlowFishEncryptor();

    @Test
    public void testEncryptHelloWorld() throws Exception {
        String input = "Hello World";
        String output = encryptor.encrypt(input);
        String expected = ";OW^���,���(�t"; //From online tool

        Assertions.assertEquals(expected, output);
    }
}
