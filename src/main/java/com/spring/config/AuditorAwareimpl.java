package com.spring.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareimpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        //Get username from spring security
        return Optional.of("Test user");
    }
}
