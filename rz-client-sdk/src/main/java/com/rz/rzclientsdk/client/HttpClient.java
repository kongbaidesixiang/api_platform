package com.rz.rzclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.rz.rzclientsdk.model.dto.User;
import com.rz.rzclientsdk.util.SignUtil;
import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class HttpClient {

    private String accessKey;
    private String secretKey;

    public HttpClient(){
    }

    public HttpClient(String accessKey, String secretKey){
        this.accessKey =accessKey;
        this.secretKey = secretKey;
    }

    public Map<String,String> getHeaderMap(String body) throws UnsupportedEncodingException {
        Map<String,String> header = new HashMap<>();
        header.put("accessKey",accessKey);
        header.put("sign", SignUtil.getSign(body,secretKey));
        header.put("body", URLEncoder.encode(body, StandardCharsets.UTF_8.name()));
        header.put("nonce", RandomUtil.randomNumbers(4));
        header.put("timestap",String.valueOf(System.currentTimeMillis()));
        return header;
    }

    public String setName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        String result = HttpUtil.get( "http://localhost:8080/api/user/",hashMap);
        System.out.println(result);
        return result;
    }

    public String getName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        String result = HttpUtil.post("http://localhost:8080/api/user/",hashMap);
        System.out.println(result);
        return result;
    }

    @SneakyThrows
    public String getnameByPostUserWithJSON(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8080/api/user/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String body = httpResponse.body();
        System.out.println("status:"+httpResponse.getStatus());
        System.out.println(body);
        return body;
    }
}
