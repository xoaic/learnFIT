//package com.example.se2project.util;
//
//import com.example.se2project.entity.Cart;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class Utils {
//    public static Cart getCartInSession(HttpServletRequest request) {
//        Cart cart = (Cart) request.getSession().getAttribute("myCart");
//        if(cart == null) {
//            cart = new Cart();
//            request.getSession().setAttribute("myCart", cart);
//        }
//        return cart;
//    }
//    public static void removeCartInSession(HttpServletRequest request) {
//        request.getSession().removeAttribute("myCart");
//    }
//}
