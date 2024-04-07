package de.leuphana.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class WebSecurityConfigurationTest {

    @Autowired WebSecurityConfiguration webSecurityConfiguration;


    @Test
    public void canPasswordBeEncoded() {
        String password = "MyPassword";
        PasswordEncoder passwordEncoder = webSecurityConfiguration.passwordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertTrue(passwordEncoder.matches(password, encodedPassword));
    }
}
