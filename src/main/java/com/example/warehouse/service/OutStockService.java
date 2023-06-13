package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.InStock;
import com.example.warehouse.entity.OutStock;
import com.example.warehouse.entity.OutStockItem;
import com.example.warehouse.entity.Stock;
import com.example.warehouse.mapper.OutStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutStockService extends ServiceImpl<OutStockMapper, OutStock> {
    @Autowired
    OutStockMapper outStockMapper;
    @Autowired
    StockService stockService;

    public OutStock selectDetailsById(int outStockId) {
        return outStockMapper.selectDetailsById(outStockId);
    }

    public List<OutStock> selectDetails() {
        return outStockMapper.selectDetails();
    }

    public boolean updateStockByOutStockId(int outStockId) {
        OutStock outStock = selectDetailsById(outStockId);
        if (!outStock.getOutStockStatus().equals("uncompleted")) {
            // 状态是未完成，才能执行更新库存的操作
            return false;
        }
        List<OutStockItem> outStockItems = outStock.getOutStockItems();
        for (OutStockItem item : outStockItems) {
            int productId = item.getProductId();
            int outStockQuantity = item.getOutStockQuantity();
            // product在stock表中暂时只能有一条记录，即一个商品只能放在一个位置
            Stock stock = stockService.getOrCreateByProductId(productId);
            int currentQuantity = stock.getStockQuantity();
            int resultQuantity = currentQuantity - outStockQuantity;
            if (resultQuantity > 9999) {
                outStock.setOutStockStatus("error");
                saveOrUpdate(outStock);
                return false;
            }
            stock.setStockQuantity(resultQuantity);
            boolean isStockUpdated = stockService.saveOrUpdate(stock);
            if (!isStockUpdated) {
                outStock.setOutStockStatus("error");
                saveOrUpdate(outStock);
                return false;
            }
        }
        outStock.setOutStockStatus("completed");
        saveOrUpdate(outStock);
        return true;
    }

    public List<OutStock> getOutStockByCategory(String outStockCategory) {
        return outStockMapper.getOutStockByCategory(outStockCategory);
    }

    public List<OutStock> getOutStockByStatus(String outStockStatus) {
        return outStockMapper.getOutStockByStatus(outStockStatus);
    }

    public List<OutStock> getOutStockByCategoryAndStatus(String outStockCategory, String outStockStatus) {
        return outStockMapper.getOutStockByCategoryAndStatus(outStockCategory, outStockStatus);
    }
}
