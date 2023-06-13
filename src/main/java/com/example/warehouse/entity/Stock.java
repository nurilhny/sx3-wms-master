package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stock")
public class Stock {
    @TableId(type = IdType.AUTO)
    private int stockId;
    private int productId;
    private int stockQuantity;
    private String position;
}
