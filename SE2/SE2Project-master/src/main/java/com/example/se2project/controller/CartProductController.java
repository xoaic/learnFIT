package com.example.se2project.controller;

import com.example.se2project.entity.CartProduct;
import com.example.se2project.repository.CartProductRepository;
import com.example.se2project.service.CartProductService;
import com.example.se2project.service.ProductService;
import com.example.se2project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("userId")
public class CartProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CartProductService cartProductService;

    @Autowired
    CartProductRepository cartProductRepository;
    @Autowired
    UserService userService;

    @GetMapping("/cart")
    public String cartProductList(Model model, HttpServletRequest servletRequest) {
        Long ids = (Long) servletRequest.getSession().getAttribute("userId");
        List<CartProduct> cartProducts = cartProductService.getCartProduct(ids);
        model.addAttribute("cartProducts", cartProducts);

        return "cartPage";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateCart(HttpServletRequest request,
                             HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");
        List<CartProduct> cartProducts = (List<CartProduct>) session.getAttribute("cart");
        for(int i = 0; i < cartProducts.size();i++) {
            cartProducts.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cartProducts", cartProducts);
        return "redirect:/cart";
    }

    private double toTal(HttpSession session) {
        List<CartProduct> cartProductList = (List<CartProduct>) session.getAttribute("cartProducts");
        double total = 0;
        for(CartProduct cartProduct: cartProductList) {
            total += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
        }
        return total;
    }
    @GetMapping()
    public String cart(ModelMap modelMap, HttpSession session) {
        modelMap.put("total", toTal(session));
        return "cartPage";
    }

//    @GetMapping("/product/cartProduct/add/{productId}")
////    @SessionAttributes("userId")
//    public String addCartProduct(@PathVariable("productId") Long productId,
//                                 @SessionAttribute("userId") Long userId
//
//    ) {
//        Optional<Product> product = productService.findById(productId);
//        User u = userService.findById(userId).get();
//
//        if (product.isPresent()) {
//            CartProduct cartProduct = CartProduct.builder()
////                    .name(product.get().getName())
////                    .detail(product.get().getDetail())
////                    .image(product.get().getImagePath())
////                    .price(product.get().getPrice())
////                    .user(u)
//
//                    .build();
//
//
//
//            cartProduct.setQuantity(1);
//            cartProductRepository.save(cartProduct);
//
//        }
//        return "redirect:/cartProductList";
//    }




}
