package grupobala.Entities.User;

import grupobala.Entities.User.IUser.IUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUser {

    @Test
    public void testUserEmptyConstructor() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> {
                new User();
            }
        );
    }

    @Test
    public void testUserClose() {
        IUser user = new User("financi", "Financi", 0, 0);
        user.close();

        Assertions.assertThrows(
            RuntimeException.class,
            () -> {
                new User();
            }
        );
    }
}
