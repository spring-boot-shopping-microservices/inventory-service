package org.example.inventoryservice.service;

import org.example.inventoryservice.model.Inventory;
import org.example.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class InventoryServiceTest {
  @Mock
  private InventoryRepository inventoryRepository;

  @InjectMocks
  private InventoryService inventoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() {
    inventoryRepository.deleteAll();
  }

  @Test
  void testIsInStockWhenInventoryExists() {
    // Given
    when(inventoryRepository.findBySkuCode(anyString()))
            .thenReturn(Optional.of(new Inventory()));

    // Then
    boolean isInStock = inventoryService
            .isInStock("testSkuCode");

    // When
    assertTrue(isInStock, "Item should be in stock");
    verify(inventoryRepository, times(1))
            .findBySkuCode(anyString());
  }

  @Test
  void testIsInStockWhenInventoryDoesNotExist() {
    // Given
    when(inventoryRepository.findBySkuCode(anyString()))
            .thenReturn(Optional.empty());

    // Then
    boolean isInStock = inventoryService
            .isInStock("testSkuCode");

    // When
    assertFalse(isInStock, "Item should not be in stock");
    verify(inventoryRepository, times(1))
            .findBySkuCode(anyString());
  }
}