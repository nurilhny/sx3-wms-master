package com.example.warehouse.controller;
import com.example.warehouse.entity.Orders;
import com.example.warehouse.entity.OrdersItem;
import com.example.warehouse.service.OrdersItemService;
import com.example.warehouse.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersItemService ordersItemService;

    //通过ID获取订单
    @GetMapping("/orders/{ordersId}")
    public Orders getOrdersById(@PathVariable int ordersId) {
        return ordersService.selectDetailsById(ordersId);
    }

    //通过类型获取入库单
    @GetMapping("/orders/paymentMethod/{paymentMethod}")
    public List<Orders> getOrdersByPaymentMethod(@PathVariable String paymentMethod) {
        return ordersService.getOrdersByPaymentMethod(paymentMethod);
    }

    //通过状态获取入库单
    @GetMapping("/orders/status/{orderStatus}")
    public List<Orders> getOrdersByStatus(@PathVariable String orderStatus) {
        return ordersService.getOrdersByStatus(orderStatus);
    }

    //通过类型+状态获取入库单
    @GetMapping("/orders/paymentMethod/{paymentMethod}/{orderStatus}")
    public List<Orders> getOrdersByPaymentMethodAndStatus(@PathVariable String paymentMethod,
                                                       @PathVariable String orderStatus) {
        return ordersService.getOrdersByPaymentMethodAndStatus(paymentMethod,orderStatus);
    }

    //获取所有订单
    @GetMapping("/orders/")
    public List<Orders> getAllOrders() {
        System.out.println("我我我我我我我我我来啊来拉力阿来阿拉！！！");
        return ordersService.selectDetails();
    }

    //添加订单
    @PostMapping("/orders/")
    public boolean addOrders(@RequestBody Orders orders) {
        System.out.println("qweqweqweqweqweqwe");
        System.out.println(orders.getUserId());


        Boolean flag1 = ordersService.save(orders);
        List<Orders> ordersList = ordersService.selectDetails();
        int ordersId = 0;
        for (Orders oS:ordersList
        ) {

            if(ordersId<oS.getOrdersId()){
                ordersId = oS.getOrdersId();
            }

        }

        System.out.println("-----------------");
        System.out.println(ordersId);
        System.out.println("---------qqq--------");

        // 保存ordersId
        for (OrdersItem ordersItem:orders.getOrdersItems()
        ) {
            System.out.println("-----------------");
            System.out.println(ordersId);
            ordersItem.setOrdersId(ordersId);
            Boolean flag2 = ordersItemService.save(ordersItem);
            if(!flag2){
                return flag2;
            }
        }

        return flag1;
    }

    //更新订单
    @PutMapping("/orders/")
    public boolean updateOrders(@RequestBody Orders orders) {
        return ordersService.updateById(orders);
    }

    //删除订单
    @DeleteMapping("/orders/{ordersId}")
    public boolean deleteOrders(@PathVariable int ordersId) {
        return ordersService.removeById(ordersId);
    }

    //通过订单更新库存
    //处理订单
    @PostMapping("/orders/{ordersId}/updateStock")
    public boolean updateStockByOrdersId(@PathVariable int ordersId) {
        return ordersService.updateStockByOrdersId(ordersId);
    }

    //通过ID获取订单项目
    @GetMapping("/ordersitems/{ordersItemId}")
    public OrdersItem getOrdersItemById(@PathVariable int ordersItemId) {
        return ordersItemService.getById(ordersItemId);
    }

    //通过订单ID获取入库单项目
    @GetMapping("/ordersitems/orders/{ordersId}")
    public List<OrdersItem> getOrdersItemByOrdersId(@PathVariable int ordersId) {
        System.out.println("qeqeqweqweqweqweqweq");
        System.out.println(ordersId);
        return ordersItemService.getOrdersItemByOrdersId(ordersId);
    }

    //获取所有订单项目
    @GetMapping("/ordersitems/")
    public List<OrdersItem> getAllOrdersItems() {
        return ordersItemService.list();
    }

    //添加订单项目
    //添加订单项目returnQuantity设置为0
    @PostMapping("/ordersitems/")
    public boolean addOrdersItem(@RequestBody OrdersItem ordersItem) {
        return ordersItemService.save(ordersItem);
    }

    //更新订单项目
    //如果需要退货，修改returnQuantity，触发器会自动修改stock
    @PutMapping("/ordersitems/")
    public boolean updateOrdersItem(@RequestBody OrdersItem ordersItem) {
        return ordersItemService.updateById(ordersItem);
    }

    //删除订单项目
    @DeleteMapping("/ordersitems/{ordersItemId}")
    public boolean deleteOrdersItem(@PathVariable int ordersItemId) {
        return ordersItemService.removeById(ordersItemId);
    }
}
