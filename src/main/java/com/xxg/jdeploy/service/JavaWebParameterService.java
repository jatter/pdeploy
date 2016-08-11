package com.xxg.jdeploy.service;

import com.xxg.jdeploy.domain.JavaWebDeployInfo;
import com.xxg.jdeploy.domain.JavaWebParameterInfo;
import com.xxg.jdeploy.mapper.JavaWebParameterMapper;
import com.xxg.jdeploy.util.ShellUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */

@Service
public class JavaWebParameterService {
    @Autowired
    private JavaWebParameterMapper javaWebParameterMapper;

    @Value("${shell.javawebdeploy}")
    private String shellFileFolder;

    @Value("${javawebdeploy.basepath}")
    private String basePath;

    @Value("${javawebdeploy.jettypath}")
    private String jettyPath;

    public  List<JavaWebParameterInfo> getList(String uuid) {
        return javaWebParameterMapper.getList(uuid);
    }

    public JavaWebParameterInfo getParameterByParameterId(String parameterId) {
        return javaWebParameterMapper.getParameterByParameterId(parameterId);
    }

    public void insert(JavaWebParameterInfo javaWebParameterInfo) {
        javaWebParameterMapper.insert(javaWebParameterInfo);
    }

    public void update(JavaWebParameterInfo javaWebParameterInfo) {
        javaWebParameterMapper.update(javaWebParameterInfo);
    }

    public void delete(String parameterId) {
        javaWebParameterMapper.delete(parameterId);
    }



}
