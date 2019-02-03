package com.augurit.myproject.sjcj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
public class TestController  {

    @RequestMapping("/index.do")
    public ModelAndView geTest(){
        ModelAndView model=new ModelAndView("index");
        model.addObject("message","Hello World");
        return model;
    }

    @RequestMapping(value = "/indexs",method = RequestMethod.GET)
    @ResponseBody
    public String indexs(){
         return "Hello World";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Map<String,Object> json(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","Ryan");
        map.put("age","18");
        map.put("sex","man");
        return map;
    }
}
