package edu.vt.cs3704.example.testconfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import edu.vt.cs3704.example.services.CurrentUserService;
import edu.vt.cs3704.example.services.GrantedAuthoritiesService;

@TestConfiguration
public class TestConfig {

    @Bean
    public CurrentUserService currentUserService() {
        return new MockCurrentUserServiceImpl();
    }

    @Bean
    public GrantedAuthoritiesService grantedAuthoritiesService() {
        return new GrantedAuthoritiesService();
    }
}
