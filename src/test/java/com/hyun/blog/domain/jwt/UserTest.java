package com.hyun.blog.domain.jwt;

import com.hyun.blog.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest {

    @Test
    void expect_user_has_protected_no_args_constructor() {
        class ChildUser extends User {
            public ChildUser() {
                super();
            }
        }

        ChildUser childUser = new ChildUser();
        assertThat(childUser).isInstanceOf(User.class);
    }
}
