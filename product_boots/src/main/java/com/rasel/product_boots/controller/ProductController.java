package com.rasel.product_boots.controller;

import com.rasel.product_boots.entity.Product;
import com.rasel.product_boots.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "product")
public class ProductController {
   private final ProductService productService;

   public ProductController(ProductService productService){
       this.productService = productService;
   }

   //post
    @PostMapping("/{post}")
   public Product createProduct(@RequestBody Product product){
       Product create = productService.saveAll(product);
       return create;
   }

   //get
    @GetMapping
    public List<Product> getAll(){
       List<Product> getList = productService.getAll();
       return getList;
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteAll(@PathVariable Long id){
        productService.deleteAll(id);
    }

    //Put
//    @PutMapping("/{id}")
//    public Product updateAll(@PathVariable Long id, @RequestBody Product product){
//      Product update = productService.updateAll(id);
//       if(update != null){
//           update.setNameProduct(product.getNameProduct());
//           update.setPrice(product.getPrice());
//           update.setModel(product.getModel());
//           update.setSellDate(product.getSellDate());
//       }
//       return productService.create(update);
//
//    }


    //Put
    public Product updateAll(@PathVariable Long id, @RequestBody Product product){
       Optional<Product> update = productService.findProductId(id);
       if(update.isPresent()){
           Product existing = update.get();
           existing.setNameProduct(product.getNameProduct());
           existing.setPrice(product.getPrice());
           existing.setModel(product.getModel());
           existing.setSellDate(product.getSellDate());

           return productService.saveAll(existing);
       }else {
           return null;
       }
    }
}
