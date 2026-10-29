package com.enterprise.ecommerce.controller;

import com.enterprise.ecommerce.model.ActivityLog;
import com.enterprise.ecommerce.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired(required = false)
    private ActivityLogRepository activityLogRepository;

    @GetMapping("/logs")
    public List<ActivityLog> getAuditLogs() {
        try {
            if (activityLogRepository != null) {
                return activityLogRepository.findTop50ByOrderByTimestampDesc();
            }
        } catch (Exception e) {
            System.err.println("[AdminController] MongoDB audit logs unavailable: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
