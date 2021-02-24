package com.luxondata.test.util;

import com.luxondata.test.vo.CUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHolderUtil {

    public static CUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CUserDetails) authentication.getPrincipal();
    }
}
