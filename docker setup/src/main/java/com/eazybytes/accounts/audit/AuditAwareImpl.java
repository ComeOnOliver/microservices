package com.eazybytes.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditorAwareImpl")
public class AuditAwareImpl implements AuditorAware<String>{
    
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Admin");
    }
}
