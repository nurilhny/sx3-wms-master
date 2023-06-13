package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.InStock;
import com.example.warehouse.entity.InStockItem;
import com.example.warehouse.entity.Stock;
import com.example.warehouse.mapper.InStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InStockService extends ServiceImpl<InStockMapper, InStock> {
    @Autowired
    InStockMapper inStockMapper;
    @Autowired
    StockService stockService;

    public InStock selectDetailsById(int inStockId) {
        return inStockMapper.selectDetailsById(inStockId);
    }

    public List<InStock> selectDetails() {
        return inStockMapper.selectDetails();
    }

    public boolean updateStockByInStockId(int inStockId) {
        InStock inStock = selectDetailsById(inStockId);
        if (!inStock.getInStockStatus().equals("uncompleted")) {
            // 状态是未完成，才能执行更新库存的操作
            return false;
        }
        List<InStockItem> inStockItems = inStock.getInStockItems();
        for (InStockItem item : inStockItems) {
            int productId = item.getProductId();
            int inStockQuantity = item.getInStockQuantity();
            // product在stock表中暂时只能有一条记录，即一个商品只能放在一个位置
            Stock stock = stockService.getOrCreateByProductId(productId);
            int currentQuantity = stock.getStockQuantity();
            int resultQuantity = currentQuantity + inStockQuantity;
            if (resultQuantity > 9999) {
                inStock.setInStockStatus("error");
                saveOrUpdate(inStock);
                return false;
            }
            stock.setStockQuantity(resultQuantity);
            boolean isStockUpdated = stockService.saveOrUpdate(stock);
            if (!isStockUpdated) {
                inStock.setInStockStatus("error");
                saveOrUpdate(inStock);
                return false;
            }
        }
        inStock.setInStockStatus("completed");
        saveOrUpdate(inStock);
        return true;
    }

    public List<InStock> getInStockByCategory(String inStockCategory) {
        return inStockMapper.getInStockByCategory(inStockCategory);
    }

    public List<InStock> getInStockByStatus(String inStockStatus) {
        return inStockMapper.getInStockByStatus(inStockStatus);
    }

    public List<InStock> getInStockByCategoryAndStatus(String inStockCategory, String inStockStatus) {
        return inStockMapper.getInStockByCategoryAndStatus(inStockCategory, inStockStatus);
    }

}
