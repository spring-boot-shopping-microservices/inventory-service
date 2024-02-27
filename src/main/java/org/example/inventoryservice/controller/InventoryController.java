package org.example.inventoryservice.controller;

import org.example.inventoryservice.dto.InventoryResponse;
import org.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
  private final InventoryService inventoryService;

  @Autowired
  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping("/{skuCode}")
  @ResponseStatus(HttpStatus.OK)
  public boolean isInStock(@PathVariable("skuCode") String skuCode) {
    return inventoryService.isInStock(skuCode);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryResponse> areItemsInStock(@RequestParam List<String> skuCodeList) {
    return inventoryService.areItemsInStock(skuCodeList);
  }
}
