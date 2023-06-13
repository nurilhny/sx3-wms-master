package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("out_stock_item")
public class OutStockItem {
    @TableId(type = IdType.AUTO)
    private int outStockItemId;
    private int outStockId;
    private int productId;
    private int outStockQuantity;
    private float outStockPrice;
}
