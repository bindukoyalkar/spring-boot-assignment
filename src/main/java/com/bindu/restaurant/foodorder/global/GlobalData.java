package com.bindu.restaurant.foodorder.global;

import com.bindu.restaurant.foodorder.entity.Menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalData {
    private GlobalData(){

    }

    public static final Map<Menu,Integer> cart;

    static{
        cart=new LinkedHashMap<>();
    }
}
