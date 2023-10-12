package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面，实现公共字段自动填充处理逻辑
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    //前置通知，在通知中进行公共字段的赋值
    @Before("execution(* com.sky.mapper.*.*(..)) && @annotation(autoFill)")     //这里将切入点和通知写在了一起
    public void autoFill(JoinPoint joinPoint , AutoFill autoFill) throws Exception {    //此处的autoFill与下面的autoFill一致
        log.info("进入AOP程序");

        //获取到当前被拦截的方法的参数--实体对象（例如category、employee）
        Object[] args = joinPoint.getArgs();    //这里会将获取的参数放在一个数组里
        if(ObjectUtils.isEmpty(args)){          //某些方法不会传入参数，例如select、delete，此时直接return，不执行后续代码
            return;
        }
        Object obj = args[0];       //由于只传入一个参数，所以取args数组中下标为0的元素即为目标

        log.info("开始为公共属性赋值，赋值前：{}", obj);

        //通过反射获取对象的set方法（4个）
        Method setCreateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
        Method setUpdateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
        Method setCreateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
        Method setUpdateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

        //获取到当前被拦截的方法上的数据库操作类型（value属性）
        OperationType operationType = autoFill.value();

        //判断，如果是insert，为4个属性赋值；如果是update，为2个属性赋值
        if(operationType.equals(OperationType.INSERT)){
            setCreateTime.invoke(obj,LocalDateTime.now());
            setCreateUser.invoke(obj, BaseContext.getCurrentId());
        }
        setUpdateTime.invoke(obj,LocalDateTime.now());
        setUpdateUser.invoke(obj, BaseContext.getCurrentId());

        log.info("开始为公共属性赋值，赋值后：{}", obj);
    }
}
