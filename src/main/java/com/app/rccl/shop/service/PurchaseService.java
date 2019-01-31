package com.app.rccl.shop.service;

import com.app.rccl.shop.model.Purchase;
import com.app.rccl.shop.model.PurchaseTransaction;
import com.app.rccl.shop.model.User;

import java.util.List;

public interface PurchaseService {

    PurchaseTransaction getCartPurchases(User user);

    List<PurchaseTransaction> getPurchaseHistory(User user);

    PurchaseTransaction savePurchase(User user, Purchase purchase);

    PurchaseTransaction checkout(User user);

    Integer getPurchaseCount(User user);

}
