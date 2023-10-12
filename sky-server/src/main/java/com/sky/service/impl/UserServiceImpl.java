package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.AccountNotFoundException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    public static final String WEIXIN_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatProperties weChatProperties;

    @Override
    public User login(UserLoginDTO userLoginDTO) {

        //1、调用微信接口(使用HttpClient)，实现登录操作
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("appid", weChatProperties.getAppid());
        paraMap.put("secret", weChatProperties.getSecret());
        paraMap.put("js_code", userLoginDTO.getCode());
        paraMap.put("grant_type", "authorization_code");

        String result = HttpClientUtil.doGet(WEIXIN_LOGIN_URL, paraMap);
        log.info("微信登录完成，结果{}",result);
        if(!StringUtils.hasLength(result)){
            throw new AccountNotFoundException(MessageConstant.LOGIN_FAILED);
        }

        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.getString("openid");     //拿到微信用户唯一标识
        if(!StringUtils.hasLength(openid)){
            throw new AccountNotFoundException(MessageConstant.LOGIN_FAILED);
        }

        //2、如果用户是第一次访问小程序，则需要完成自动注册
        User user = userMapper.selectByOpenid(openid);
        if(user == null){
            user = User.builder().openid(openid).createTime(LocalDateTime.now()).build();
            userMapper.insert(user);
        }

        //3、返回
        return user;
    }
}
