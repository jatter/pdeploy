package com.xxg.jdeploy.controller;


import com.xxg.jdeploy.domain.JavaWebParameterInfo;
import com.xxg.jdeploy.service.JavaWebParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.UUID;

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
	 * 添加参数请求
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(JavaWebParameterInfo javaWebParameterInfo) {
		String uuid = UUID.randomUUID().toString();
		javaWebParameterInfo.setParameter_id(uuid);
		javaWebParameterService.insert(javaWebParameterInfo);
		return "redirect:/systemparam/parameterManagement/" + uuid;
	}

	/**
	 * 详情页面
	 */
	@RequestMapping(value = "detail/{uuid}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable String uuid) {
		ModelAndView mv = new ModelAndView("systemparam/detail");
		mv.addObject("detail", javaWebParameterService.getParameter(uuid));
		return mv;
	}
	/**
	 * 获取参数uuid
	 */
	/*@RequestMapping(value = "parameterManagement/{uuid}", method = RequestMethod.GET)
	public ModelAndView parameterManagement(@PathVariable String uuid) {
		ModelAndView mv = new ModelAndView("systemparam/parameterManagement");
		mv.addObject("parameterUuid", javaWebParameterService.getParameter(uuid));
		return mv;
	}*/
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
