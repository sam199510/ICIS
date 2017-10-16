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

    //声明一个时间测试类对象
    TimeTest t;

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
     *        startTime（开始时间，格式要求："yyyy-MM-dd HH:mm:ss"）、
     *        finalTime（结束时间，格式要求："yyyy-MM-dd HH:mm:ss"）
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
        //从数据库中获取出所有的记录
        List<IcisActivity> allIcisActivity = this.icisActivityServiceI.seleceAllIcisActivity();
        //遍历记录
        for (IcisActivity icisActivityItem : allIcisActivity) {
            //去掉开始时间是空的对象
            if (icisActivityItem.getStartTime() != null) {
                //从数据库中获取的时间
                Calendar cal = Calendar.getInstance();
                cal.setTime(icisActivityItem.getStartTime());
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                //从前端页面中获取的开始时间
                Calendar calCmp = Calendar.getInstance();
                calCmp.setTime(startOfTime);
                int yearCmp = calCmp.get(Calendar.YEAR);
                int monthCmp = calCmp.get(Calendar.MONTH) + 1;
                int dayCmp = calCmp.get(Calendar.DAY_OF_MONTH);
                //比较两个时间
                if ((year == yearCmp)&&
                    (month == monthCmp)&&
                    (day == dayCmp)) {
                    return "社区只允许一天举办一个活动";
                }
            }
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
        int publishActvityState = 1;// this.icisActivityServiceI.publishActivity(icisActivity);
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
     * 一键开始时间监听
     * 请求：/icisActivity/oneBtnToStartTimeListener.html
     * 请求类型：POST
     * @return 一键监听结果
     */
    @RequestMapping(value = "oneBtnToStartTimeListener", method = RequestMethod.POST)
    @ResponseBody
    public String oneBtnToStartTimeListener(){
        //筛选当天的活动
        IcisActivity icisActivity = this.icisActivityServiceI.selectIcisActivityToday();
        //建立一个时间监听对象
        t = new TimeTest();
        //设置初始监听时间
        t.setStartTime(icisActivity.getAllowSignInStartTime());
        //设置结束监听时间
        t.setFinalTime(icisActivity.getAllowSignInFinalTime());
        //设置活动id
        t.setActivityId(icisActivity.getId());
        //设置时间监听器标志位
        t.setFlag(true);
        //启动时间监听器
        t.start();
        return "一键开始监听时间";
    }

    /**
     * 一键结束时间监听
     * 请求：/icisActivity/oneBtnToStopTimeListener.html
     * 请求类型：POST
     * @return 一键监听结果
     */
    @RequestMapping(value = "oneBtnToStopTimeListener", method = RequestMethod.POST)
    @ResponseBody
    public String oneBtnToStopTimeListener() {
        //设置标志位结束监听
        t.setFlag(false);
        return "一键结束监听时间";
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

    /**
     * 时间监听器内部类
     */
    class TimeTest extends Thread implements Runnable {
        //监听器标志位
        boolean flag;
        //活动状态
        int n;
        //监听开始时间
        Date startTime;
        //监听结束时间
        Date finalTime;
        //活动id
        Long activityId;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public void stopThread() {
            flag = false;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getFinalTime() {
            return finalTime;
        }

        public void setFinalTime(Date finalTime) {
            this.finalTime = finalTime;
        }

        public Long getActivityId() {
            return activityId;
        }

        public void setActivityId(Long activityId) {
            this.activityId = activityId;
        }

        @Override
        public void run() {
            while (flag) {
                //获取当前时间
                Date now = new Date();
                //判断是否处于该时间段内
                if ((now.after(startTime) || now.equals(startTime)) &&
                        (now.equals(finalTime) || now.before(finalTime))) {
                    n = 1;
                } else {
                    n = 0;
                }
                //修改允许签到状态
                IcisActivity icisActivity = new IcisActivity();
                icisActivity.setId(activityId);
                icisActivity.setState(n);
                //更新数据库状态
                icisActivityServiceI.updateByPrimaryKeySelective(icisActivity);
                try {
                    //每5秒监听一次
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
