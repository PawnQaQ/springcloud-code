package com.liu.fallback;

import com.liu.domain.Product;
import com.liu.feignclients.ProductClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ProductClientBack implements ProductClient {
   private Map<String, Object> res=new HashMap<>();
    @Override
    public String showMsg() {
        return "降级已经触发.....";
    }

    @Override
    public Map<String, Object> findAll() {
        res.put("msg","当前服务不可用，服务已经降级...");
        return res;
    }

    @Override
    public Map<String, Object> findById(String id1) {
        res.put("msg","当前服务不可用，服务已经降级...");
        return res;
    }

    @Override
    public Map<String, Object> save(Product product) {
        res.put("msg","当前保存服务不可用，服务已经降级...");
        return res;
    }
}
