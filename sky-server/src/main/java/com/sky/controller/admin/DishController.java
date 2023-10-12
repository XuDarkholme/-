package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品接口")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     * @return
     */
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品数据，{}",dishDTO);
        dishService.save(dishDTO);

        //清理缓存数据
        String key = "dish_" + dishDTO.getCategoryId();
        redisTemplate.delete(key);
        return Result.success();
    }

    /**
     * 分页条件查询
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO pageQueryDTO){
        log.info("分页条件查询，{}",pageQueryDTO);
        PageResult pageResult = dishService.page(pageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 分页条件查询
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除菜品{}",ids);
        dishService.delete(ids);

        //将所有的菜品缓存数据清理掉，所有以dish_开头的key
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);

        return Result.success();
    }

    /**
     * 根据ID查询，用于页面回显
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishVO> getInfo(@PathVariable Long id){
        log.info("根据ID查询，用于数据回显，{}",id);
        DishVO dishVO = dishService.getInfo(id);
        return Result.success(dishVO);
    }

    /**
     * 修改操作
     * @return
     */
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品，{}",dishDTO);
        dishService.update(dishDTO);

        //将所有的菜品缓存数据清理掉，所有以dish_开头的key
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);

        return Result.success();
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.selectBycategoryId(categoryId);
        return Result.success(list);
    }
}
