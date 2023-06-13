package com.example.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.warehouse.entity.InStock;
import com.example.warehouse.entity.OutStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutStockMapper extends BaseMapper<OutStock> {
    OutStock selectDetailsById(int outStockId);

    List<OutStock> selectDetails();

    List<OutStock> getOutStockByCategory(String outStockCategory);

    List<OutStock> getOutStockByCategoryAndStatus(String outStockCategory, String outStockStatus);

    List<OutStock> getOutStockByStatus(String outStockStatus);
}
