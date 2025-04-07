package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodInventoryDTO;
import lk.ijse.donationsystem.service.BloodInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;

    public BloodInventoryController(BloodInventoryService bloodInventoryService) {
        this.bloodInventoryService = bloodInventoryService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addBloodInventory(@RequestBody BloodInventoryDTO bloodInventoryDTO) {
        try {
            String message = bloodInventoryService.addBloodInventory(bloodInventoryDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }


/*
    @PostMapping("/add")
    public ResponseEntity<String> addBloodInventory(@RequestBody BloodInventoryDTO bloodInventoryDTO) {
        try {
            String message = bloodInventoryService.addBloodInventory(bloodInventoryDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }*/

    @GetMapping("/{bloodBankId}")
    public ResponseEntity<BloodInventoryDTO> getBloodInventory(@PathVariable UUID bloodBankId) {
        try {
            BloodInventoryDTO bloodInventoryDTO = bloodInventoryService.getBloodInventory(bloodBankId);
            if (bloodInventoryDTO == null) {
                return ResponseEntity.status(404).body(null);
            }
            return ResponseEntity.ok(bloodInventoryDTO);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/update/{bloodInventoryId}")
    public ResponseEntity<String> updateBloodInventory(@PathVariable UUID bloodInventoryId, @RequestParam String quantity) {
        try {
            String message = bloodInventoryService.updateBloodInventory(bloodInventoryId, quantity);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}



/*
package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodInventoryDTO;
import lk.ijse.donationsystem.service.BloodInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:63342")

public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;

    public BloodInventoryController(BloodInventoryService bloodInventoryService) {
        this.bloodInventoryService = bloodInventoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBloodInventory(@RequestBody BloodInventoryDTO bloodInventoryDTO) {
        String message = bloodInventoryService.addBloodInventory(bloodInventoryDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{bloodBankId}")
    public ResponseEntity<BloodInventoryDTO> getBloodInventory(@PathVariable UUID bloodBankId) {
        BloodInventoryDTO bloodInventoryDTO = bloodInventoryService.getBloodInventory(bloodBankId);
        if (bloodInventoryDTO == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(bloodInventoryDTO);
    }

    // Update blood inventory by blood inventory ID and quantity
    @PutMapping("/update/{bloodInventoryId}")
    public ResponseEntity<String> updateBloodInventory(@PathVariable UUID bloodInventoryId, @RequestParam String quantity) {
        String message = bloodInventoryService.updateBloodInventory(bloodInventoryId, quantity);
        return ResponseEntity.ok(message);
    }
}
*/
