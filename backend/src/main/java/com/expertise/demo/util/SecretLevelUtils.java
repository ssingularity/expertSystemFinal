package com.expertise.demo.util;

import lombok.Data;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Data
public class SecretLevelUtils {
    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("秘密", 0);
        map.put("一般", 1);
        map.put("机密", 2);
        map.put("重要", 3);
        map.put("绝密", 4);
        map.put("核心", 5);
    }

    public static boolean compare(String expertSecretLevel, String programSecretLevel) {
        Assert.isTrue(map.containsKey(expertSecretLevel), "密级不存在");
        Assert.isTrue(map.containsKey(programSecretLevel), "密级不存在");
        return map.get(expertSecretLevel) > map.get(programSecretLevel);
    }
}
