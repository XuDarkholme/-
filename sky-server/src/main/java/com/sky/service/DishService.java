package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    void save(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO pageQueryDTO);

    void delete(List<Long> ids);

    DishVO getInfo(Long id);

    void update(DishDTO dishDTO);

    List<Dish> selectBycategoryId(Long categoryId);

    List<DishVO> listWithFlavor(Dish dish);
}
