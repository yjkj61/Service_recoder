package com.yjkj.service_recoder.java.utils;

import androidx.room.TypeConverter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StringListConverter {

    @TypeConverter
    public String objToString(List<String> list){
        return JsonUtils.stringify(list);
    }

    @TypeConverter
    public  List<String> stringToObject (String json){
        Type type = new TypeToken<List<String>>(){}.getType();
        return JsonUtils.gson.fromJson(json,type);
    }
}
