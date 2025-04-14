/*











package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodInventoryDTO;
import lk.ijse.donationsystem.service.BloodInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blood-inventory")
@CrossOrigin
public class BloodInventoryController {

    @Autowired
    private BloodInventoryService inventoryService;

*/
/*
    // 1. Create inventory for a blood bank
    @PostMapping("/create/{bloodBankId}")
    public ResponseEntity<BloodInventoryDTO> createInventory(@PathVariable UUID bloodBankId) {
        return ResponseEntity.ok(inventoryService.createInventory(bloodBankId));
    }*//*



    // 2. Get inventory by blood bank ID
    @GetMapping("/bank/{bloodBankId}")
    public ResponseEntity<BloodInventoryDTO> getByBloodBankId(@PathVariable UUID bloodBankId) {
        return ResponseEntity.ok(inventoryService.getInventoryByBloodBankId(bloodBankId));
    }

    // 3. Add stock to inventory
    @PostMapping("/add-stock/{inventoryId}")
    public ResponseEntity<BloodInventoryDTO> addStock(@PathVariable UUID inventoryId, @RequestBody BloodInventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.addBloodStock(inventoryId, dto));
    }

    // 4. Delete stock from inventory
    @DeleteMapping("/remove-stock/{inventoryId}/{stockId}")
    public ResponseEntity<Void> removeStock(@PathVariable UUID inventoryId, @PathVariable UUID stockId) {
        inventoryService.removeBloodStock(inventoryId, stockId);
        return ResponseEntity.noContent().build();
    }

    // 5. Get all inventories (optional)
    @GetMapping("/all")
    public ResponseEntity<List<BloodInventoryDTO>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }
}

*/
