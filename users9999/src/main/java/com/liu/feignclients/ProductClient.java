package com.liu.feignclients;

import com.liu.domain.Product;
import com.liu.fallback.ProductClientBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Component
@FeignClient(value = "products",fallback = ProductClientBack.class) //属性是注册在consul中的服务名字id
public interface ProductClient {
    @GetMapping("/product/showMsg")//映射的路径与商品ProductController的一致 也就是你要调用的服务路径
    String  showMsg();//返回值与Product里面的Controller 一致 方法名随意

    @GetMapping("/product/findAll")
    Map<String,Object> findAll();

    @GetMapping("/product/findById")
    Map<String,Object> findById(@RequestParam("id") String id1);
    //必须要RequestParam
    //这个@RequestParam("id") 的值是要与商品的参数名字findById(String id)保持一致，后面的随意


    @PostMapping("/product/save")
    //使用Post方式的时候 服务的提供方(product的controller )与服务的调用方(User)都要使用@RequestBody
    //ProductController----> public Map<String,Object> save(@RequestBody  Product product)
    Map<String,Object> save(@RequestBody Product product);


}


