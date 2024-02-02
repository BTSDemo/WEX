package com.rad.transactionmanager.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rad.transactionmanager.purchase.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
}
