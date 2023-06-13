package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.InStockItem;
import com.example.warehouse.entity.OrdersItem;
import com.example.warehouse.mapper.InStockItemMapper;
import com.example.warehouse.mapper.OrdersItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersItemService extends ServiceImpl<OrdersItemMapper, OrdersItem> {
    @Autowired
    private OrdersItemMapper ordersItemMapper;

    public List<OrdersItem> getOrdersItemByOrdersId(int ordersId) {
        return ordersItemMapper.getOrdersItemByOrdersId(ordersId);
    }

}
