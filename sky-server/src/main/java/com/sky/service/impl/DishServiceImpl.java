package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Transactional
    @Override
    public void save(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        //1、保存菜品的基本信息
        dish.setStatus(StatusConstant.DISABLE);
        dishMapper.insert(dish);

        //2、保存菜品的口味信息
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors.forEach(dishFlavor -> {
            dishFlavor.setDishId(dish.getId());
        });
        dishFlavorMapper.insertBatch(flavors);

    }

    @Override
    public PageResult page(DishPageQueryDTO pageQueryDTO) {
        //1、设置分页参数
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());

        //2、执行查询
        List<DishVO> dishVOList = dishMapper.list(pageQueryDTO);

        //3、解析并封装分页结果
        Page<DishVO> page = (Page<DishVO>) dishVOList;
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Transactional
    @Override
    public void delete(List<Long> ids) {
        //1、判断菜品状态，起售中的菜品不能删除，提示错误信息
        Long count = dishMapper.countEnableDishByIds(ids);
        if(count > 0){  //这批菜品中包含了起售菜品
            throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
        }

        //2、判断菜品是否关联套餐，如果关联不能删除，提示错误信息
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if(!CollectionUtils.isEmpty(setmealIds)){  //这批菜品关联了套餐
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //3、执行删除操作，并删除菜品关联的口味
        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);

    }

    @Override
    public DishVO getInfo(Long id) {
        //1、根据ID查询菜品的基本信息
        Dish dish = dishMapper.getById(id);

        //2、根据ID查询菜品口味列表信息
        List<DishFlavor> flavorList = dishFlavorMapper.getById(id);

        //3、组装数据
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(flavorList);

        return dishVO;
    }

    @Transactional
    @Override
    public void update(DishDTO dishDTO) {
        //1、根据ID修改菜品的基本信息 -- dish
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);

        //2、根据ID修改菜品的口味信息（先删后加） -- dish_flavor
        //   删除
        dishFlavorMapper.deleteByDishIds(Collections.singletonList(dish.getId()));

        //   添加
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors.forEach(dishFlavor ->{
            dishFlavor.setDishId(dish.getId());
        });
        dishFlavorMapper.insertBatch(flavors);

    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> selectBycategoryId(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return dishMapper.selectBycategoryId(dish);
    }

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.selectBycategoryId(dish);

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getById(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }
        return dishVOList;
    }
}
