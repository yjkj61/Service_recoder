package com.yjkj.service_recoder.java.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yjkj.service_recoder.java.dataBaseBean.MedicationBean;
import com.yjkj.service_recoder.java.dataBaseBean.PhysicalExaminationBean;
import com.yjkj.service_recoder.java.dataBaseBean.UserBean;
import com.yjkj.service_recoder.java.dataBaseDao.MedicationDao;
import com.yjkj.service_recoder.java.dataBaseDao.PhysicalExaminationDao;
import com.yjkj.service_recoder.java.dataBaseDao.UserDao;

@Database(entities = {MedicationBean.class, PhysicalExaminationBean.class, UserBean.class}, version = 4,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicationDao medicationDao();
    public abstract PhysicalExaminationDao physicalExaminationDao();

    public abstract UserDao userDao();
}