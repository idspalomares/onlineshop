package com.app.rccl.shop.controller;

import com.app.rccl.shop.model.Purchase;
import com.app.rccl.shop.model.PurchaseTransaction;
import com.app.rccl.shop.model.User;
import com.app.rccl.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * get all purchase history per user
     * @param request
     * @return
     */
    @GetMapping("/history")
    private ResponseEntity<List<PurchaseTransaction>> getPurchaseHistory(HttpServletRequest request) {
        User user = getUser(request);
        return new ResponseEntity(purchaseService.getPurchaseHistory(user), HttpStatus.OK);
    }

    /**
     * get cart per user
     * @param request
     * @return
     */
    @GetMapping("/cart")
    private ResponseEntity<PurchaseTransaction> getCartTransaction(HttpServletRequest request) {
        User user = getUser(request);
        return new ResponseEntity(purchaseService.getCartPurchases(user), HttpStatus.OK);
    }

    /**
     * get count of current in cart
     * @param request
     * @return
     */
    @GetMapping("/count")
    private ResponseEntity<Integer> getPurchaseCount(HttpServletRequest request) {
        User user = getUser(request);
        return new ResponseEntity(purchaseService.getPurchaseCount(user), HttpStatus.OK);
    }

    /**
     * adding item to cart
     * @param request
     * @param purchase
     * @return
     */
    @PostMapping
    private ResponseEntity addToCart(HttpServletRequest request, @RequestBody Purchase purchase) {
        User user = getUser(request);
        return new ResponseEntity(purchaseService.savePurchase(user, purchase), HttpStatus.OK);
    }

    /**
     * checking out cart
     * @param request
     * @return
     */
    @PutMapping("/checkout")
    private ResponseEntity<PurchaseTransaction> checkout(HttpServletRequest request) {
        User user = getUser(request);
        PurchaseTransaction purchaseTransaction = purchaseService.checkout(user);
        return new ResponseEntity(purchaseTransaction, HttpStatus.OK);
    }

    private User getUser(HttpServletRequest request) {
       return (User) request.getSession().getAttribute("USER");
    }

}
