package com.liu.controller;

import com.liu.domain.Product;
import com.liu.feignclients.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class TestFeignController {
    @Autowired
    private ProductClient productClient;
    @GetMapping("/feign/test")
    public String test(){
        log.info("测试feign");
        String s = productClient.showMsg();
        System.out.println(s);
        return s;
    }


    @GetMapping("/feign/testFindAll")
    public Map<String, Object> test1(){
        log.info("testFindAll");
        Map<String, Object> map = productClient.findAll();
        System.out.println(map);
        return map;
    }


    @GetMapping("/feign/testFindById")
    public Map<String, Object> test2(String id){
        log.info("testfindById");
        Map<String, Object> map = productClient.findById(id);
        System.out.println(map);
        return map;
    }
    //如果这方法没有测试成功降级 记得关一下线程睡眠


    @GetMapping("/feign/pojo")
    public Map<String, Object> test3(Product product){
        log.info("testPojo");
        Map<String, Object> map = productClient.save(product);
        System.out.println(map);
        return map;
    }


}
