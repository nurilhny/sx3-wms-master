package com.example.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.warehouse.entity.OrdersItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersItemMapper extends BaseMapper<OrdersItem> {
    List<OrdersItem> getOrdersItemByOrdersId(int ordersId);
}
