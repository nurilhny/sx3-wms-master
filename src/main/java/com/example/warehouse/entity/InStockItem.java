package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("in_stock_item")
public class InStockItem {
    @TableId(type = IdType.AUTO)
    private int inStockItemId;
    private int inStockId;
    private int productId;
    private int inStockQuantity;
    private float inStockPrice;
}
