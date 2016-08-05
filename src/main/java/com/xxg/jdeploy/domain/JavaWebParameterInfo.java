package com.xxg.jdeploy.domain;

/**
 * Created by Administrator on 2016/8/4.
 */
public class JavaWebParameterInfo {

    private String uuid;
    private String parameter_id;
    private String parameter_name;
    private String parameter_value;
    private String parameter_path;
    private String regular;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParameter_id(){
        return parameter_id;
    }

    public void setParameter_id(String parameter_id) {
        this.parameter_id = parameter_id;
    }

    public String getParameter_name(){
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }

    public String getParameter_value(){
        return parameter_value;
    }

    public void setParameter_value(String parameter_value) {
        this.parameter_value = parameter_value;
    }

    public String getParameter_path(){
        return parameter_path;
    }

    public void setParameter_path(String parameter_path) {
        this.parameter_path = parameter_path;
    }

    public String getRegular(){
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
