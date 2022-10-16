package grupobala.Entities.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import grupobala.Entities.User.IUser.IUser;

public class TestUser {

    @Test
    public void testUserEmptyConstructor() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            new User();
        });
    }
    
    @Test
    public void testUserClose() {
        IUser user = new User("financi", "Financi", 0, 0);
        user.close();

        Assertions.assertThrows(RuntimeException.class, () -> {
            new User();
        });
    }
}
