package com.icis.backend.controller;

import com.icis.backend.entity.IcisResident;
import com.icis.backend.service.IcisResidentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.util.List;

@Controller
@RequestMapping("/icisResident")
public class IcisResidentController {
    /**
     * 将HTTPServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将ICISResidentService接口自动注入
     */
    @Autowired
    private IcisResidentServiceI icisResidentServiceI;

    public void setIcisResidentServiceI(IcisResidentServiceI icisResidentServiceI) {
        this.icisResidentServiceI = icisResidentServiceI;
    }

    @RequestMapping("index")
    public ModelAndView indexPage(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("selectAllResident")
    @ResponseBody
    public List<IcisResident> selectAllResident(){
        return icisResidentServiceI.selectAllResident();
    }

    /**
     * 方法：管理员添加业主用户的方法
     * 请求：/icisResident/addIcisResident.html
     * 请求类型：POST
     * @param icisResident
     * @Return 添加业主用户信息反馈
     */
    @RequestMapping(value = "addIcisResident", method = RequestMethod.POST)
    @ResponseBody
    public String addIcisResident(IcisResident icisResident) {
        int existUserSize = this.icisResidentServiceI.isExistAssignUser(icisResident).size();
        //判断业主是否存在
        if (existUserSize == 1) {
            return "该业主已存在，无法添加";
        } else {
            int addState = this.icisResidentServiceI.insert(icisResident);
            //判断业主添加是否成功
            if (addState == 0) {
                return "添加业主失败";
            } else {
                return "添加业主成功";
            }
        }
    }

    /**
     * 登录检测用户名和密码是否正确
     * 请求：/icisResident/loginCheck.html
     * 请求类型：POST
     * @param icisResident
     * @return 登录反馈
     */
    @RequestMapping(value = "loginCheck", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(IcisResident icisResident) {
        int existUserSize = this.icisResidentServiceI.isExistAssignUser(icisResident).size();
        //判断用户是否存在
        if (existUserSize == 0) {
            return "该用户不存在";
        } else {
            int loginUserSize = this.icisResidentServiceI.selectByUsernameAndPassword(icisResident).size();
            //判断用户名和密码是否正确
            if (loginUserSize == 0) {
                return "密码不正确";
            } else {
                return "登录成功";
            }
        }
    }

    /**
     * 登录成功后调用获取用户对象
     * 请求：/icisResident/login.html
     * 请求类型：POST
     * @param icisResident
     * @return 登录的用户对象
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public IcisResident login(IcisResident icisResident) throws Exception {
        IcisResident loginIcisResident = this.icisResidentServiceI.selectByUsernameAndPassword(icisResident).get(0);
//        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        //返回头像设置链接
        if (loginIcisResident.getHeadPhoto() == null) {
            loginIcisResident.setHeadPhoto(null);
        } else {
            loginIcisResident.setHeadPhoto("/icisResident/getPhoto.html?filePath=" + loginIcisResident.getHeadPhoto());
        }
        return loginIcisResident;
    }

    /**
     * 更新用户信息
     * 请求：/icisResident/updateUserInfo.html
     * 请求类型：POST
     * @param icisResident
     * @return 更新是否成功
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserInfo(IcisResident icisResident) {
        int updateState = this.icisResidentServiceI.updateByPrimaryKey(icisResident);
        //判断修改是否成功
        if (updateState == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }

    /**
     * 上传用户头像
     * 请求：/icisResident/uploadHeadPhoto.html
     * 请求类型：POST
     * @param file
     * @param icisResident
     * @return 头像上传信息反馈
     */
    @RequestMapping(value = "uploadHeadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String uploadHeadPhoto(@RequestParam("file")MultipartFile file, IcisResident icisResident){
        //判断上传是否成功
        if (uploadHeadPhotoFunction(file,icisResident)){
            return "上传成功";
        } else {
            return "上传失败";
        }
    }

    /**
     * 上传头像的具体方法
     * @param file
     * @param icisResident
     * @return 布尔值
     */
    private boolean uploadHeadPhotoFunction(MultipartFile file,IcisResident icisResident){
        //判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //设置头像文件存储路劲
                String filePath = request.getSession().getServletContext().getRealPath("/").replaceAll("/target/ICIS/","") + "/src/main/webapp/images/headPhoto/icis_resident/" + file.getOriginalFilename();
                //存储头像文件
                file.transferTo(new File(filePath));
                //将头像路径存储至数据库
                icisResident.setHeadPhoto(filePath);
                this.icisResidentServiceI.updateByPrimaryKey(icisResident);
                //以布尔值返回处理结果
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 发送获取头像请求
     * 请求：/icisResident/getHeadPhoto.html
     * 请求类型：POST
     * @param icisResident
     * @return 头像设置链接
     */
    @RequestMapping(value = "getHeadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String getHeadPhoto(IcisResident icisResident) {
        try {
            //从数据库中获取头像存储路径
            IcisResident icisResidentGetHeadPhoto = this.icisResidentServiceI.selectByPrimaryKey(icisResident.getId());
            String headPhotoFilePath = icisResidentGetHeadPhoto.getHeadPhoto();
            //获取本机IP
//            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            //返回头像设置链接
            return "/icisResident/getPhoto.html?filePath=" + headPhotoFilePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
