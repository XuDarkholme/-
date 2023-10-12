package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量保存菜品口味信息
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 批量删除菜品口味信息
     * @param ids
     */
    void deleteByDishIds(List<Long> ids);

    /**
     * 根据菜品ID查询菜品口味列表数据
     * @param id
     */
    @Select("select * from sky_take_out.dish_flavor where dish_id = #{id}")
    List<DishFlavor> getById(Long id);
}
