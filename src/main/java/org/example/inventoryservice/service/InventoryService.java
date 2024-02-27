package org.example.inventoryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.inventoryservice.dto.InventoryResponse;
import org.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  @Autowired
  public InventoryService(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @Transactional(readOnly = true)
  public boolean isInStock(String skuCode) {
    return inventoryRepository.findBySkuCode(skuCode).isPresent();
  }

  @Transactional(readOnly = true)
  public List<InventoryResponse> areItemsInStock(List<String> skuCodeList) {
    return inventoryRepository.findBySkuCodeIn(skuCodeList).stream()
            .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build())
            .toList();
  }
}
