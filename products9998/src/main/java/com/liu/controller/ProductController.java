package com.liu.controller;


import com.liu.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ProductController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/product/showMsg")
    public String showMsg(){
        log.info("进入商品服务,展示商品~~~");
        return "进入商品服务,展示商品~~~,当前服务的端口:"+port;
    }

    @GetMapping("/product/findAll")
    public Map<String,Object> findAll(){
        Map<String, Object> map = new HashMap<>();
        log.info("进入商品服务,查询所有商品信息...");
        map.put("status",true);
        map.put("msg","查询所有商品信息成功!,当前服务端口:"+port);
        return map;
    }

       @GetMapping("/product/findById")
       //这里就是在测试怎样把访问方法传入参数的id抓到这里来
    public Map<String,Object> findById(String id){//这里也可以加@RequestParam
           Map<String, Object> map = new HashMap<>();

           //模拟等待超时
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }//Read timed out
//=====================================================
        log.info("这是商品服务的findById方法....");
        map.put("这是调用的端口",port);
        map.put("这是商品的id",id);
        return map;
    }

       @PostMapping("/product/save")
    public Map<String,Object> save(@RequestBody  Product product){
        Map<String, Object> map = new HashMap<>();
        log.info("这是商品服务的findById方法....");
        map.put("这是调用的端口",port);
        map.put("这是商品的id",product);
        return map;
    }


    //测试熔断
    @GetMapping("/product/break")
    //@HystrixCommand(fallbackMethod = "testBreakFallBack")
    //@HystrixCommand(defaultFallback = "BreakFallBack") 在测试客户端的熔断时
    //如果服务端降级和客户端降级同时开启,要求服务端降级方法的返回值必须与客户端方法降级的返回值一致
    public String testBreak(Integer id){
        if(id<0){
            throw  new RuntimeException("非法参数异常,id的值不能小于0");
        }
        return "访问成功，当前的id为："+ id;
    }

    public String testBreakFallBack(Integer id){
        return "数据不合法："+ id+"触发了熔断....";
    }

    public String BreakFallBack(){
        return "触发了熔断....";
    }


}
