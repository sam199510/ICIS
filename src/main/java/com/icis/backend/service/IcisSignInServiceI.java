package com.icis.backend.service;

import com.icis.backend.entity.IcisSignIn;
import org.springframework.stereotype.Repository;

@Repository
public interface IcisSignInServiceI {
    /**
     * 签到功能
     * @param record
     * @return 签到是否成功
     */
    public int signIn(IcisSignIn record);
}
