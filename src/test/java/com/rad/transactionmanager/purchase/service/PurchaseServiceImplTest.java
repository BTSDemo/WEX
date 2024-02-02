package com.rad.transactionmanager.purchase.service;

import com.rad.transactionmanager.purchase.model.Purchase;
import com.rad.transactionmanager.purchase.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class PurchaseServiceImplTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Test
    void savePurchase() {
        // Mock data
        Purchase mockPurchase = new Purchase();
        mockPurchase.setDescription("Test Purchase");
        mockPurchase.setTransactionDate(LocalDate.of(2022, 1, 1)); // Set LocalDate here
        mockPurchase.setPurchaseAmount(BigDecimal.valueOf(100.00));

        // Mock repository behavior
        when(purchaseRepository.save(any())).thenReturn(mockPurchase);

        // Perform the actual test
        Purchase savedPurchase = purchaseService.savePurchase(mockPurchase);

        // Assertions
        assertNotNull(savedPurchase);
        assertEquals(mockPurchase.getDescription(), savedPurchase.getDescription());

        // Verify that the save method was called with the expected parameters
        verify(purchaseRepository, times(1)).save(eq(mockPurchase));
    }

    // Rest of the tests...
}
