package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private int productId;
    private String productName;
    private int supplierId;
    private float price;
    @TableField(exist = false)
    private Supplier supplier;
    @TableField(exist = false)
    private Stock stock;
}