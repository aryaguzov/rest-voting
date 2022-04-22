package com.graduation.restvoting.util;

import com.graduation.restvoting.model.AbstractBaseEntity;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    // fake authorization, must refactor when Spring Security will be added
    public static int authUserId() {
        return id;
    }
}
