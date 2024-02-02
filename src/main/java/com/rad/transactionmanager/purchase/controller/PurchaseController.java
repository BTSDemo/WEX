package com.rad.transactionmanager.purchase.controller;

import com.rad.transactionmanager.purchase.model.Purchase;
import com.rad.transactionmanager.purchase.service.PurchaseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase-form")
    public String showPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchase-form";
    }

    @PostMapping("/save-purchase")
    public String savePurchase(Purchase purchase) {
        // Call the service to save the purchase
        purchaseService.savePurchase(purchase);

        // Redirect to a confirmation page or another appropriate view
        return "redirect:/purchase-confirmation";
    }

    @GetMapping("/purchase-confirmation")
    public String showPurchaseConfirmation(Model model) {
    	// TODO - ???
        return "purchase-confirmation";
    }
    
    @GetMapping("/purchase-list")
    public String showPurchaseList(Model model) {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        model.addAttribute("purchases", purchases);
        return "purchase-list";
    }
    
    @GetMapping("/purchase-details/{purchaseId}")
    public String showPurchaseDetails(@PathVariable Long purchaseId, Model model) {
        // Retrieve the purchase by ID
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        model.addAttribute("purchase", purchase);
        return "purchase-details";
    }
    // Additional methods, if needed

}
