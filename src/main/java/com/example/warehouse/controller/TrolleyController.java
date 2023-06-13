package com.example.warehouse.controller;
import com.example.warehouse.entity.Trolley;
import com.example.warehouse.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TrolleyController {
    @Autowired
    private TrolleyService trolleyService;
    //通过id获取小车
    @GetMapping("/trolleys/{trolley_id}")
    public Trolley getByTrolleyId(@PathVariable int trolley_id){
        return trolleyService.getByTrolleyId(trolley_id);}
    //通过状态获取小车
    @GetMapping("/trolleys/state/{state}")
    public List<Trolley> getByState(@PathVariable String state){
        return trolleyService.getByState(state);
    }
    //通过批次获取小车
    @GetMapping("/trolleys/batch/{batch}")
    public List<Trolley> getByBatch(@PathVariable int batch){
        return trolleyService.getByBatch(batch);
    }
    //通过批次和状态获取小车
    @GetMapping("/trolleys/batch/{batch}/{state}")
    public List<Trolley> getByBatchAndState(@PathVariable int batch,
                                    @PathVariable String state){
        return trolleyService.getByBatchAndState(batch,state);
    }

    //停用小车
    @GetMapping("/trolleys/cancelled/{batch}")
    public boolean cancelledTrolleys(@PathVariable int batch){
        System.out.println("-----------c---------------");
        System.out.println(batch);
        System.out.println("------------c--------------");
        trolleyService.setCancelled(batch);
        return true;
    }

    //启动小车
    @GetMapping("/trolleys/start/{batch}")
    public boolean startTrolleys(@PathVariable int batch){
        trolleyService.setFree(batch);
        return true;
    }

    @GetMapping("/trolleys/")
    public List<Trolley> getAll(){
        return trolleyService.getAll();
    }

    @GetMapping("/trolleys/batchs")
    public List<Integer> getTrolleyBatchs(){
        return trolleyService.getTrolleyBatchs();
    }
    @PostMapping("/trolleys/")
    public boolean addTrolley(@RequestBody Trolley trolley){
        return trolleyService.addTrolley(trolley);
    }
    @PutMapping("/trolleys/")
    public boolean updateTrolley(@RequestBody Trolley trolley){
        return trolleyService.updateByTrolleyId(trolley);
    }
    @DeleteMapping("/trolleys/{trolleyId}")
    public boolean deleteTrolley(@PathVariable int trolleyId){
        return trolleyService.deleteByTrolleyId(trolleyId);
    }


}
