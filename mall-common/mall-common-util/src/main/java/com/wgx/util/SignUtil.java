package com.wgx.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *  生成签名工具类，用于防止篡改
 ******************************************
 * @author 肖文格 [2022/7/9 20:15]
 * @version 1.0.0
 ******************************************
 */
@Slf4j
public class SignUtil {

    private static final String secret = "qrewotbnodsfs";

    public static boolean checkSign(Map<String, Object> reqParam){
        if(StringUtils.isEmpty(reqParam)){
            return false;
        }
        String clietSign = (String)reqParam.get("sign");
        reqParam.remove("sign");
        // 生成签名 + 签名校验
        return generateSign(reqParam).equals(clietSign);
    }

    private static String generateSign(Map<String, Object> reqParam) {
        Map<String, Object> sortedMap = sortMapByKey(reqParam);
        // 参数排序
        StringBuilder decorateReqParam = new StringBuilder();
        // 参数组装（与前端约定好生成签名的逻辑，比如，设置参数以逗号隔开）
        sortedMap.forEach((k, v) -> {
            decorateReqParam.append(k).append(",").append(v);
        });
        decorateReqParam.append(secret);
        return MD5Util.md5(decorateReqParam.toString());
    }

    private static Map<String, Object> sortMapByKey(Map<String, Object> reqParam) {
        TreeMap<String, Object> sortedMap = new TreeMap<>(new MyComparator());
         sortedMap.putAll(reqParam);
         return sortedMap;
    }

    static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    // name,wgxage,18qrewotbnodsfs
    // age,18name,wgxqrewotbnodsfs
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("appId", "travel");
        map.put("appName", "travel");
        log.info("生成的签名为============>" + generateSign(map));
    }

}
