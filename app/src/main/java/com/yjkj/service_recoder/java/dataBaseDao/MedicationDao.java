package com.yjkj.service_recoder.java.dataBaseDao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.yjkj.service_recoder.java.dataBaseBean.MedicationBean;

import java.util.List;

@Dao
public interface MedicationDao {

    @Query("SELECT * FROM MedicationBean")
    List<MedicationBean> MedicationBeans();

    @Insert
    void insertAll(MedicationBean... medicationBeans);


    @Delete
    void delete(MedicationBean medicationBean);
}
