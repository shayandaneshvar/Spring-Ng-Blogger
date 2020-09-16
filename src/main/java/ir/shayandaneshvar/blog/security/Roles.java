package ir.shayandaneshvar.blog.security;

import java.util.Arrays;
import java.util.List;

public enum Roles {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
    String value;

    Roles(String value) {
        this.value = value;
    }

    static List<String> getRoles() {
        return Arrays.asList(ADMIN.value, USER.value);
    }
}
