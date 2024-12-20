package com.rz.rzinterface.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rz.rzclientsdk.model.dto.User;
import com.rz.rzclientsdk.util.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String setNamebyGet(String name) {
        return "Get请求 " + name;
    }

    @PostMapping()
    public String getNamebyPost(@RequestParam(value = "name") String name) {
        return "post 请求"+ name;
    }

    @PostMapping("/user")
    public String getnameByPostUserWithJSON(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestap = request.getHeader("timestap");
        boolean hasBlank = StrUtil.hasBlank(accessKey,body, sign, nonce, timestap);
        // 判断是否为空
        if(hasBlank){
            return "无权限";
        }
        // TODO 使用accessKey去数据库查询secretKey
        // 假设查到的secret是rz 进行加密得到sign
        String secretKey = "rz";
        String userjson = JSONUtil.toJsonStr(user);
        String sign1 = SignUtil.getSign(userjson, secretKey);
        if(!StrUtil.equals(sign,sign1)){
            return "无权限";
        }
        // TODO 判断随机数nonce
        // 时间戳是否为数字
        if(!NumberUtil.isNumber(timestap)){
            return "无权限";
        }
        // 五分钟之内有效
        if(System.currentTimeMillis()-Long.parseLong(timestap) > 5 * 60 * 1000){
            return "无权限";
        }
        return "发送POST请求 JSON中你的名字是：" + user.getName();
    }
}
