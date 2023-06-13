package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.warehouse.entity.InStock;
import com.example.warehouse.entity.InStockItem;
import com.example.warehouse.entity.Trolley;
import com.example.warehouse.mapper.TrolleyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class TrolleyService {
    int batch= 1;
    @Autowired
    private TrolleyMapper trolleyMapper;
    public boolean addTrolley(Trolley trolley){return trolleyMapper.addTrolley(trolley);}
    public void setFree(int batch){
      trolleyMapper.setFree(batch);
    }
    public void setBusy(int batch){
        trolleyMapper.setBusy(batch);
        //return trolleyMapper.setBusy(batch);
    }
    public void useBattery(int batch){
        //return trolleyMapper.useBattery(batch);
    }
    public void charge(int batch){
        //return trolleyMapper.charge(batch);
    }
    public void setCancelled(int batch){
        System.out.println("-----------s---------------");
        System.out.println(batch);
        System.out.println("------------s--------------");
        trolleyMapper.setCancelled(batch);}
    public boolean deleteByTrolleyId(int trolleyId){
        return trolleyMapper.deleteByTrolleyId(trolleyId);
    }
    public boolean updateByTrolleyId(Trolley trolley){
        return trolleyMapper.updateByTrolleyId(trolley);
    }
    public Trolley getByTrolleyId(int trolley_id){
        return trolleyMapper.getByTrolleyId(trolley_id);
    }
    public List<Trolley> getByState(String state){
        return trolleyMapper.getByState(state);
    }
    public List<Trolley> getByBatch(int batch){
        return trolleyMapper.getByBatch(batch);
    }
    public List<Trolley> getAll(){
        return trolleyMapper.getAll();
    }
    public List<Trolley> getByBatchAndState(int batch, String state){return trolleyMapper.getByBatchAndState(batch, state);}
    public boolean schedule(InStock inStock) throws InterruptedException {
        int product_quantity=0; //商品目前数量
        int trolley_size=0; //小车目前的运力
        int i=0;
        int arr[] = new int[10];
        int arr1[] = new int[10];
        List<Trolley> trolleyList;
        for (InStockItem item : inStock.getInStockItems()) {
            product_quantity=product_quantity+item.getInStockQuantity();
        }//获取商品总数量
        while (product_quantity>0) {
            while (trolley_size < product_quantity && batch <= 10) {
                trolleyList = getByBatch(batch);
                if (trolleyList.get(1).getState() == "free" && trolleyList.get(1).getBattery() != 0) {
                    for (Trolley trolley : trolleyList) {
                        trolley_size = trolley_size + trolley.getSize();
                        arr[i]=batch;
                    }
                    useBattery(batch);
                    setBusy(batch);
                } else if (trolleyList.get(1).getBattery() == 0) {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            charge(batch);
                            setFree(batch);
                        }
                    }, 1000);
                }
                batch = batch + 1;
            }
            Thread.currentThread().sleep(500);
            product_quantity=product_quantity-trolley_size;
            batch=1;
            trolley_size=0;
            for (int j = 0; j < arr.length; j++) {
                setFree(j);
                arr[j]=arr1[j];
            }

        }
        return  true;
    }
    public List<Integer> getTrolleyBatchs(){
        return trolleyMapper.getTrolleyBatchs();
    }

}
