package com.example.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.warehouse.entity.InStock;
import com.example.warehouse.entity.InStockItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InStockItemMapper extends BaseMapper<InStockItem> {
    List<InStockItem> getByInStockId(int inStockId);
}
