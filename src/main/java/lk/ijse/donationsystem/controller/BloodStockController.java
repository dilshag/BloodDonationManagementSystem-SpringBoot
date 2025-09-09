package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodStockDTO;
import lk.ijse.donationsystem.service.BloodStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bloodstocks")
@CrossOrigin(origins = "http://localhost:63342")

public class BloodStockController {

    @Autowired
    private BloodStockService bloodStockService;

    @GetMapping("/by-blood-bank-name")
    public ResponseEntity<List<BloodStockDTO>> getStockByBloodBankName(@RequestParam String name) {
        List<BloodStockDTO> stockList = bloodStockService.getStockByBloodBankName(name);
        return ResponseEntity.ok(stockList);
    }

}
