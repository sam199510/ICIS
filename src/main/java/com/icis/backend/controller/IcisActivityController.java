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

/**
 * 智慧社区活动控制类
 */
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
    public List<IcisActivity> seleceAllIcisActivity() {
        //获取本机IP
//        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        List<IcisActivity> icisActivities = this.icisActivityServiceI.seleceAllIcisActivity();
        for (IcisActivity icisActivity : icisActivities) {
            if (icisActivity.getImage() == null) {
                icisActivity.setImage(null);
            } else {
                icisActivity.setImage("/icisActivity/getPhoto.html?filePath=" + icisActivity.getImage());
            }
        }
        return icisActivities;
    }

    /**
     * 发布活动
     * 请求：/icisActivity/publishActivity.html
     * 请求类型：POST
     * @param icisActivity
     *        其中活动参数包括：
     *        title（活动标题）、image（活动图片）、time（活动举办时间）、
     *        position（活动举办地点）、
     *        content1（活动内容1）、content2（活动内容2）、content3（活动内容3）
     * @param file
     *        其中参数包括file，传入的图片
     * @return 发布活动是否成功
     *         成功返回"发布活动成功"
     *         失败返回"发布活动失败"
     * @throws Exception
     */
    @RequestMapping(value = "publishActivity", method = RequestMethod.POST)
    @ResponseBody
    public String publishActivity(IcisActivity icisActivity, @RequestParam("file")MultipartFile file) throws Exception{
        //设置活动的发布时间
        Date publishTime = new Date();
        icisActivity.setPublishTime(publishTime);
        //文件上传
        if (file != null && !file.isEmpty()) {
            //获取图片上传路径
            String filePath = request.getSession().getServletContext().getRealPath("/").replaceAll("/target/ICIS/","") + "/src/main/webapp/images/activity/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            icisActivity.setImage(filePath);
        }
        //将发布的活动存入数据库
        int publishActvityState = this.icisActivityServiceI.publishActivity(icisActivity);
        //判断活动发布是否成功
        if (publishActvityState == 0) {
            return "发布活动失败";
        } else {
            return "发布活动成功";
        }
    }

    /**
     * 社区活动图片显示
     * 请求类型：GET
     * 请求：/icisResident/getPhoto.html
     * @param response
     * @param filePath
     *        其中filePath为文件在服务器上的路径
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
     * 以流形式返回社区活动图片
     * @param inputStream
     * @return 流形式返回社区活动图片
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
