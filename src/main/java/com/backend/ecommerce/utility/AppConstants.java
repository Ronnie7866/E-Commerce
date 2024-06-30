package com.backend.ecommerce.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AppConstants {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_BY = "productId";
    public static final String SORT_ORDER = "asc";
    public static final List<String> EXCEPTION_APIS = Arrays.asList(
            "/api/auth/**",
            "/api/inventory/**",
            "/forgotPassword/**",
            "/v2/api/docs/",
            "/swagger-resource/**",
            "/swagger-ui/**",
            "/webjars/**"
    );

}
