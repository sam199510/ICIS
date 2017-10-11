package com.icis.backend.controller;

import com.alibaba.fastjson.JSON;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
     *        title（活动标题）、
     *        position（活动举办地点）、
     *        content1（活动内容1）、content2（活动内容2）、content3（活动内容3）、
     *        startTime（开始时间）、finalTime（结束时间）
     * @param file
     *        其中参数包括file，传入的图片
     * @return 发布活动是否成功
     *         成功返回"发布活动成功"
     *         失败返回"发布活动失败"
     * @throws Exception
     */
    @RequestMapping(value = "publishActivity", method = RequestMethod.POST)
    @ResponseBody
    public String publishActivity(String startTime, String finalTime, IcisActivity icisActivity, @RequestParam("file")MultipartFile file) throws Exception{
        //设置活动的发布时间
        Date publishTime = new Date();
        icisActivity.setPublishTime(publishTime);
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        //转换始末时间
        Date startOfTime = null;
        Date finalOfTime = null;
        try {
            startOfTime = sdf.parse(startTime);
            finalOfTime = sdf.parse(finalTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //设置始末时间
        icisActivity.setStartTime(startOfTime);
        icisActivity.setFinalTime(finalOfTime);
        //设置活动时间
        String strStartOfTime = sdf2.format(startOfTime);
        String strFinalOfTime = sdf2.format(finalOfTime);
        String time = "活动开始时间 "+strStartOfTime+"\n活动结束时间 "+ strFinalOfTime;
        icisActivity.setTime(time);
        //设置允许签到的始末时间
        Calendar startAllCal = Calendar.getInstance();
        Calendar finalAllCal = Calendar.getInstance();
        startAllCal.setTime(startOfTime);
        finalAllCal.setTime(finalOfTime);
        startAllCal.add(Calendar.MINUTE, -30);
        finalAllCal.add(Calendar.MINUTE,30);
        Date startAllowOfTime;
        Date finalAllowOfTime;
        startAllowOfTime = startAllCal.getTime();
        finalAllowOfTime = finalAllCal.getTime();
        icisActivity.setAllowSignInStartTime(startAllowOfTime);
        icisActivity.setAllowSignInFinalTime(finalAllowOfTime);
        //设置状态为未举行
        icisActivity.setState(0);
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
     * 修改活动举行状态
     * 请求：/icisActivity/changeActivityState.html
     * 请求类型：POST
     * @param icisActivity
     *        需要传入的参数有：
     *        id（活动id）、state（活动举行状态）
     * @return 返回是否修改状态成功
     *         成功返回"状态修改成功"
     *         事变返回"状态修改失败"
     */
    @RequestMapping(value = "changeActivityState", method = RequestMethod.POST)
    @ResponseBody
    public String changeActivityState(IcisActivity icisActivity) {
        if (icisActivity.getState() == 0) {
            icisActivity.setState(1);
        } else {
            icisActivity.setState(0);
        }
        int changeStateValue = this.icisActivityServiceI.updateByPrimaryKeySelective(icisActivity);
        if (changeStateValue == 1) {
            return "状态修改成功";
        } else {
            return "状态修改失败";
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
