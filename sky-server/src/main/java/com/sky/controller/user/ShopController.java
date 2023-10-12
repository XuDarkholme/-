package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("adminShopController")
@RequestMapping("/user/shop")
@Api(tags = "C端-店铺操作接口")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer)redisTemplate.opsForValue().get(KEY);

        // 使用 Optional 进行安全的拆箱
        String statusDescription = Optional.ofNullable(status)
                .map(s -> s == 1 ? "营业中" : "打样中")
                .orElse("未知状态");

        log.info("获取到店铺的营业状态为：{}", statusDescription);
        return Result.success(status);
    }
}
