package com.xxg.jdeploy.controller;


import com.xxg.jdeploy.domain.JavaWebParameterInfo;
import com.xxg.jdeploy.service.JavaWebParameterService;
import com.xxg.jdeploy.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xxg.jdeploy.util.FileUtil.getPatchedFileName;

/**
 * 线上环境的后台服务自动化部署
 * @author wucao
 */
@Controller
@RequestMapping("systemparam")
public class SystemParamController {

	@Autowired
	private JavaWebParameterService javaWebParameterService;
	/**
	 * 添加参数页面
	 */
	@RequestMapping(value = "new/{projectId}", method = RequestMethod.GET)
	public ModelAndView newParam(@PathVariable String projectId) {
		ModelAndView mv = new ModelAndView("systemparam/new");
		mv.addObject("projectId", projectId);
		return mv;
	}
	/**
	 * 删除参数
	 */
	@RequestMapping(value = "delete/{uuid}/{parameterId}", method = RequestMethod.GET)
	public String delete(@PathVariable String uuid, @PathVariable String parameterId) {
		javaWebParameterService.delete(parameterId);
		return "redirect:/systemparam/parameterManagement/" + uuid;
	}
	/**
	 * 添加参数请求
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(JavaWebParameterInfo javaWebParameterInfo) {
		javaWebParameterService.insert(javaWebParameterInfo);
		return "redirect:/systemparam/parameterManagement/" + javaWebParameterInfo.getUuid();
	}

	/**
	 * 参数修改页面跳转
	 */
	@RequestMapping(value = "changeParam/{projectId}/{parameterId}", method = RequestMethod.GET)
	public ModelAndView modify(@PathVariable String parameterId , @PathVariable String projectId) {
		JavaWebParameterInfo javaWebParameterInfo = javaWebParameterService.getParameterByParameterId(parameterId);
		ModelAndView mv = new ModelAndView("systemparam/changeParam");
		mv.addObject("changeParam", javaWebParameterInfo);
		mv.addObject("projectId", projectId);
		return mv;
	}

	/**
	 * 参数修改实现
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(JavaWebParameterInfo javaWebParameterInfo) {
		javaWebParameterService.update(javaWebParameterInfo);
		return "redirect:/systemparam/parameterManagement/" + javaWebParameterInfo.getUuid();
	}

	/**
	 * 同步
	 */
	@RequestMapping(value = "readFile/{parameterId}", method = RequestMethod.GET)
	public String readFile(@PathVariable String parameterId) {
		JavaWebParameterInfo javaWebParameterInfo = javaWebParameterService.getParameterByParameterId(parameterId);
		String path = javaWebParameterInfo.getParameter_path();
		String regular = javaWebParameterInfo.getRegular();
		String parameter_value = javaWebParameterInfo.getParameter_value();
		String encoding =  "";                       //文件编码
		String fileContentStr = "";                  //文件内容
		File outFile = new File(path);
		StringBuffer fileContent = new StringBuffer();
		try {
			fileContent = FileUtil.readTextFile(path,true);  //读取文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			encoding = FileUtil.getFileEncoding(path);      //获取文件编码格式
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileContentStr = fileContent.toString();

		//System.out.println("fileContentStr:"+fileContentStr);

		/**通过正则匹配相应内容*/
		String driver = fileContentStr;
		Pattern p = Pattern.compile(regular);      //正则表达式 commend by danielinbiti
		Matcher m = p.matcher(driver);
		String result = "";
		while (m.find()) {
			result = m.group(1);
		}
		driver = driver.replaceFirst(result, parameter_value);   //替换

		//System.out.println(driver);

		try {
			FileUtil.writeString(encoding,driver,outFile);      //写入
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/systemparam/parameterManagement/" + javaWebParameterInfo.getUuid();
	}
	/**
	 * 参数列表展示
	 */
	@RequestMapping(value = "parameterManagement/{projectId}", method = RequestMethod.GET)
	public ModelAndView parameterList(@PathVariable String projectId) {
		ModelAndView mv = new ModelAndView("systemparam/parameterManagement");
		mv.addObject("parameterList", javaWebParameterService.getList(projectId));
		mv.addObject("projectId", projectId);
		return mv;
	}


}
