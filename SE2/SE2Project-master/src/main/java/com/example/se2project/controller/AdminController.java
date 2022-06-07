package com.example.se2project.controller;//package com.onlineshopping.se2finalproject.Controller;
//
//import com.onlineshopping.se2finalproject.Entity.Product;
//import com.onlineshopping.se2finalproject.Entity.User;
//import com.onlineshopping.se2finalproject.Service.ProductService;
//import com.onlineshopping.se2finalproject.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//public class AdminController {
//    public static String uploadDirectory = System.getProperty("product.dir") + "/src/main/webapp/image";
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private UserRespository userRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @RequestMapping("/admin/users")
//    public String showUserList(Model model) {
//        List<User> userList = userService.listAllUser();
//        model.addAttribute("userList", userList);
//        return "userList";
//    }
//
//    @GetMapping("/product")
//    public String showProduct(Model model) {
//        List<Product> products = productService.listAllProduct();
//        model.addAttribute("products", products);
//        return "view-products";
//    }
//
//    @RequestMapping("/register")
//    public String showRegisterForm(Model model) {
//        model.addAttribute("user", new User());
////        model.addAttribute("pageTitle", "REGISTER");
//        return "registerUser";
//    }
//
//    @RequestMapping("/saveUser")
//    public String saveUser(@RequestParam(value = "id", required = false) Long id,
//                           User user) {
//        user.setId(id);
//        userRepository.save(user);
//        return "redirect:/";
//    }
//
//    @RequestMapping("/admin/addProduct")
//    public String addProductForm(Model model) {
////        model.addAttribute("product",new ProductDto());
//        return "productAdd";
//    }
//
//    @PostMapping("admin/addProduct/save")
//    public String saveProduct(@RequestParam(value = "name", required = false, defaultValue = "empty name") String name,
//                              @RequestParam(value = "price", required = false, defaultValue = "0") double price,
//                              @RequestParam(value = "detail", required = false, defaultValue = "default detail") String detail,
//                              @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
////        if(bindingResult.hasErrors()){
////            ObjectError error = new ObjectError("error", "Error when create new!");
////            bindingResult.addError(error);
////            return "productAdd";
////        }
//
////        if (FileUtil.saveImage(image)) {
////            Product product = Product.builder()
////                    .name(name)
////                    .detail(detail)
////                    .price(price)
////                    .image("/image/" + image.getOriginalFilename())
////                    .build();
////            productService.insert(product);
////            return "redirect:/";
////        } else {
////            return "redirect:/admin/addProduct";
////        }
//
//        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//        Product product = new Product();
//        product.setName(name);
//        product.setDetail(detail);
//        product.setPrice(price);
//        product.setImage(fileName);
//        Product savedProduct = productService.insert(product);
//        String uploadDir = "product-image/" + savedProduct.getProductId();
//        Path uploadPath = Paths.get(uploadDir);
//        if(!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//        try {
//            InputStream inputStream = image.getInputStream();
//            Path filePath = uploadPath.resolve(fileName);
//            System.out.println(filePath.toFile().getAbsolutePath());
//            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
//        }catch (IOException e) {
//            throw new IOException("could not save uploaded file: " + fileName);
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/")
//    public String home(Model model){
//
//        List<Product> products = productService.listAllProduct();
//        List<String> urls = products.stream().map(Product::getImage).collect(Collectors.toList());
//        model.addAttribute("imgUrls", urls);
//        return "home";
//    }
//
//}
