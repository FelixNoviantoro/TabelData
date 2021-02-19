package com.example.practice.ui;
import com.example.practice.model.Product;
import com.example.practice.repository.MasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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

    @PostMapping("/saveproduct")
    public String saveProduct(
            @ModelAttribute Product product,
            @RequestParam(required = false, name = "cari") Integer cari,
            ModelMap mapParam
    ) {
        System.out.println(product.getProductId());
        masterData.insertproduct(product);
        cari = product.getProductId();
        mapParam.put("cek", masterData.fetchProductAll(cari));
        return "productlist";
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
    public String deleteProduct(@PathVariable("id") Integer id, Model model){
        model.addAttribute("product", masterData.deleteProduct(id));
        return "updateproduct";
    }
}


