package com.wgx.mall.filter;

import com.alibaba.fastjson.JSONObject;
import com.wgx.mall.util.HttpParamsUtil;
import com.wgx.util.SignUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebFilter
public class SinFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 过滤器从输入流中获取了数据过后，无法重新获取输入流的数据，所以需要装饰request
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        Map<String, Object> sortedMap = HttpParamsUtil.getAllParams(httpServletRequest);
        // 校验
        if(SignUtil.checkSign(sortedMap)){
            chain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject param = new JSONObject();
            param.put("code",-1);
            param.put("message", "签名错了");
            writer.append(param.toJSONString());
        }
    }

    @Override
    public void destroy() {

    }
}
