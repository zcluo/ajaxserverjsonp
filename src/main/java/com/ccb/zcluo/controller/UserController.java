package com.ccb.zcluo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.ccb.zcluo.service.IUserService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONWrappedObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.codehaus.jackson.map.util.JSONPObject;


import com.ccb.zcluo.User;
import com.ccb.zcluo.service.IUserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("/showUser")

    public String toIndex(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user",user);
        return "showUser";
    }
    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
       return user;
    }
    @ResponseBody
    @RequestMapping("/showName")
    public JSONPObject getName(HttpServletRequest request, String callbackParam){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        return new JSONPObject(callbackParam,user);


    }
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


    @ResponseBody
    @RequestMapping(value = "/re")
    public ModelAndView re() {
        String uri = "https://api.vultr.com/v1/os/list";
        HttpGet httpGet = new HttpGet(uri);
        String resp = new String();
        ObjectMapper objMapper = new ObjectMapper();
        String ret = "";
        try {
            //String resp = Request.Get(uri).connectTimeout(100).socketTimeout(100).execute().returnContent().asString();
            resp = Request.Get(uri).execute().returnContent().asString();
            System.out.println(resp);

            Map jsonObject = JSONObject.parseObject(resp);
            //ret = jsonObject.toJSONString();
            for (Object key:jsonObject.keySet()
                 ) {
                System.out.println("key is " + key + "; value is "+jsonObject.get(key));
                
            }
            System.out.println(ret);
            ModelAndView mv = new ModelAndView();
            mv.addObject("sysinfo",jsonObject);

            mv.setViewName("re");

            return mv;





            //return new JSONPObject("callbackfunc",resp);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }


        return null;
    }

    //match automatically
    @RequestMapping("/person")
    public String toPerson(String name,double age){
        System.out.println(name+" "+age);
        return "hello";
    }
    @RequestMapping("/person1")
    public String toPerson(User p){
        System.out.println(p.getUserName()+" "+p.getAge());
        return "hello";
    }

    //the parameter was converted in initBinder
    @RequestMapping("/date")
    public String date(Date date){
        System.out.println(date);
        return "hello";
    }

    //At the time of initialization,convert the type "String" to type "date"
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true));
    }

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public String upload(HttpServletRequest req) throws Exception{
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+
                "upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();

        return "hello";
    }

    @RequestMapping(value="/param")
    public String testRequestParam(@RequestParam(value="id") Integer id,
                                   @RequestParam(value="name")String name){
        System.out.println(id+" "+name);
        return "/hello";
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    public String get(@PathVariable("id") Integer id){
        System.out.println("get"+id);
        return "/hello";
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.POST)
    public String post(@PathVariable("id") Integer id){
        System.out.println("post"+id);
        return "/hello";
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
    public String put(@PathVariable("id") Integer id){
        System.out.println("put"+id);
        return "/hello";
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete"+id);
        return "/hello";
    }

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("in testExceptionHandler");
        return mv;
    }

    @RequestMapping("/error")
    public String error(){
        int i=5/0;
        return "hello";
    }

}