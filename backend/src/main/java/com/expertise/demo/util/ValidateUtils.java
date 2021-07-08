package com.expertise.demo.util;

import org.springframework.util.StringUtils;

public class ValidateUtils {
    public static boolean validate(String target, String[] validWords) {
        if (!StringUtils.hasText(target)) {
            return false;
        }
        for (String word: validWords) {
            if (target.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
