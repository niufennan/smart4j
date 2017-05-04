package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/16.
 */
public class ServletHelper {
    private static final Logger LOGGER= LoggerFactory.getLogger(ServletHelper.class);
    private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER=new ThreadLocal<ServletHelper>();
    private HttpServletRequest request;
    private HttpServletResponse response;

    private ServletHelper(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    public static void init(HttpServletRequest request,HttpServletResponse response){
        SERVLET_HELPER_HOLDER.set(new ServletHelper(request,response));
    }
    public  static void destroy(){
        SERVLET_HELPER_HOLDER.remove();
    }
    private static HttpServletRequest getRequest(){
        return SERVLET_HELPER_HOLDER.get().request;
    }
    private static HttpServletResponse getResponse(){
        return SERVLET_HELPER_HOLDER.get().response;
    }
    private static HttpSession getSession(){
        return getRequest().getSession();
    }
    private static ServletContext getServletContext(){
        return getRequest().getServletContext();
    }
    public  static void setRequestAttribute(String key,Object value){
        getRequest().setAttribute(key,value);
    }
    public static <T> T getRequestAttribute(String key){
        return (T)getRequest().getAttribute(key);
    }

    public static void  removeRequestAttribute(String key){
        getRequest().removeAttribute(key);
    }

    public static void sendRedirect(String location){
        try {
            getResponse().sendRedirect(getRequest().getContextPath()+location);
        } catch (IOException e) {
            LOGGER.error("redirect failure",e);
        }
    }

    public static void setSessionAttribute(String key,Object value){
        getSession().setAttribute(key,value);
    }
    public static <T> T getSessionAttribute(String key){
        return (T) getRequest().getSession().getAttribute(key);
    }
    public static void removeSessionAttribute(String key){
        getRequest().getSession().removeAttribute(key);
    }

    public static void invalidateSession(){
        getRequest().getSession().invalidate();
    }
}
