package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("supplier")
public class Supplier {
    @TableId(type = IdType.AUTO)
    private int supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone;
}
