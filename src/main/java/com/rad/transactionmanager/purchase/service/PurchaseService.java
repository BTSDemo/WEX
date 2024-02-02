package com.rad.transactionmanager.purchase.service;

import java.util.List;

import com.rad.transactionmanager.purchase.model.Purchase;

public interface PurchaseService {

    List<Purchase> getAllPurchases();

	Purchase savePurchase(Purchase purchase);

	Purchase getPurchaseById(Long purchaseId);

}
