package com.icis.backend.controller;

import com.icis.backend.entity.IcisRepairs;
import com.icis.backend.service.IcisRepairsServiceI;
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
@RequestMapping("/icisRepairs")
public class IcisRepairsController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisRepairsService接口自动注入
     */
    @Autowired
    private IcisRepairsServiceI icisRepairsServiceI;

    public void setIcisRepairsServiceI(IcisRepairsServiceI icisRepairsServiceI) {
        this.icisRepairsServiceI = icisRepairsServiceI;
    }

    /**
     * 报修功能
     * 请求：/icisRepairs/addIcisRepairsRecord.html
     * 请求类型
     * @param icisRepairs
     * @param files
     * @return 报修是否成功
     */
    @RequestMapping(value = "addIcisRepairsRecord", method = RequestMethod.POST)
    @ResponseBody
    public String addIcisRepairsRecord(IcisRepairs icisRepairs,@RequestParam("file") MultipartFile[] files) {
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                try {
                    //获取图片上传路径
                    String filePath = request.getSession().getServletContext().getRealPath("/").replaceAll("/target/ICIS/","") + "/src/main/webapp/images/repairs/" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                    if (i == 0) {
                        icisRepairs.setPhoto1(filePath);
                    } else if (i == 1) {
                        icisRepairs.setPhoto1(filePath);
                    } else if (i == 2) {
                        icisRepairs.setPhoto1(filePath);
                    } else if (i == 3) {
                        icisRepairs.setPhoto1(filePath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //设置报修时间
        Date time = new Date();
        icisRepairs.setTime(time);
        //设置报修状态
        icisRepairs.setIsComplete(0);
        //数据库中添加数据
        int addRepairsRecordState = this.icisRepairsServiceI.insert(icisRepairs);
        if (addRepairsRecordState == 0) {
            return "报修失败";
        } else {
            return "报修成功";
        }
    }

    /**
     * 筛选用户的报修列表
     * 请求类型：POST
     * 请求：/icisRepairs/selectMyIcisRepairs.html
     * @param residentId
     * @return 用户报修列表
     * @throws Exception
     */
    @RequestMapping(value = "selectMyIcisRepairs", method = RequestMethod.POST)
    @ResponseBody
    public List<IcisRepairs> selectMyIcisRepairs(Long residentId) throws Exception {
        List<IcisRepairs> icisRepairs = this.icisRepairsServiceI.selectMyIcisRepairs(residentId);
        //获取本机IP
//        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        String request = "/icisRepairs/getPhoto.html?filePath=";
        for (IcisRepairs icisRepair:icisRepairs) {
            if (icisRepair.getPhoto1() == null) {
                icisRepair.setPhoto1(null);
            } else {
                icisRepair.setPhoto1(request + icisRepair.getPhoto1());
            }
            if (icisRepair.getPhoto2() == null) {
                icisRepair.setPhoto2(null);
            } else {
                icisRepair.setPhoto2(request + icisRepair.getPhoto2());
            }
            if (icisRepair.getPhoto3() == null) {
                icisRepair.setPhoto3(null);
            } else {
                icisRepair.setPhoto3(request + icisRepair.getPhoto3());
            }
            if (icisRepair.getPhoto4() == null) {
                icisRepair.setPhoto4(null);
            } else {
                icisRepair.setPhoto4(request + icisRepair.getPhoto4());
            }
        }
        return icisRepairs;
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

    @RequestMapping(value = "completeRepairs", method = RequestMethod.POST)
    @ResponseBody
    public String completeRepairs(IcisRepairs icisRepairs) {
        icisRepairs.setIsComplete(1);
        int completeState = this.icisRepairsServiceI.completeRepairs(icisRepairs);
        if (completeState == 0) {
            return "报修完成失败";
        } else {
            return "报修完成成功";
        }
    }
}
