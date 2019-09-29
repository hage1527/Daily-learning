package com.hage.spring.factory;

import com.hage.spring.service.IAccountService;
import com.hage.spring.service.impl.AccountServiceImpl;

/**
 * User: Administrator
 * Date: 2019/9/18
 * Time: 20:25
 */
public class StaticFactory {
    public static IAccountService getService(){
        return new AccountServiceImpl();
    }
}
