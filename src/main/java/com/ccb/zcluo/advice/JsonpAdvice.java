package com.ccb.zcluo.advice;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Created by zcluo on 2016/4/3.
 */

//@ControllerAdvice(basePackages = "com.ccb.zcluo.controller")
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice  {
    public JsonpAdvice() {

        super("callback"); //指定jsonpParameterNames
        System.out.println("in jsonp advice!");
    }
}
