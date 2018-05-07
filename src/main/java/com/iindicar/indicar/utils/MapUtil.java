package com.iindicar.indicar.utils;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yeseul on 2018-05-04.
 */

public class MapUtil {

    /**
     * 맵(Map)을 키(key)를 기준으로 정렬하는 메소드
     * @param map 정렬 전
     * @param isASC true: 오름차순 / false: 내림차순
     * @return 정렬 후 map
     * */
    public static Map<Object, Object> sortByKey(final Map map, boolean isASC){
        if(isASC){
            return new TreeMap<Object, Object>(map);
        } else {
            TreeMap<Object, Object> treeMap = new TreeMap<>(Collections.reverseOrder());
            treeMap.putAll(map);
            return treeMap;
        }
    }
}
