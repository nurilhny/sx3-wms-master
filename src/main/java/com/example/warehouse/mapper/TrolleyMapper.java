package com.example.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.warehouse.entity.Trolley;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrolleyMapper extends BaseMapper<Trolley> {
    @Insert("insert into trolley(battery,state,`size`,batch) values(#{battery},#{state},#{size},#{batch})")
    boolean addTrolley(Trolley trolley);

    @Update("update trolley set state='free' where batch=#{batch}")
    void setFree(int batch);

    @Update("update trolley set state='busy' where batch=#{batch}")
    void setBusy(int batch);

    @Update("update trolley set state='cancelled' where batch=#{batch}")
    void setCancelled(int batch);

    @Update("update trolley set battery=battery-1 where batch=#{batch}")
    void useBattery(int batch);

    @Update("update trolley set battery=#{battery},state=#{state},size=#{size},batch=#{batch} where trolley_id=#{trolley_id}")
    boolean updateByTrolleyId(Trolley trolley);

    @Update("update trolley set battery=100 where batch=#{batch}")
    void charge(int batch);

    @Delete("delete from trolley where trolley_id=#{trolley_id}")
    boolean deleteByTrolleyId(int trolley_id);

    @Select("select * from trolley where trolley_id=#{trolley_id}")
    Trolley getByTrolleyId(int trolley_id);

    @Select("select * from trolley where state=#{state}")
    List<Trolley> getByState(String state);

    @Select("select * from trolley where batch=#{batch}")
    List<Trolley> getByBatch(int batch);

    @Select("select * from trolley")
    List<Trolley> getAll();

    @Select("select * from trolley where batch=#{batch} and state=#{state}")
    List<Trolley> getByBatchAndState(int batch, String state);

    @Select("select distinct batch from trolley where state='free'")
    List<Integer> getTrolleyBatchs();
}
