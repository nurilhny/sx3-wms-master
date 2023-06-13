package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.InStockItem;
import com.example.warehouse.entity.OutStockItem;
import com.example.warehouse.mapper.InStockItemMapper;
import com.example.warehouse.mapper.OutStockItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutStockItemService extends ServiceImpl<OutStockItemMapper, OutStockItem> {
    @Autowired
    private OutStockItemMapper outStockItemMapper;

    public List<OutStockItem> getByOutStockId(int outStockId) {
        return outStockItemMapper.getByOutStockId(outStockId);
    }

}
