package com.icis.backend.controller;

import com.icis.backend.entity.IcisFeedback;
import com.icis.backend.service.IcisFeedbackServiceI;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/icisFeedback")
public class IcisFeedbackController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IcisFeedbackServiceI icisFeedbackServiceI;

    public void setIcisFeedbackServiceI(IcisFeedbackServiceI icisFeedbackServiceI) {
        this.icisFeedbackServiceI = icisFeedbackServiceI;
    }

    /**
     * 意见反馈
     * 请求：/icisFeedback/addFeedbackRecord.html
     * 请求类型：POST
     * @param icisFeedback
     * @param files
     * @return
     */
    @RequestMapping(value = "addFeedbackRecord", method = RequestMethod.POST)
    @ResponseBody
    public String addFeedbackRecord(IcisFeedback icisFeedback, @RequestParam("file")MultipartFile[] files) {
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                try {
                    //获取图片上传路径
                    String filePath = request.getSession().getServletContext().getRealPath("/").replaceAll("/target/ICIS/","") + "/src/main/webapp/images/feedback/" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                    if (i == 0) {
                        icisFeedback.setPhoto1(filePath);
                    } else if (i == 1) {
                        icisFeedback.setPhoto2(filePath);
                    } else if (i == 2) {
                        icisFeedback.setPhoto3(filePath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //获取上传时间
        Date time = new Date();
        icisFeedback.setTime(time);
        //将反馈记录存入数据库
        int addFeedbackRecordState = this.icisFeedbackServiceI.insert(icisFeedback);
        //判断反馈是否成功
        if (addFeedbackRecordState == 1) {
            return "反馈成功";
        } else {
            return "反馈失败";
        }
    }

    /**
     * 获取所有反馈
     * 请求：/icisFeedback/selectAllFeedbackRecord.html
     * 请求类型：POST
     * @return
     */
    @RequestMapping(value = "selectAllFeecbackRecord", method = RequestMethod.POST)
    @ResponseBody
    public List<com.icis.backend.model.IcisFeedback> selectAllFeedbackRecord() {
        //建立一个反馈数组对象
        List<com.icis.backend.model.IcisFeedback> icisFeedbackModels = new ArrayList<com.icis.backend.model.IcisFeedback>();
        //获取反馈的列表
        List<IcisFeedback> icisFeedbacks = this.icisFeedbackServiceI.selectAllFeedbackRecord();
        try {
            //获取本机IP
//            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String request = "/icisFeedback/getPhoto.html?filePath=";
            //训话遍历列表
            for (IcisFeedback icisFeedback: icisFeedbacks) {
                //设置各种属性
                com.icis.backend.model.IcisFeedback icisFeedbackItem = new com.icis.backend.model.IcisFeedback();
                icisFeedbackItem.setContent(icisFeedback.getContent());
                icisFeedbackItem.setId(icisFeedback.getId());
                icisFeedbackItem.setTime(icisFeedback.getTime());
                if (icisFeedback.getPhoto1() != null) {
                    icisFeedbackItem.setPhoto1(request + icisFeedback.getPhoto1());
                } else {
                    icisFeedbackItem.setPhoto1(null);
                }
                if (icisFeedback.getPhoto2() != null) {
                    icisFeedbackItem.setPhoto2(request + icisFeedback.getPhoto2());
                } else {
                    icisFeedbackItem.setPhoto2(null);
                }
                if (icisFeedback.getPhoto3() != null) {
                    icisFeedbackItem.setPhoto3(request + icisFeedback.getPhoto3());
                } else {
                    icisFeedbackItem.setPhoto3(null);
                }
                //向数组中添加模型
                icisFeedbackModels.add(icisFeedbackItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回模型
        return icisFeedbackModels;
    }

    /**
     * 请求反馈图片获取
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
     * 以流形式返回用户反馈
     * @param inputStream
     * @return 流形式返回用户反馈
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
