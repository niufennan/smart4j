package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/1.
 */
public class Handler {
    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass,Method actionMethod){
        this.controllerClass=controllerClass;
        this.actionMethod=actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
