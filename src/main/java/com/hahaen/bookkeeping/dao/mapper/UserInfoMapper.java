package com.hahaen.bookkeeping.dao.mapper;

import com.hahaen.bookkeeping.model.persistence.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    @Select("SELECT id, username, password, create_time, update_time FROM hcas_userinfo WHERE id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Long id);

}
