package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.Product;
import com.example.warehouse.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {
    @Autowired
    ProductMapper productMapper;

    public Product selectDetailsById(int productId) {
        return productMapper.selectDetailsById(productId);
    }

    public List<Product> selectDetails() {
        return productMapper.selectDetails();
    }

    public int getCount() {
        return productMapper.getCount();
    }

    public List<Product> selectDetailsPage(int pageNum, int pageSize) {
        return productMapper.selectDetailsPage(pageNum, pageSize);
    }

    public List<Product> getProductBySupplierId(int supplierId){
        return productMapper.getProductBySupplierId(supplierId);
    }
}
