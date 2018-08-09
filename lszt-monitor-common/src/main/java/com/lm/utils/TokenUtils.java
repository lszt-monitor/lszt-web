package com.lm.utils;

import org.apache.commons.lang.StringUtils;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {


    /**
     * 校验生成token
     *
     * @param url
     * @param par
     * @param secret
     * @return
     */
    public static String getToken(String url, Map<String, Object> par, String secret) {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(secret) || par == null) {
            return null;
        }
        if (!url.equals("xxxx") || !secret.equals("xxx") ||
                !par.get("appid").equals("aa") || !par.get("uuid").equals("zzz")) {
            return null;
        }
        //拿到时间戳去判断接口请求时间生成新的token去访问接口
        Long time = (Long) par.get("time");
        System.out.println(time);
        String newUrl = url + par + secret;
        //返回32位TOKEN
        String md5Url = MD5Util.MD5(newUrl);
        return md5Url;
    }


    public static void main(String[] arges) {
        String Secret = "xxx";
        Map<String, Object> parm = new HashMap<>();
        parm.put("appid", "aa");
        parm.put("uuid", "zzz");
        parm.put("time", System.currentTimeMillis());//时间戳
        String tolken = TokenUtils.getToken("xxxx", parm, Secret);
        if (StringUtils.isEmpty(tolken)) {
            System.out.println("null");
        } else {
            System.out.println(tolken);
        }
    }
}
