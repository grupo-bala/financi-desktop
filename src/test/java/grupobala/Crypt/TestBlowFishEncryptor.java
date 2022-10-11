package grupobala.Crypt;

import grupobala.Crypt.Implementations.BlowFishEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
