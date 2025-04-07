/*
package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodStockDTO;
import lk.ijse.donationsystem.service.BloodStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bloodstock")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodStockController {

    private final BloodStockService bloodStockService;

    public BloodStockController(BloodStockService bloodStockService) {
        this.bloodStockService = bloodStockService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBloodStock(@RequestBody BloodStockDTO bloodStockDTO) {
        try {
            String message = bloodStockService.addBloodStock(bloodStockDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{stockId}")
    public ResponseEntity<String> updateBloodStock(@PathVariable UUID stockId, @RequestParam int newQuantity) {
        try {
            String message = bloodStockService.updateBloodStock(stockId, newQuantity);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{stockId}")
    public ResponseEntity<String> removeBloodStock(@PathVariable UUID stockId) {
        try {
            String message = bloodStockService.removeBloodStock(stockId);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<BloodStockDTO> getBloodStockById(@PathVariable UUID stockId) {
        try {
            BloodStockDTO bloodStockDTO = bloodStockService.getBloodStockById(stockId);
            return ResponseEntity.ok(bloodStockDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BloodStockDTO>> getAllBloodStock() {
        List<BloodStockDTO> stockList = bloodStockService.getAllBloodStock();
        return ResponseEntity.ok(stockList);
    }
}
*/
