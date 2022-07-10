package com.wgx.mall.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
public class HttpParamsUtil {

    public static SortedMap<String, Object> getAllParams(HttpServletRequest httpServletRequest) throws IOException {
        // 获取url后面的参数
        Map<String, Object> urlParams = getUrlParams(httpServletRequest);
        // 获取body的参数
        Map<String, Object> BodyParams = getBodyParams(httpServletRequest);
        // 排序
        SortedMap<String, Object> totalParams = sortedReqParam(urlParams, BodyParams);
        // 返回
        return totalParams;
    }

    private static SortedMap<String, Object> sortedReqParam(Map<String, Object> urlParams, Map<String, Object> bodyParams) {
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        if(!StringUtils.isEmpty(urlParams)){
            urlParams.forEach((k, v) -> {
                sortedMap.put(k, v);
            });
        }
        if(!StringUtils.isEmpty(bodyParams)){
            bodyParams.forEach((k, v) -> {
                sortedMap.put(k, v);
            });
        }
        return sortedMap;
    }


    private static Map<String, Object> getBodyParams(HttpServletRequest httpServletRequest) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
        String line = null;
        StringBuilder bodyBuilder = new StringBuilder();
        while(null != (line = bufferedReader.readLine())){
            bodyBuilder.append(line);
        }
        return JSONObject.parseObject(bodyBuilder.toString(), Map.class);
    }

    private static HashMap<String,Object> getUrlParams(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        HashMap<String, Object> reqParamMap = new HashMap<>();
         /** get请求中可能参数是中文啥的，这个时候会用特殊符号代替, 常见的诸如 http 协议的 url 就是纯文本的，不能直接放二进制流
         所以要解码
         localhost:xx/a=b&b=c;  getQueryString的结果 就是 a = b & b = c **/
        if(StringUtils.isEmpty(httpServletRequest.getQueryString())){
            return null;
        }
        String decodeParams = URLDecoder.decode(httpServletRequest.getQueryString(), "utf-8");
        String[] spilitReqParam = decodeParams.split("&");
        for(String s : spilitReqParam){
            int equalsIndex = s.indexOf("=");
            String key = s.substring(0, equalsIndex);
            String value = s.substring(equalsIndex + 1);
            reqParamMap.put(key, value);
        }
        return reqParamMap;
    }
}
