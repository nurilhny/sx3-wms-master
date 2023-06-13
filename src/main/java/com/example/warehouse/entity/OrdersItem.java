package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orders_item")
public class OrdersItem {
    @TableId(type = IdType.AUTO)
    private int ordersItemId;
    private int ordersId;
    private int productId;
    private int quantity;
    private int returnQuantity;
    private float price;
}
