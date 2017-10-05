package com.icis.backend.controller;

import com.icis.backend.entity.IcisActivity;
import com.icis.backend.service.IcisActivityServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/icisActivity")
public class IcisActivityController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisActivityService接口自动注入
     */
    @Autowired
    private IcisActivityServiceI icisActivityServiceI;

    public void setIcisActivityServiceI(IcisActivityServiceI icisActivityServiceI) {
        this.icisActivityServiceI = icisActivityServiceI;
    }

    /**
     * 获取所有社区活动
     * 请求：/icisActivity/seleceAllIcisActivity.html
     * 请求类型：POST
     * @return 社区活动列表
     * @throws Exception
     */
    @RequestMapping(value = "seleceAllIcisActivity", method = RequestMethod.POST)
    @ResponseBody
    public List<IcisActivity> seleceAllIcisActivity() throws Exception {
        //获取本机IP
        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        List<IcisActivity> icisActivities = this.icisActivityServiceI.seleceAllIcisActivity();
        for (IcisActivity icisActivity : icisActivities) {
            if (icisActivity.getImage() == null) {
                icisActivity.setImage(null);
            } else {
                icisActivity.setImage("http://" + ipAddress + ":8080/icisActivity/getPhoto.html?filePath=" + icisActivity.getImage());
            }
        }
        return icisActivities;
    }

    /**
     * 发布活动
     * 请求：/icisActivity/publishActivity.html
     * 请求类型：POST
     * @param icisActivity
     * @param file
     * @return 发布活动是否成功
     * @throws Exception
     */
    @RequestMapping(value = "publishActivity", method = RequestMethod.POST)
    @ResponseBody
    public String publishActivity(IcisActivity icisActivity, @RequestParam("file")MultipartFile file) throws Exception{
        Date publishTime = new Date();
        icisActivity.setPublishTime(publishTime);
        if (file != null && !file.isEmpty()) {
            //获取图片上传路径
            String filePath = request.getSession().getServletContext().getRealPath("/").replaceAll("/target/ICIS/","") + "/src/main/webapp/images/activity/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            icisActivity.setImage(filePath);
        }
        int publishActvityState = this.icisActivityServiceI.publishActivity(icisActivity);
        if (publishActvityState == 0) {
            return "发布活动失败";
        } else {
            return "发布活动成功";
        }
    }

    /**
     * 头像设置链接显示头像
     * 请求类型：GET
     * 请求：/icisResident/getPhoto.html
     * @param response
     * @param filePath
     * @throws Exception
     */
    @RequestMapping(value = "getPhoto", method = RequestMethod.GET)
    public void getPhoto(HttpServletResponse response, String filePath) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        File file = new File(filePath);
        FileInputStream fips = new FileInputStream(file);
        byte[] byImg = readStream(fips);
        outputStream.write(byImg);
        outputStream.flush();
    }

    /**
     * 以流形式返回用户头像
     * @param inputStream
     * @return 流形式返回用户头像
     */
    public byte[] readStream(InputStream inputStream){
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int data = -1;
        try {
            while ((data = inputStream.read()) != -1) {
                bops.write(data);
            }
            return bops.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
