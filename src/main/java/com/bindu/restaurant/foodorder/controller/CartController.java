package com.bindu.restaurant.foodorder.controller;

import com.bindu.restaurant.foodorder.entity.Menu;
import com.bindu.restaurant.foodorder.global.GlobalData;
import com.bindu.restaurant.foodorder.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    MenuService menuService;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable int id){
        Menu menu=menuService.findById(id);
        if( GlobalData.cart.containsKey(menu)){
            GlobalData.cart.put(menu,GlobalData.cart.get(menu)+1);
        }
        else
            GlobalData.cart.put(menu,1);
        return "redirect:/home";
    }

    @GetMapping("/viewCart")
    public String viewCart(Model theModel){
        theModel.addAttribute("cart",GlobalData.cart);
        double sum=0.0;
        for(Menu menu:GlobalData.cart.keySet()){
            sum=sum+(GlobalData.cart.get(menu) * menu.getPrice());
        }
        theModel.addAttribute("total", sum);

        return "show-cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable int id){
        Menu menu= menuService.findById(id);
        GlobalData.cart.remove(menu);
        return "redirect:/cart/viewCart";
    }
}
