package com.example.warehouse.controller;

import com.example.warehouse.entity.InStockItem;
import com.example.warehouse.entity.OutStock;
import com.example.warehouse.entity.OutStockItem;
import com.example.warehouse.service.OutStockItemService;
import com.example.warehouse.service.OutStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OutStockController {
    @Autowired
    private OutStockService outStockService;
    @Autowired
    private OutStockItemService outStockItemService;

    //通过ID获取出库单
    @GetMapping("/outstocks/{outStockId}")
    public OutStock getOutStockById(@PathVariable int outStockId) {
        return outStockService.selectDetailsById(outStockId);
    }

    //通过类型获取出库单
    @GetMapping("/outstocks/category/{outStockCategory}")
    public List<OutStock> getOutStockByCategory(@PathVariable String outStockCategory) {
        return outStockService.getOutStockByCategory(outStockCategory);
    }

    //通过状态获取入库单
    @GetMapping("/outstocks/status/{outStockStatus}")
    public List<OutStock> getOutStockByStatus(@PathVariable String outStockStatus) {
        return outStockService.getOutStockByStatus(outStockStatus);
    }

    //通过类型+状态获取入库单
    @GetMapping("/outstocks/category/{outStockCategory}/{outStockStatus}")
    public List<OutStock> getOutStockByCategoryAndStatus(@PathVariable String outStockCategory,
                                                       @PathVariable String outStockStatus) {
        return outStockService.getOutStockByCategoryAndStatus(outStockCategory,outStockStatus);
    }

    //获取所有出库单
    @GetMapping("/outstocks/")
    public List<OutStock> getAllOutStocks() {
        return outStockService.selectDetails();
    }

    //添加出库单
    @PostMapping("/outstocks/")
    public boolean addOutStock(@RequestBody OutStock outStock) {
        System.out.println("qweqweqweqweqweqwe");
        System.out.println(outStock.getUserId());


        Boolean flag1 = outStockService.save(outStock);
        List<OutStock> outStockList = outStockService.selectDetails();
        int outStockId = 0;
        for (OutStock outS:outStockList
        ) {
            if(outStockId<outS.getOutStockId()){
                outStockId = outS.getOutStockId();
            }

        }

        // 保存outStockId
        for (OutStockItem outStockItem:outStock.getOutStockItems()
        ) {
            outStockItem.setOutStockId(outStockId);
            Boolean flag2 = outStockItemService.save(outStockItem);
            if(!flag2){
                return flag2;
            }
        }

        // 更新库存
        outStockService.updateStockByOutStockId(outStockId);
        return flag1;
    }

    //更新出库单
    @PutMapping("/outstocks/")
    public boolean updateOutStock(@RequestBody OutStock outStock) {
        return outStockService.updateById(outStock);
    }

    //删除出库单
    @DeleteMapping("/outstocks/{outStockId}")
    public boolean deleteOutStock(@PathVariable int outStockId) {
        return outStockService.removeById(outStockId);
    }

    //通过出库单更新库存
    @PostMapping("/outstocks/{outStockId}/updateStock")
    public boolean updateStockByOutStockId(@PathVariable int outStockId) {
        return outStockService.updateStockByOutStockId(outStockId);
    }

    //通过ID获取出库单项目
    @GetMapping("/outstockitems/{outStockItemId}")
    public OutStockItem getOutStockItemById(@PathVariable int outStockItemId) {
        return outStockItemService.getById(outStockItemId);
    }

    //获取所有出库单项目
    @GetMapping("/outstockitems/")
    public List<OutStockItem> getAllOutStockItems() {
        return outStockItemService.list();
    }

    //通过入库单ID获取入库单项目
    @GetMapping("/outstockitems/outstock/{outStockId}")
    public List<OutStockItem> getOutStockItemByOutStockId(@PathVariable int outStockId) {
        System.out.println("qeqeqweqweqweqweqweq");
        System.out.println(outStockId);
        return outStockItemService.getByOutStockId(outStockId);
    }

    //添加出库单项目
    @PostMapping("/outstockitems/")
    public boolean addOutStockItem(@RequestBody OutStockItem outStockItem) {
        return outStockItemService.save(outStockItem);
    }

    //更新出库单项目
    @PutMapping("/outstockitems/")
    public boolean updateOutStockItem(@RequestBody OutStockItem outStockItem) {
        return outStockItemService.updateById(outStockItem);
    }

    //删除出库单项目
    @DeleteMapping("/outstockitems/{outStockItemId}")
    public boolean deleteOutStockItem(@PathVariable int outStockItemId) {
        return outStockItemService.removeById(outStockItemId);
    }
}
