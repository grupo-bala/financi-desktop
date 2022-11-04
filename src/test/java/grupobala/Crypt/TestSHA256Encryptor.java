package grupobala.Crypt;

import grupobala.Crypt.Implementations.SHA256Encryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSHA256Encryptor {

    @Test
    public void testEncryptHelloWorld() throws Exception {
        SHA256Encryptor encryptor = new SHA256Encryptor();
        String input = "Hello World";
        String actual = encryptor.encrypt(input);
        String expected =
            "a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e";

        Assertions.assertEquals(expected, actual);
    }
}
