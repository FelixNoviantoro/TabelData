package com.example.practice.ui;
import com.example.practice.model.Product;
import com.example.practice.model.Login;
import com.example.practice.repository.MasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class Home {

    @Autowired
    private MasterData masterData;

    @GetMapping("/productlist")
    public String getProductList(@RequestParam(required = false, name = "cari") Integer cari, ModelMap mapParam
    ) {
        mapParam.put("cek", masterData.fetchProductAll(cari));
        return "productlist";
    }

    @GetMapping("/insertproduct")
    public String insertProduct(Model model) {
        model.addAttribute(new Product());
        return "insertproduct";
    }

    @PostMapping("/updateproduct")
    public String updateProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "insertproduct";
        } else {
            if (product.getProductId() > 0) {
                masterData.updateProduct(product);
            } else {
                masterData.insertproduct(product);
            }

            return "productlist";
        }
    }

    @GetMapping("/updateproduct/{id}")
    public String updateKategori(@PathVariable("id") Integer id, Model model){
        model.addAttribute("product",masterData.fetchProductById(id));
        return "insertproduct";
    }

    @GetMapping("deleteproduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        masterData.deleteProduct(id);
        return "redirect:../productlist";
    }

    @GetMapping("api/productlistjson")
    public ResponseEntity<List<Product>> getProductList(@RequestParam(required = false, name = "cari") Integer cari
    ) {
        return ResponseEntity.ok(masterData.fetchProductAll(cari));
    }

    @PostMapping("api/saveproductjson")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        
            masterData.updateProduct(product);
            return ResponseEntity.ok(product);
        
    }

    @PostMapping("api/insertproductjson")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        
            masterData.insertproduct(product);
            return ResponseEntity.ok(product);
        
    }

    @GetMapping("api/editproductjson/{id}")
        public ResponseEntity<Product> editjson(
            @PathVariable("id") Integer id
            ) {
                return ResponseEntity.ok(masterData.fetchProductById(id));
            }

    @GetMapping("api/deleteproductjson/{id}")
    public ResponseEntity<List<Product>> deleteProductjson(@PathVariable("id") Integer id){
        masterData.deleteProduct(id);
        return ResponseEntity.ok(masterData.fetchProductAll(null));
    }

    // @PostMapping("api/getuser")
    // public ResponseEntity<Login> getUsernData(){
    //     return ResponseEntity.ok(masterData.getUser("ABC", "ABCD"));
    // }

    // @PostMapping("/api/login")
    // public ResponseEntity<Map> loginForm(
    //     @RequestBody Login login, 
    //     BindingResult bindingResult){

    //     Map<String, Object> status = new HashMap<>();
    //     if(login != null && Objects.equals(login.getUsername(),"ABC")
    //     && Objects.equals(login.getPassword(), "12345")) {
    //         status.put("status", true);
    //     } else {
    //         status.put("status", false);
    //     }
    //     return ResponseEntity.ok(status);
    // }

    @PostMapping("/api/login/{username}")
    public ResponseEntity<Login> loginForm(
        @PathVariable("username") String username, 
        @RequestBody Login login, 
        BindingResult bindingResult){

        Login user = masterData.getUser(username);
        if(login != null && Objects.equals(login.getUsername(), user.getUsername())
        && Objects.equals(login.getPassword(), user.getPassword())) {
            user.setIsLogin(true);
            masterData.updateJam(user);
            masterData.updateIsLogin(user);
        } else {
            user.setIsLogin(false);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/logout/{username}")
    public ResponseEntity<Login> logoutForm(
        @PathVariable("username") String username, 
        @RequestBody Login login, 
        BindingResult bindingResult){

        Login user = masterData.getUser(username);
            user.setIsLogin(false);
            masterData.updateJam(user);
            masterData.updateIsLogin(user);
        return ResponseEntity.ok(user);
    }

}


