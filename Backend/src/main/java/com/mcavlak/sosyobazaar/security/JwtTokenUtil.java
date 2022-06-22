package com.mcavlak.sosyobazaar.security;

import com.mcavlak.sosyobazaar.models.CustomUserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Component
@Transactional
public class JwtTokenUtil {


    public static final long DAY_MILLIS = 24 * 60 * 60 * 1000;

    public static String KEY_ID = "id";
    public static String KEY_USERNAME = "un";
    public static String KEY_UPDATED = "uts";
    public static String KEY_ROLE = "role";

    public String createToken(CustomUserDetails customUserDetails, boolean rememberMe) {

        JwtToken.Builder builder = new JwtToken.Builder();
        builder.put(KEY_ID, customUserDetails.getUser().getId());
        builder.put(KEY_USERNAME, customUserDetails.getUser().getUsername());
        builder.put(KEY_ROLE, customUserDetails.getUser().getRole().toString());

        long uts = Timestamp.valueOf(customUserDetails.getUser().getUpdatedDateTime()).getTime();
        builder.put(KEY_UPDATED, uts);
        long numDays = rememberMe ? 14 : 1;
        builder.expiration(new Date(System.currentTimeMillis() + DAY_MILLIS * numDays));
        return builder.build();
    }

    public String generate(CustomUserDetails customUserDetails, boolean rememberMe) {

        return createToken(customUserDetails, rememberMe);
    }

    public boolean isValid(JwtToken jwtToken, CustomUserDetails customUserDetails) {

        //expired check
        if (!jwtToken.isNoneExpired()) {
            return false;
        }

        //id check
        Long id = jwtToken.getClaims().get(KEY_ID, Long.class);
        if (!Objects.equals(customUserDetails.getUser().getId(), id)) {
            return false;
        }

        //updated timestamp check
        Long utsToken = jwtToken.getClaims().get(KEY_UPDATED, Long.class);
        Long uts = Timestamp.valueOf(customUserDetails.getUser().getUpdatedDateTime()).getTime();
        if (!uts.equals(utsToken)) {
            return false;
        }

        //username check
        String un = jwtToken.getClaims().get(KEY_USERNAME, String.class);
        if (!un.equals(customUserDetails.getUsername())) {
            return false;
        }

        //role check
        String role = jwtToken.getClaims().get(KEY_ROLE, String.class);
        return role.equals(customUserDetails.getRole().toString());

    }


}