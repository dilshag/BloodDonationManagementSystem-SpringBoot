package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/donations")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodDonationController {

    private final BloodDonationService bloodDonationService;

    public BloodDonationController(BloodDonationService bloodDonationService) {
        this.bloodDonationService = bloodDonationService;
    }

    @PostMapping("/donate")
    public ResponseEntity<String> donateBlood(@RequestBody BloodDonationDTO bloodDonationDTO) {
        try {
            String resultMessage = bloodDonationService.donateBlood(bloodDonationDTO);
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}
