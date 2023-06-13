package com.example.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.warehouse.entity.InStockItem;
import com.example.warehouse.entity.OutStockItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutStockItemMapper extends BaseMapper<OutStockItem> {
    List<OutStockItem> getByOutStockId(int outStockId);
}
