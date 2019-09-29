package com.hage.spring;

import com.hage.spring.domain.Account;
import com.hage.spring.service.IAccountService;
import com.hage.spring.utils.TransactionUtil;
import config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description: 通过代理对方法提供事务支持
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ProxyTest {

    @Resource(name = "accountService")
    //这个AccountService类没有事务支出，要通过动态代理增强
    private IAccountService as;
    @Resource(name = "transactionUtil")
    private TransactionUtil transactionUtil;
    @Test
    public void testFindAll(){
        List<Account> allAccount = as.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindAllById(){
        Account account = as.findAccountById(2);
        System.out.println(account);

    }
    @Test
    public void testTransferAccount(){
        as.transferAccount("ccc", "bbb", 100f);
    }
    //用代理增强
    @Test
    public void testProxy(){
        //通过代理对业务方法进行事务支持
        IAccountService accountService=(IAccountService) Proxy.newProxyInstance(as.getClass().getClassLoader(), as.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            //环绕通知
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result=null;
                try {
                    //前置通知
                    transactionUtil.beginTransaction();
                    result=method.invoke(as, args);
                    //后置通知
                    transactionUtil.commitTransaction();
                }catch (Exception e){
                    //异常通知
                    transactionUtil.rollbackTransaction();
                }finally {
                    //最终通知
                    transactionUtil.release();
                }
                return result;
            }
        });

        accountService.transferAccount("bbb", "ccc", 100f);

    }
}
