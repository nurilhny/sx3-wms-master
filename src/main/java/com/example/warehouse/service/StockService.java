package com.example.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.Stock;
import com.example.warehouse.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService extends ServiceImpl<StockMapper, Stock> {
    @Autowired
    StockMapper stockMapper;

    public int getStockIdByProductId(int productId) {
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);
        Stock stock = stockMapper.selectOne(wrapper);
        if (stock == null) {
            return -1; // 库存不存在
        } else {
            return stock.getStockId();
        }
    }

    public Stock getOrCreateByProductId(int productId) {
        int stockId = getStockIdByProductId(productId);
        Stock stock = getById(stockId);
        if (stock == null) {
            // 如果对应的stock记录不存在，则创建一个新的stock记录
            stock = new Stock();
            stock.setProductId(productId);
            stock.setPosition("A1");
        }
        return stock;
    }


    public List<Stock> getStockByPosition(String position){return stockMapper.getStockByPosition(position);}
}
