package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.BloodBankStatus;
import lk.ijse.donationsystem.dto.BloodBankDTO;
import lk.ijse.donationsystem.service.BloodBankService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bloodbanks")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodBankController {

    private final BloodBankService bloodBankService;

    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> addBloodBank(@RequestBody BloodBankDTO bloodBankDTO) {
        try {
            return ResponseEntity.ok(bloodBankService.addBloodBank(bloodBankDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<BloodBankDTO>> getAllBloodBanks() {
        return ResponseEntity.ok(bloodBankService.getAllBloodBanks());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<BloodBankDTO> getBloodBankById(@PathVariable UUID id) {
        BloodBankDTO bloodBankDTO = bloodBankService.getBloodBankById(id);
        if (bloodBankDTO == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(bloodBankDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBloodBank(@PathVariable UUID id, @RequestBody BloodBankDTO bloodBankDTO) {
        try {
            return ResponseEntity.ok(bloodBankService.updateBloodBank(id, bloodBankDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBloodBank(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bloodBankService.deleteBloodBank(id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/set-status/{id}")
    public ResponseEntity<Map<String, String>> setBloodBankStatus(
            @PathVariable UUID id,
            @RequestParam BloodBankStatus status) {

        String message = bloodBankService.setStatus(id, status);
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/active")
    public ResponseEntity<List<BloodBankDTO>> getAllActiveBloodBanks() {
        List<BloodBankDTO> activeBloodBanks = bloodBankService.getAllActiveBloodBanks();
        return ResponseEntity.ok(activeBloodBanks);
    }
}



