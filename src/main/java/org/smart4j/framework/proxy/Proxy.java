package org.smart4j.framework.proxy;

/**
 * Created by Administrator on 2017/3/12.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
