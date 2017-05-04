package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by Administrator on 2017/3/1.
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[]array){
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object []array){
        return ArrayUtils.isEmpty(array);
    }
}
