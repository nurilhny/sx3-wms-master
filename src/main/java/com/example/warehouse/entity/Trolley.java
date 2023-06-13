package com.example.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("trolley")
public class Trolley {
    @TableId(type = IdType.AUTO)
    private int trolleyId;
    private int battery;
    private String state;
    private int size;
    private int batch;
}
