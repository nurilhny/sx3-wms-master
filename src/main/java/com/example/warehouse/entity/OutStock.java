package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("out_stock")
public class OutStock {
    @TableId(type = IdType.AUTO)
    private int outStockId;
    private Date outStockTime;
    private int userId;
    private float totalPrice;
    private String outStockStatus;
    private String outStockCategory;
    @TableField(exist = false)
    private List<OutStockItem> outStockItems;
}
