package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodBankDTO;
import lk.ijse.donationsystem.service.BloodBankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bloodbanks")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodBankController {

    private final BloodBankService bloodBankService;

    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBloodBank(@RequestBody BloodBankDTO bloodBankDTO) {
        try {
            return ResponseEntity.ok(bloodBankService.addBloodBank(bloodBankDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BloodBankDTO>> getAllBloodBanks() {
        return ResponseEntity.ok(bloodBankService.getAllBloodBanks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodBankDTO> getBloodBankById(@PathVariable UUID id) {
        BloodBankDTO bloodBankDTO = bloodBankService.getBloodBankById(id);
        if (bloodBankDTO == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(bloodBankDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBloodBank(@PathVariable UUID id, @RequestBody BloodBankDTO bloodBankDTO) {
        try {
            return ResponseEntity.ok(bloodBankService.updateBloodBank(id, bloodBankDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBloodBank(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bloodBankService.deleteBloodBank(id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}




/*
package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodBankDTO;
import lk.ijse.donationsystem.service.BloodBankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bloodbanks")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:63342")

public class BloodBankController {

    private final BloodBankService bloodBankService;

    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBloodBank(@RequestBody BloodBankDTO bloodBankDTO) {
        return ResponseEntity.ok(bloodBankService.addBloodBank(bloodBankDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BloodBankDTO>> getAllBloodBanks() {
        return ResponseEntity.ok(bloodBankService.getAllBloodBanks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodBankDTO> getBloodBankById(@PathVariable UUID id) {
        return ResponseEntity.ok(bloodBankService.getBloodBankById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBloodBank(@PathVariable UUID id, @RequestBody BloodBankDTO bloodBankDTO) {
        return ResponseEntity.ok(bloodBankService.updateBloodBank(id, bloodBankDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBloodBank(@PathVariable UUID id) {
        return ResponseEntity.ok(bloodBankService.deleteBloodBank(id));
    }
}
*/
