package com.ccb.zcluo.controller;

import com.ccb.zcluo.model.UserJsonp;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zcluo on 2016/4/3.
 */
@RestController
public class JacksonJsonViewController {
    @RequestMapping(value = "/jackson1",produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(UserJsonp.OnlyIdView.class)
    @ResponseBody
    public UserJsonp test1() {
        return new UserJsonp(1L, "zhangsan");
    }

    @RequestMapping(value = "/jackson2",produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(UserJsonp.OnlyNameView.class)
    public UserJsonp test2() {
        return new UserJsonp(1L, "zhangsan");
    }

    @RequestMapping("/jackson3")
    @JsonView(UserJsonp.AllView.class) //可以省略
    public UserJsonp test3() {
        return new UserJsonp(1L, "zhangsan");
    }
}
