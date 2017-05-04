package org.smart4j.framework.bean;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/5.
 */
public class FileParam {
    private String fieldName;
    private String fileName;
    private  long fileSize;
    private String contentType;
    private InputStream inputStream;

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {

        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }
}
