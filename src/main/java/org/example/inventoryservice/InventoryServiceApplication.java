package org.example.inventoryservice;

import org.example.inventoryservice.model.Inventory;
import org.example.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class InventoryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(InventoryServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository) {
    return args -> {
      Inventory inventory = new Inventory();
      inventory.setSkuCode("SKU123");
      inventory.setQuantity(10);

      Inventory inventory2 = new Inventory();
      inventory2.setSkuCode("SKU896");
      inventory2.setQuantity(2);

      Inventory inventory3 = new Inventory();
      inventory3.setSkuCode("SKU754");
      inventory3.setQuantity(6);

      inventoryRepository.saveAll(Arrays.asList(inventory, inventory2, inventory3));
    };
  }
}
