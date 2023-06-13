package com.example.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.warehouse.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    Product selectDetailsById(int productId);

    List<Product> selectDetails();


    List<Product> selectDetailsPage(int pageNum, int pageSize);

    List<Product> getProductBySupplierId(int supplierId);

    int getCount();
}
