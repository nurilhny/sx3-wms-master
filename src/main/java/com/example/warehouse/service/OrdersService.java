package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.Orders;
import com.example.warehouse.entity.OrdersItem;
import com.example.warehouse.entity.Stock;
import com.example.warehouse.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService extends ServiceImpl<OrdersMapper, Orders> {
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    StockService stockService;

    public Orders selectDetailsById(int ordersId) {
        return ordersMapper.selectDetailsById(ordersId);
    }

    public List<Orders> selectDetails() {
        return ordersMapper.selectDetails();
    }

    public boolean updateStockByOrdersId(int ordersId) {
        Orders orders = selectDetailsById(ordersId);
        if (!orders.getOrdersStatus().equals("uncompleted")) {
            // 状态是未完成，才能执行更新库存的操作
            return false;
        }
        List<OrdersItem> ordersItems = orders.getOrdersItems();
        for (OrdersItem item : ordersItems) {
            int productId = item.getProductId();
            int ordersQuantity = item.getQuantity();
            // product在stock表中暂时只能有一条记录，即一个商品只能放在一个位置
            Stock stock = stockService.getOrCreateByProductId(productId);
            int currentQuantity = stock.getStockQuantity();
            int resultQuantity = currentQuantity - ordersQuantity;
            if (resultQuantity < 0) {
                orders.setOrdersStatus("error");
                saveOrUpdate(orders);
                return false;
            }
        }
        for (OrdersItem item : ordersItems) {
            int productId = item.getProductId();
            int ordersQuantity = item.getQuantity();
            Stock stock = stockService.getOrCreateByProductId(productId);
            int currentQuantity = stock.getStockQuantity();
            int resultQuantity = currentQuantity - ordersQuantity;
            stock.setStockQuantity(resultQuantity);
            boolean isStockUpdated = stockService.saveOrUpdate(stock);
            if (!isStockUpdated) {
                orders.setOrdersStatus("error");
                saveOrUpdate(orders);
                return false;
            }
        }
        orders.setOrdersStatus("completed");
        saveOrUpdate(orders);
        return true;
    }

    public List<Orders> getOrdersByPaymentMethod(String ordersPaymentMethod) {
        return ordersMapper.getOrdersByPaymentMethod(ordersPaymentMethod);
    }

    public List<Orders> getOrdersByStatus(String ordersStatus) {
        return ordersMapper.getOrdersByStatus(ordersStatus);
    }

    public List<Orders> getOrdersByPaymentMethodAndStatus(String ordersPaymentMethod, String ordersStatus) {
        return ordersMapper.getOrdersByPaymentMethodAndStatus(ordersPaymentMethod, ordersStatus);
    }
}
