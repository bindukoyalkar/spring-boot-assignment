package com.bindu.restaurant.foodorder.global;

import com.bindu.restaurant.foodorder.entity.Menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GlobalData {
    public static Map<Menu,Integer> cart;
    private static final Logger logger = Logger.getLogger(GlobalData.class.getName());

    static{
        cart=new LinkedHashMap<Menu,Integer>();
       /* logger.info("==========================>>>>>>");
        logger.info(cart.toString());
        logger.info("==========================>>>>>>");*/
    }
}
