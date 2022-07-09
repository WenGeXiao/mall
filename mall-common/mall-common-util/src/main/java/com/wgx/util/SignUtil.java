package com.wgx.util;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 *  生成签名工具类，用于防止篡改
 ******************************************
 * @author 肖文格 [2022/7/9 20:15]
 * @version 1.0.0
 ******************************************
 */
public class SignUtil {

    private static final String secret = "qrewotbnodsfs";

    public static boolean checkSign(Map<String, Object> reqParam, String targetSign){
        if(StringUtils.isEmpty(reqParam) || StringUtils.isEmpty(targetSign)){
            return false;
        }
        sortReqParam(reqParam);
        // 参数排序
        StringBuilder decorateReqParam = new StringBuilder();
        // 参数组装（与前端约定好生成签名的逻辑，比如，设置参数以逗号隔开）
        reqParam.forEach((k, v) -> {
            decorateReqParam.append(k).append(",").append(v);
        });
        decorateReqParam.append(decorateReqParam);
        // 生成签名 + 签名校验
        return MD5Util.md5(decorateReqParam.toString()).equals(targetSign);
    }

    private static void sortReqParam(Map<String, Object> reqParam) {
        Map<String, Object> sortedMap = new TreeMap<String, Object>((source, target)->{
             return source.compareTo(target);
    });
        sortedMap.putAll(reqParam);
    }

}
