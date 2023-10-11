import lombok.SneakyThrows;
import org.adbs.vtlabs.lab2new.model.service.User;
import org.adbs.vtlabs.lab2new.service.AuthorityService;
import org.adbs.vtlabs.lab2new.service.UserService;
import org.junit.jupiter.api.Test;

public class SomeTest {

    @Test
    @SneakyThrows
    void test1() {
        UserService userService = UserService.getInstance();
        User user = userService.registerUser("username1", "pass");
        System.out.println(user);
    }

    @Test
    @SneakyThrows
    void jwtTest() {
        AuthorityService authorityService = AuthorityService.getInstance();
        String token = authorityService.generateUserJwt(new User()
                .setUserId(123L)
                .setUsername("rebel")
        );
        System.out.println(token);
    }

    enum SomeEnum {
        VALUE1, VALUE2
    }
}
