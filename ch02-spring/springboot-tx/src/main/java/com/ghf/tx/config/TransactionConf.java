package com.ghf.tx.config;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//全局事务配置
@Configuration
public class TransactionConf {

    String POINTCUT_EXPRESSTION = "execution(* com.ghf.tx.service.*.*(..))";

    @Autowired
    private DataSourceTransactionManager transactionManager;

    //事务通知
    @Bean
    public TransactionInterceptor txAdvice(){

        NameMatchTransactionAttributeSource nameMatchSource = new NameMatchTransactionAttributeSource();
        Map<String, TransactionAttribute> nameMap = new HashMap<>();

        //规则1：只读事务
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        //规则2：更新事务
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));//发生Exception类异常，回滚
        int txTimeout = 50000;
        requiredTx.setTimeout(txTimeout);//超过5s则回滚

        //更新事务：
        nameMap.put("txAdd*", requiredTx);
        nameMap.put("txDdelete*", requiredTx);
        nameMap.put("txUpdate*", requiredTx);
        //只读事务：
        nameMap.put("txFind*", readOnlyTx);

        //保存方法-具体事务规则
        nameMatchSource.setNameMap(nameMap);

        //使用方法名匹配具体管理规则
        TransactionAttributeSource transactionAttributeSouce = nameMatchSource;

        //事务通知：用事务管理器 + 事务属性来源（事务管理规则）
        return new TransactionInterceptor(transactionManager, transactionAttributeSouce);
    }

    //顾问（相当于切面：切点+通知内容）
    @Bean
    public Advisor txAdvisor(){

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(POINTCUT_EXPRESSTION);

        Advice advice = txAdvice();
        Advisor advisor =new DefaultPointcutAdvisor(pointcut, advice);
        return advisor;
    }
}
