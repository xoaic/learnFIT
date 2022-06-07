package com.example.se2project.controller;
import com.example.se2project.entity.CartProduct;
import com.example.se2project.entity.Category;
import com.example.se2project.entity.Product;
import com.example.se2project.entity.User;
import com.example.se2project.entity.dto.ProductDto;
import com.example.se2project.service.CategoryService;
import com.example.se2project.service.ProductService;
import com.example.se2project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("userId")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @GetMapping("/product/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("products",new ProductDto());
        model.addAttribute("categories",categoryService.findAll());
        return "adminPages/products/productAdd";
    }
    @GetMapping("/product/category/{id}")
    public String productByCategory(@PathVariable Long id, Model model) {
        model.addAttribute("allCategory", categoryService.findAll());
//        model.addAttribute("cartCount")?
        model.addAttribute("products", productService.getProductByCategoryId(id));
        return "homePage";
    }
    @GetMapping("/product/addCategory")
    public String addCategoryForm(Model model) {
        model.addAttribute("category",new Category());
        return "categoryAdd";
    }
    @PostMapping("/addCategory/save")
    public String saveCategory(@RequestParam(value = "name", required = false, defaultValue = "empty name") String name,
                               @RequestParam(value = "description", required = false, defaultValue = "empty description") String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryService.add(category);
        return "redirect:/";
    }
    @PostMapping("/addProduct/save")
    public String saveProduct(@ModelAttribute("products") ProductDto productDto,
                              @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        Product product = new Product();
        product.setProductId(productDto.getId());
        product.setName(productDto.getName());
        product.setDetail(productDto.getDetail());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findById(productDto.getCategoryId()).get());
        product.setImage(fileName);
        Product savedProduct = productService.add(product);
        String uploadDir = "product-image/" + savedProduct.getProductId();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try {
            InputStream inputStream = image.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) {
            throw new IOException("could not save uploaded file: " + fileName);
        }
        return "redirect:/";
    }
    @GetMapping("/product/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id,
                             HttpServletRequest servletRequest, HttpSession session) {

//        User u = (User) model.getAttribute("userId");
        Long ids = (Long) servletRequest.getSession().getAttribute("userId");
        System.out.println(getUser(ids));
        if(session.getAttribute("cart") == null) {
            List<CartProduct> cartProductList = new ArrayList<CartProduct>();
            cartProductList.add(new CartProduct(productService.findById(id).get(), getUser(ids), 1));
            System.out.println(cartProductList);
            session.setAttribute("cart", cartProductList);
//
        }else {
            List<CartProduct> cartProducts = (List<CartProduct>) session.getAttribute("cart");
            int index = isExisted(id, cartProducts);
            if(index == -1) {
                cartProducts.add(new CartProduct(productService.findById(id).get(), getUser(ids),1));
            }else{
                int quantity = cartProducts.get(index).getQuantity() + 1;
                cartProducts.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cartProducts);
        }
        return "redirect:/cartProductList";
    }
//    @GetMapping("/")
//    public String viewProduct(Model model) {
//        List<Product> products = productService.findAll();
//        if(!products.isEmpty()){
//            model.addAttribute("products", products);
//            model.addAttribute("allCategory", categoryService.findAll());
//        }
//        return "homePage";
//    }
//    @GetMapping(value = "/product/{id}")
//    public String getProductById(@PathVariable(value = "id") Long id, Model model) {
////        Employee employee = employeeRepository.getById(id);
////        model.addAttribute("employee", employee);
//        return "employeeDetail";
//    }
    public User getUser(@SessionAttribute("userId") Long userId) {
        return userService.findById(userId).get();
    }
    public int isExisted(Long id, List<CartProduct> cartProducts) {
        for(int i = 0; i < cartProducts.size();i++) {
            if(cartProducts.get(i).getProduct().getProductId() == id) {
                return i;
            }
        }
        return -1;
    }

}
