package com.rad.transactionmanager.purchase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rad.transactionmanager.purchase.model.Purchase;
import com.rad.transactionmanager.purchase.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public Purchase savePurchase(Purchase purchase) {
        // TODO - Perform validation if needed
        // Example: Validate description length, valid date, positive purchase amount, etc.

        // Save the purchase and return the saved entity
        return purchaseRepository.save(purchase);
    }

	@Override
	public List<Purchase> getAllPurchases() {
		
		return purchaseRepository.findAll();
	}

	@Override
	public Purchase getPurchaseById(Long purchaseId) {
	    Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
	    return optionalPurchase.orElse(null); // or handle it based on  application logic
	}

}
