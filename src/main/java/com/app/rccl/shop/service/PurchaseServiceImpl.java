package com.app.rccl.shop.service;

import com.app.rccl.shop.model.Product;
import com.app.rccl.shop.model.Purchase;
import com.app.rccl.shop.model.PurchaseTransaction;
import com.app.rccl.shop.model.User;
import com.app.rccl.shop.repository.PurchaseRepository;
import com.app.rccl.shop.repository.PurchaseTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseTransactionRepository transactionRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public PurchaseTransaction getCartPurchases(User user) {
        return transactionRepository.findByUserCart(user);
    }

    @Override
    public List<PurchaseTransaction> getPurchaseHistory(User user) {
        return transactionRepository.findByUserPurchaseHistory(user);
    }

    @Override
    @Transactional
    public PurchaseTransaction savePurchase(User user, Purchase purchase) {
        PurchaseTransaction purchaseTransaction = transactionRepository.findByUserCart(user);

        if (purchaseTransaction == null) {
            return saveNewTransaction(user, purchase);
        } else {
            boolean exists = false;

            for (Purchase pc: purchaseTransaction.getPurchases()) {
                if (purchase.getProduct().getId().equals(pc.getProduct().getId())) {
                    pc.setQuantity(pc.getQuantity() + 1);
                    exists = true;
                }
            }

            if (!exists) {
                purchase.setQuantity(1);
                Purchase newPurchase = purchaseRepository.save(purchase);
                purchaseTransaction.getPurchases().add(newPurchase);
            }

            return purchaseTransaction;
        }
    }

    @Override
    @Transactional
    public PurchaseTransaction checkout(User user) {
        Date transactionDate = new Date();
        PurchaseTransaction purchaseTransaction = transactionRepository.findByUserCart(user);
        purchaseTransaction.setTransactionDate(transactionDate);
        purchaseTransaction.setInvoiceCode(generateInvoice(transactionDate, purchaseTransaction.getId().toString()));
        purchaseTransaction.setTotalPrice(getTotalPrice(purchaseTransaction.getPurchases()));
        return purchaseTransaction;
    }

    @Override
    public Integer getPurchaseCount(User user) {
        PurchaseTransaction purchaseTransaction = transactionRepository.findByUserCart(user);
        Integer totalCount = 0;

        if (purchaseTransaction != null) {
            for (Purchase purchase: purchaseTransaction.getPurchases()) {
                totalCount = totalCount + purchase.getQuantity();
            }

            return totalCount;
        } else {
            return 0;
        }
    }

    private BigDecimal getTotalPrice(List<Purchase> purchases) {
        BigDecimal grandTotal = BigDecimal.ZERO;

        for (Purchase purchase: purchases) {
            Product product = purchase.getProduct();
            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(purchase.getQuantity()));
            grandTotal = grandTotal.add(totalPrice);
        }

        return grandTotal;
    }

    private String generateInvoice(Date date, String id) {
        String invoice = String.format("%d%d%d-%s", date.getYear(),
                date.getMonth() + 1, date.getDate(), id);
        return invoice;
    }

    private PurchaseTransaction saveNewTransaction(User user, Purchase purchase){
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setUser(user);
        transaction.setPurchases(Arrays.asList(purchase));

        PurchaseTransaction purchaseTransaction = transactionRepository.save(transaction);

        purchase.setQuantity(1);
        purchaseRepository.save(purchase);
        return purchaseTransaction;
    }

}
