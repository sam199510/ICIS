package com.icis.backend.controller;

import com.icis.backend.entity.IcisContacts;
import com.icis.backend.service.IcisContactsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/icisContacts")
public class IcisContactsController {
    /**
     * 将HttpServletRequest自动注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 将IcisContactsService接口自动注入
     */
    @Autowired
    private IcisContactsServiceI icisContactsServiceI;

    public void setIcisContactsServiceI(IcisContactsServiceI icisContactsServiceI) {
        this.icisContactsServiceI = icisContactsServiceI;
    }

    /**
     * 添加联系人请求
     * 请求：/icisContacts/addContact.html
     * 请求类型：POST
     * @param icisContacts
     * @return 添加联系人是否成功
     */
    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    @ResponseBody
    public String addContact(IcisContacts icisContacts) {
        int isExistIcisContactsState = this.icisContactsServiceI.isExistIcisContact(icisContacts).size();
        if (isExistIcisContactsState == 1) {
            return "该联系人已存在";
        } else {
            int addContactState = this.icisContactsServiceI.addContact(icisContacts);
            if (addContactState == 0) {
                return "添加联系人失败";
            } else {
                return "添加联系人成功";
            }
        }
    }

    /**
     * 请求获取所有联系人
     * 请求类型：POST
     * 请求：/icisContacts/showAllIcisContacts.html
     * @return 联系人列表
     */
    @RequestMapping(value = "showAllIcisContacts", method = RequestMethod.POST)
    @ResponseBody
    public List<IcisContacts> selectAllIcisContacts() {
        return this.icisContactsServiceI.selectAllIcisContacts();
    }

    /**
     * 删除联系人
     * 请求类型：POST
     * 请求：/icisContacts/deleteIcisContact.html
     * @param icisContacts
     * @return 联系人删除是否成功
     */
    @RequestMapping(value = "deleteIcisContact", method = RequestMethod.POST)
    @ResponseBody
    public String deleteIcisContact(IcisContacts icisContacts) {
        int deleteState = this.icisContactsServiceI.deleteContact(icisContacts.getId());
        if (deleteState == 1) {
            return "删除联系人成功";
        } else {
            return "删除联系人失败";
        }
    }

    /**
     * 更新联系人信息
     * 请求：/icisContacts/updateContact.html
     * 请求类型：POST
     * @param icisContacts
     * @return 更新联系人信息是否成功
     */
    @RequestMapping(value = "updateContact", method = RequestMethod.POST)
    @ResponseBody
    public String updateContact(IcisContacts icisContacts) {
        int updateState = this.icisContactsServiceI.updateContact(icisContacts);
        if (updateState == 1) {
            return "更新联系人信息成功";
        } else {
            return "更新联系人信息失败";
        }
    }
}
