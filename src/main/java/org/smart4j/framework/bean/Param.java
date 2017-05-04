package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/1.
 */
public class Param {

    private List<FormParam> formParamList;
    private List<FileParam> fileParamsList;

    public Param(List<FormParam> formParamList){
        this.formParamList=formParamList;
    }
    public Param(List<FormParam> formParamList,List<FileParam> fileParamsList){
        this.formParamList=formParamList;
        this.fileParamsList=fileParamsList;
    }
    //请求参数
    public Map<String,Object> getFieldMap(){
        Map<String,Object> fieldMap=new HashMap<String,Object>();
        if(CollectionUtil.isNotEmpty(formParamList)){
            for(FormParam formParam:formParamList){
                String fieldName=formParam.getFieldName();
                Object fieldValue=formParam.getFieldValue();
                if(fieldMap.containsKey(fieldName)){
                    fieldValue=fieldMap.get(fieldName)+ StringUtil.SEPARATOR+fieldValue;
                }
                fieldMap.put(fieldName,fieldValue);
            }
        }
        return fieldMap;
    }
    //上传文件
    public  Map<String,List<FileParam>> getFileMap(){
        Map<String,List<FileParam>> fileMap=new HashMap<String, List<FileParam>>();
        if(CollectionUtil.isNotEmpty(fileParamsList)){
            for (FileParam fileParam:fileParamsList){
                String fieldName=fileParam.getFieldName();
                List<FileParam> fileParamList;
                if(fileMap.containsKey(fieldName)){
                    fileParamList=fileMap.get(fieldName);
                }else{
                    fileParamList=new ArrayList<FileParam>();
                }
                fileParamList.add(fileParam);
                fileMap.put(fieldName,fileParamList);
            }
        }
        return fileMap;
    }

    //获取文件
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }
    public FileParam getFile(String fieldName){
        List<FileParam> fileParamList=getFileList(fieldName);
        if(CollectionUtil.isNotEmpty(fileParamList)&&fileParamList.size()==1){
            return fileParamList.get(0);
        }
        return  null;
    }

    public boolean isEmpty(){
        return CollectionUtil.isEmpty(formParamList)&&CollectionUtil.isNotEmpty(fileParamsList);
    }

    public String getString(String name){
        return CastUtil.castString(getFieldMap().get(name));
    }

    public double getDouble(String name){
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFieldMap().get(name));
    }
    public long getLong(String name){
        return CastUtil.castLong(getFieldMap().get(name));
    }


    //private Map<String,Object> paramMap;
    //public Param(Map<String,Object> paramMap){
    //    this.paramMap=paramMap;
    //}

    //public Map<String,Object> getMap(){
    //    return paramMap;
    //}

    //public boolean isEmpty(){
    //    return CollectionUtil.isEmpty(paramMap);
    //}
}
