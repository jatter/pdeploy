package com.xxg.jdeploy.mapper;

import com.xxg.jdeploy.domain.JavaWebParameterInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public interface JavaWebParameterMapper {
    @Select("select parameter_id,uuid,parameter_name,parameter_value,parameter_path,regular from parameter_management where uuid=#{uuid}")
    List<JavaWebParameterInfo> getList(String uuid);

    @Select("select uuid,parameter_id,parameter_name,parameter_value,parameter_path,regular from parameter_management where parameter_id=#{parameterId}")
    JavaWebParameterInfo getParameterByParameterId(String parameterId);

    @Update("update parameter_management set parameter_id=#{parameter_id},parameter_name=#{parameter_name},parameter_value=#{parameter_value},parameter_path=#{parameter_path},regular=#{regular}  where parameter_id=#{parameter_id}")
    void update(JavaWebParameterInfo javaWebParameterInfo);

    @Insert("insert into parameter_management (uuid,parameter_name,parameter_value,parameter_path,regular) values (#{uuid},#{parameter_name},#{parameter_value},#{parameter_path},#{regular})")
    void insert(JavaWebParameterInfo javaWebParameterInfo);

    @Delete("delete from parameter_management where parameter_id = #{parameterId}")
    void delete(String parameterId);

}
