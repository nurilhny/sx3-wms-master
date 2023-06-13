package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("in_stock")
public class InStock {
    @TableId(type = IdType.AUTO)
    private int inStockId;
    private Date inStockTime;
    private float totalPrice;
    private int userId;
    private String inStockStatus;
    private String inStockCategory;
    @TableField(exist = false)
    private List<InStockItem> inStockItems;
}
