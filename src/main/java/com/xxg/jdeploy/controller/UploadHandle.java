package com.xxg.jdeploy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * UploadHandle
 *
 * @author zangqisong
 * @date 2016/8/11
 */
@Controller
@RequestMapping("upload")
public class UploadHandle {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "uploadId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<byte[]> idIdentification(@RequestParam("file") MultipartFile multipartfile) throws IOException {
        System.out.println("getOriginalFilename:"+multipartfile.getOriginalFilename());
        System.out.println("getName:"+multipartfile.getName());
        System.out.println("type:" + multipartfile.getContentType());
        String type = multipartfile.getName().split(".")[1];
        System.out.println("typeStr:" + type);
        if (!"zip".equals(type)) {
            return null;
        }
        // 设置格式,图片
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        Date nowDate = new Date();//时间
        int month = nowDate.getMonth()+1;
        int date = nowDate.getDate();
        int hours = nowDate.getHours();
        int minutes = nowDate.getMinutes();

        String filePath;//文件路径
        filePath = System.getProperty("upload.path") + File.separator + "upload"
                 + File.separator + ""+ month + "-"+ date + "-"+ hours + File.separator  ;
        File file = new File(filePath);
        file.mkdirs();
        System.out.println("filePath:" + filePath);
        //保存文件到临时目录
        // String savePath = request.getSession().getServletContext().getRealPath("/")
        String savePath = filePath + multipartfile.getOriginalFilename();

        File saveFile = new File(savePath);

        multipartfile.transferTo(saveFile);
        //页面显示用户上传的图片
        return new ResponseEntity<byte[]>(multipartfile.getBytes(), headers, HttpStatus.OK);//new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(saveFile), headers, HttpStatus.OK);
    }
}
