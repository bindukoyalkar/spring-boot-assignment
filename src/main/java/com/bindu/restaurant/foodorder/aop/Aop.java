package com.bindu.restaurant.foodorder.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class Aop{
    private static final Logger logger = Logger.getLogger(Aop.class.getName());

    @Before("execution(* com.bindu.restaurant.foodorder.service.*.find*(..))")
    public void performAop(){
       logger.info("\n=======>>> " +
                "executing @Before advice on find methods");
    }
}