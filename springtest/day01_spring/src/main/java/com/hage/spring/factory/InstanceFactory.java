package com.hage.spring.factory;

import com.hage.spring.service.IAccountService;
import com.hage.spring.service.impl.AccountServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/9/18
 * Time: 20:17
 * Description: No Description
 */
public class InstanceFactory {
    public IAccountService getService(){
        return new AccountServiceImpl();
    }
}
