package com.xxg.jdeploy.mapper;

import com.xxg.jdeploy.domain.JavaWebParameterInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public interface JavaWebParameterMapper {
   /* @Select("select * from parameter_management")
    List<JavaWebParameterInfo> getList();*/

    @Select("select parameter_id,parameter_name,parameter_value,parameter_path,regular from parameter_management where uuid=#{uuid}")
    List<JavaWebParameterInfo> getParameter(String uuid);

    @Insert("insert into parameter_management (parameter_name,parameter_value,parameter_path) values (#{parameter_name},#{parameter_value},#{parameter_path})")
    void insert(JavaWebParameterInfo javaWebParameterInfo);
}
