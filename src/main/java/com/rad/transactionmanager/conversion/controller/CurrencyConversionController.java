package com.rad.transactionmanager.conversion.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rad.transactionmanager.conversion.service.ConversionService;
import com.rad.transactionmanager.conversion.utils.ConversionDetail;
import com.rad.transactionmanager.conversion.utils.ConversionException;
import com.rad.transactionmanager.conversion.utils.NoConversionRateException;
import com.rad.transactionmanager.purchase.model.Purchase;
import com.rad.transactionmanager.purchase.service.PurchaseService;

@Controller
public class CurrencyConversionController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/currency-conversion/{purchaseId}")
    public String showCurrencyConversionForm(@PathVariable("purchaseId") Long purchaseId, Model model) {
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        model.addAttribute("purchase", purchase);
        return "currency-conversion";
    }
    
    @PostMapping("/perform-currency-conversion")
    public String performCurrencyConversion(@RequestParam("purchaseId") Long purchaseId,
    										@RequestParam("transactionDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate transactionDate,
    										@RequestParam("purchaseAmount") BigDecimal purchaseAmount,
                                            @RequestParam("selectedCurrency") String selectedCurrency,
                                            Model model) {

        model.addAttribute("selectedCurrency", selectedCurrency);
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        model.addAttribute("purchase", purchase);

    	try {
            // Implementation of currency conversion logic and call the Treasury Reporting Rates of Exchange API
            ConversionDetail conversionDetail = conversionService.convertToCurrency(transactionDate, purchaseAmount, selectedCurrency);

            // Update  model with the converted amount and other details for display in the results page
            model.addAttribute("conversionDetails", conversionDetail);
        } catch (NoConversionRateException e) {
            // Handling the exception by adding an error message to the model
            model.addAttribute("errorMessage", "No conversion rate available within the last 6 months of Transaction Date for the selected currency.");
        } catch (ConversionException e) {
        	model.addAttribute("errorMessage", e.getMessage());
        }
        return "currency-conversion-result";
    }
    
	@GetMapping("/convert-currency/{purchaseId}")
    public String convertToCurrency(@PathVariable Long purchaseId,
    								Model model) {

		// Retrieve the purchase by ID
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        model.addAttribute("purchase", purchase);
        return "currency-conversion";
    }
}
