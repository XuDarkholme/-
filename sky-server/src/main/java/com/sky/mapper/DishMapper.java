package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from sky_take_out.dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 保存菜品基本信息
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into sky_take_out.dish (name,category_id,price,image,description,status,create_time,update_time,create_user,update_user) VALUES " +
            "(#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Dish dish);

    /**
     * 条件查询
     * @param pageQueryDTO
     * @return
     */
    List<DishVO> list(DishPageQueryDTO pageQueryDTO);

    List<Dish> selectBycategoryId(Dish dish);

    /**
     * 根据ID统计起售菜品的数量
     * @param ids
     * @return
     */
    long countEnableDishByIds(List<Long> ids);

    /**
     * 批量删除菜品数据
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根据ID查询菜品数据
     * @param id
     */
    @Select("select * from sky_take_out.dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 动态修改菜品信息
     * @param dish
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据套餐id查询菜品
     * @param setmealId
     * @return
     */
    @Select("select a.* from sky_take_out.dish a left join sky_take_out.setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long setmealId);

    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
