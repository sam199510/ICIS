package com.icis.backend.controller;

import com.icis.backend.entity.IcisDynamic;
import com.icis.backend.entity.IcisDynamicComment;
import com.icis.backend.entity.IcisDynamicSupport;
import com.icis.backend.service.IcisDynamicCommentServiceI;
import com.icis.backend.service.IcisDynamicServiceI;
import com.icis.backend.service.IcisDynamicSupportServiceI;
import com.icis.backend.service.IcisResidentServiceI;
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
import java.util.Set;

@Controller
@RequestMapping("/icisDynamic")
public class IcisDynamicController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisDynamicService接口自动注入
     */
    @Autowired
    private IcisDynamicServiceI icisDynamicServiceI;

    public void setIcisDynamicServiceI(IcisDynamicServiceI icisDynamicServiceI) {
        this.icisDynamicServiceI = icisDynamicServiceI;
    }

    /**
     * 将IcisDynamicSupportService接口自动注入
     */
    @Autowired
    private IcisDynamicSupportServiceI icisDynamicSupportServiceI;

    public void setIcisDynamicSupportServiceI(IcisDynamicSupportServiceI icisDynamicSupportServiceI) {
        this.icisDynamicSupportServiceI = icisDynamicSupportServiceI;
    }

    /**
     * 将IcisDynamicCommentService接口自动注入
     */
    @Autowired
    private IcisDynamicCommentServiceI icisDynamicCommentServiceI;

    public void setIcisDynamicCommentServiceI(IcisDynamicCommentServiceI icisDynamicCommentServiceI) {
        this.icisDynamicCommentServiceI = icisDynamicCommentServiceI;
    }

    /**
     * 将IcisResidentService接口自动注入
     */
    @Autowired
    private IcisResidentServiceI icisResidentServiceI;

    public void setIcisResidentServiceI(IcisResidentServiceI icisResidentServiceI) {
        this.icisResidentServiceI = icisResidentServiceI;
    }

    /**
     * 发布动态请求
     * 请求：/icisDynamic/addIcisDynamic.html
     * 请求类型：POST
     * @param icisResidentId
     * @param icisDynamic
     * @param file
     * @return 发布动态是否成功
     */
    @RequestMapping(value = "addIcisDynamic", method = RequestMethod.POST)
    @ResponseBody
    public String addIcisDynamic(Long icisResidentId, IcisDynamic icisDynamic, @RequestParam("file") MultipartFile file) {
        icisDynamic.setCreatorId(icisResidentId);
        Date publish_time = new Date();
        icisDynamic.setPublishTime(publish_time);
        if (file != null && !file.isEmpty()) {
            try {
                //获取图片上传路径
                String filePath = request.getSession().getServletContext().getRealPath("/").replaceAll("/target/ICIS/","") + "/src/main/webapp/images/dynamic/" + file.getOriginalFilename();
                file.transferTo(new File(filePath));
                icisDynamic.setPublishPhoto(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int addDynamicState = this.icisDynamicServiceI.addIcisDynamic(icisDynamic);
        if (addDynamicState == 1) {
            return "发布动态成功";
        } else {
            return "发布动态失败";
        }
    }

    /**
     * 点赞动态方法
     * 请求类型：POST
     * 请求：/icisDynamic/supportDynamic.html
     * @param supportorId
     * @param dynamicId
     * @return
     */
    @RequestMapping(value = "supportDynamic", method = RequestMethod.POST)
    @ResponseBody
    public String supportDynamic(Long supportorId, Long dynamicId) {
        //建立点赞对象
        IcisDynamicSupport icisDynamicSupport = new IcisDynamicSupport();
        //设置动态id
        icisDynamicSupport.setDynamicId(dynamicId);
        //设置用户id
        icisDynamicSupport.setSupportorId(supportorId);
        //设置点赞时间
        Date supportTime = new Date();
        icisDynamicSupport.setSupporterTime(supportTime);
        //将点赞结果存入数据库
        int supportState = this.icisDynamicSupportServiceI.supportIcisDynamic(icisDynamicSupport);
        //判断点赞添加是否成功
        if (supportState == 1) {
            return "点赞成功";
        } else {
            return "点赞失败";
        }
    }

    /**
     * 取消点赞请求
     * 请求：/icisDynamic/unsupportDynamic.html
     * 请求类型：POST
     * @param icisDynamicSupport
     * @return 取消点赞是否成功
     */
    @RequestMapping(value = "unsupportDynamic", method = RequestMethod.POST)
    @ResponseBody
    public String unsupportDynamic(IcisDynamicSupport icisDynamicSupport) {
        int unsupportDynamicState = this.icisDynamicSupportServiceI.unsupportDynamic(icisDynamicSupport);
        if (unsupportDynamicState == 1) {
            return "取消点赞成功";
        } else {
            return "取消点赞失败";
        }
    }

    /**
     * 给动态评论
     * 请求类型：POST
     * 请求：/icisDynamic/unsupportDynamic.html
     * @param icisDynamicComment
     * @return 评论是否成功
     */
    @RequestMapping(value = "commentDynamic", method = RequestMethod.POST)
    @ResponseBody
    public String commentDynamic(IcisDynamicComment icisDynamicComment) {
        //评论时间
        Date commentTime = new Date();
        icisDynamicComment.setCommentTime(commentTime);
        int commentDynamicState = this.icisDynamicCommentServiceI.commentIcisDynamic(icisDynamicComment);
        if (commentDynamicState == 1) {
            return "评论动态成功";
        } else {
            return "评论动态失败";
        }
    }

    /**
     * 获取所有动态内容
     * @return 动态列表
     * @throws Exception
     */
    @RequestMapping(value = "selectAllIcisDynamic", method = RequestMethod.POST)
    @ResponseBody
    public List<com.icis.backend.model.IcisDynamic> selectAllIcisDynamic() throws Exception {
        //获取本机IP
        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        //建立一个朋友圈动态的数组模型
        List<com.icis.backend.model.IcisDynamic> icisDynamiModel = new ArrayList<com.icis.backend.model.IcisDynamic>();
        //从数据库中获取朋友圈动态的所有数据
        List<IcisDynamic> icisDynamics = this.icisDynamicServiceI.selectAllIcisDynamic();
        //循环遍历朋友圈动态数组
        for (IcisDynamic icisDynamic: icisDynamics) {
            //建立一个朋友圈动态的单元模型
            com.icis.backend.model.IcisDynamic icisDynamicItem = new com.icis.backend.model.IcisDynamic();
            //获取发布朋友圈动态的用户的昵称
            Long creatorId = icisDynamic.getCreatorId();
            String creatorNickname = this.icisResidentServiceI.selectByPrimaryKey(creatorId).getNickname();
            icisDynamicItem.setDynamicCreatorNickname(creatorNickname);
            //获取用户头像
            String dynamicUserHeadPhoto = this.icisResidentServiceI.selectByPrimaryKey(creatorId).getHeadPhoto();
            if (dynamicUserHeadPhoto == null) {
                icisDynamicItem.setDynamicUserHeadPhoto(null);
            } else {
                icisDynamicItem.setDynamicUserHeadPhoto("http://" + ipAddress + ":8080/icisDynamic/getPhoto.html?filePath=" + dynamicUserHeadPhoto);
            }
            //获取朋友圈动态的内容
            icisDynamicItem.setDynamicPublishContent(icisDynamic.getPublishContent());
            //获取朋友圈动态的发布时间
            icisDynamicItem.setDynamicPublishTime(icisDynamic.getPublishTime());
            //获取朋友圈动态的图片
            if (icisDynamic.getPublishPhoto() == null) {
                icisDynamicItem.setDynamicPublishPhoto(null);
            } else {
                icisDynamicItem.setDynamicPublishPhoto("http://" + ipAddress + ":8080/icisDynamic/getPhoto.html?filePath=" + icisDynamic.getPublishPhoto());
            }
            //建立一个点赞的数组模型
            List<com.icis.backend.model.IcisDynamicSupport> icisDynamicSupportModel = new ArrayList<com.icis.backend.model.IcisDynamicSupport>();
            //从数据库中获取点赞列表
            List<IcisDynamicSupport> icisDynamicSupports = this.icisDynamicSupportServiceI.selectSupportorByDynamicId(icisDynamic.getId());
            //循环遍历点赞列表
            for (IcisDynamicSupport icisDynamicSupport : icisDynamicSupports) {
                com.icis.backend.model.IcisDynamicSupport icisDynamicSupportItem = new com.icis.backend.model.IcisDynamicSupport();
                icisDynamicSupportItem.setDynamicSupportTime(icisDynamicSupport.getSupporterTime());
                icisDynamicSupportItem.setDynamicSupportorNickname(this.icisResidentServiceI.selectByPrimaryKey(icisDynamicSupport.getSupportorId()).getNickname());
                icisDynamicSupportModel.add(icisDynamicSupportItem);
            }
            //获取点赞
            icisDynamicItem.setIcisDynamicSupports(icisDynamicSupportModel);
            //建立一个评论的数组模型
            List<com.icis.backend.model.IcisDynamicComment> icisDynamicCommentModel = new ArrayList<com.icis.backend.model.IcisDynamicComment>();
            //从数据库中获取评论列表
            List<IcisDynamicComment> icisDynamicComments = this.icisDynamicCommentServiceI.selectDynamicCommentByDynamicId(icisDynamic.getId());
            //循环遍历评论列表
            for (IcisDynamicComment icisDynamicComment: icisDynamicComments) {
                com.icis.backend.model.IcisDynamicComment icisDynamicCommentItem = new com.icis.backend.model.IcisDynamicComment();
                icisDynamicCommentItem.setDynamicCommentContent(icisDynamicComment.getContent());
                icisDynamicCommentItem.setDynamicCommentTime(icisDynamicComment.getCommentTime());
                icisDynamicCommentItem.setDynamicCommentorNickname(this.icisResidentServiceI.selectByPrimaryKey(icisDynamicComment.getCommentorId()).getNickname());
                icisDynamicCommentModel.add(icisDynamicCommentItem);
            }
            //获取评论
            icisDynamicItem.setIcisDynamicComments(icisDynamicCommentModel);
            //将单元添加入数组中
            icisDynamiModel.add(icisDynamicItem);
        }
        //返回朋友圈动态数组对象
        return icisDynamiModel;
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
