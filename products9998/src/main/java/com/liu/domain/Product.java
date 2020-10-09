package com.liu.domain;


import lombok.Data;
import java.util.Date;
@Data
public class Product {
    private String id;
    private String name;
    private Double price;
    private Date update;
}
