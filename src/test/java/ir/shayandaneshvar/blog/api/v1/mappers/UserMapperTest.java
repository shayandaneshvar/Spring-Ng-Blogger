package ir.shayandaneshvar.blog.api.v1.mappers;

import ir.shayandaneshvar.blog.api.v1.model.RegisterRequest;
import ir.shayandaneshvar.blog.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserMapperTest {
    private UserMapper mapper = UserMapper.INSTANCE;
    private final String USER = "User", PASS = "pwd", EMAIL = "abc@gmail.com";

    @Test
    public void testMappingToUser() {
        final RegisterRequest REQ = new RegisterRequest()
                .setUsername(USER)
                .setPassword(PASS)
                .setEmail(EMAIL);
        User user = mapper.requestToUser(REQ);
        Assertions.assertEquals(user.getUsername(),USER);
        Assertions.assertEquals(user.getPassword(),PASS);
        Assertions.assertEquals(user.getEmail(),EMAIL);
    }
}
