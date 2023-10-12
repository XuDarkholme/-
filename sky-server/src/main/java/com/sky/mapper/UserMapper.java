package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据openid获取用户信息
     * @param openid
     * @return
     */
    @Select("select * from sky_take_out.user where openid = #{openid}")
    User selectByOpenid(String openid);



    /**
     * 插入用户信息
     * @param user
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into sky_take_out.user (openid, name, phone, sex, id_number, avatar, create_time) VALUES " +
            "(#{openid},#{name},#{phone},#{sex},#{idNumber},#{avatar},#{createTime})")
    void insert(User user);

    @Select("select * from sky_take_out.user where id = #{id}")
    User getById(Long userId);
}
