package com.car.rentservice.audited;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created By Shameera.A on 3/28/2020
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of("System");

    }
}
