package com.app.rccl.shop.repository;

import com.app.rccl.shop.model.PurchaseTransaction;
import com.app.rccl.shop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseTransactionRepository extends ShopRepository<PurchaseTransaction> {

    @Query("select pt from PurchaseTransaction pt where pt.user = :user and pt.invoiceCode is NULL")
    PurchaseTransaction findByUserCart(@Param("user") User user);

    @Query("select pt from PurchaseTransaction pt where pt.user = :user and pt.invoiceCode is not NULL order by pt.transactionDate asc")
    List<PurchaseTransaction> findByUserPurchaseHistory(@Param("user") User user);

}
