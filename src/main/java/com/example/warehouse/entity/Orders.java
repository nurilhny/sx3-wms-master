package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("orders")
public class Orders {
    @TableId(type = IdType.AUTO)
    private int ordersId;
    private Date ordersTime;
    private int userId;
    private String ordersStatus;
    private float totalPrice;
    private String paymentMethod;
    private String shippingAddress;
    @TableField(exist = false)
    private List<OrdersItem> ordersItems;
}
