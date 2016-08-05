package com.xxg.jdeploy.service;

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

//    public List<JavaWebParameterInfo> getList() {
//        return javaWebParameterMapper.getList();
//    }

    public List<JavaWebParameterInfo> getParameter(String uuid) {
        return javaWebParameterMapper.getParameter(uuid);
    }

    public void insert(JavaWebParameterInfo javaWebParameterInfo) {
        javaWebParameterMapper.insert(javaWebParameterInfo);
    }


}
