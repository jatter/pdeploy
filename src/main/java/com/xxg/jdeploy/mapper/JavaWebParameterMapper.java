package com.xxg.jdeploy.mapper;

import com.xxg.jdeploy.domain.JavaWebParameterInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public interface JavaWebParameterMapper {
    @Select("select parameter_id,uuid,parameter_name,parameter_value,parameter_path,regular from parameter_management where uuid=#{uuid}")
    List<JavaWebParameterInfo> getList(String uuid);

    @Select("select uuid,parameter_id,parameter_name,parameter_value,parameter_path,regular from parameter_management where uuid=#{uuid}")
    JavaWebParameterInfo getParameter(String uuid);

    @Insert("insert into parameter_management (uuid,parameter_name,parameter_value,parameter_path,regular) values (#{uuid},#{parameter_name},#{parameter_value},#{parameter_path},#{regular})")
    void insert(JavaWebParameterInfo javaWebParameterInfo);
}
