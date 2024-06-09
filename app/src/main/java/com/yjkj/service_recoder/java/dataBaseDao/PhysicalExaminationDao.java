package com.yjkj.service_recoder.java.dataBaseDao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.yjkj.service_recoder.java.dataBaseBean.PhysicalExaminationBean;

import java.util.List;

@Dao
public interface PhysicalExaminationDao {

    @Query("SELECT * FROM PhysicalExaminationBean")
    List<PhysicalExaminationBean> PhysicalExaminationBeans();

    @Insert
    void insertAll(PhysicalExaminationBean... physicalExaminationBeans);


    @Delete
    void delete(PhysicalExaminationBean physicalExaminationBean);
}
