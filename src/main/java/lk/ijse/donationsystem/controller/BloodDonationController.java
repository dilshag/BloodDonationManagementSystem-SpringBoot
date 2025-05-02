package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.dto.DonationRequestDTO;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/donations")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodDonationController {

    @Autowired
    private BloodDonationService donationService;
/*
    @PreAuthorize("hasAuthority('DONOR')")*/
    @PostMapping("/createDonation")
    public ResponseEntity<String> createDonation(@RequestBody DonationRequestDTO dto) {
        donationService.createDonation(dto);
        return ResponseEntity.ok("Donation added successfully");
    }


    @GetMapping
    public ResponseEntity<List<BloodDonationDTO>> getAllDonations() {
        return ResponseEntity.ok(donationService.getAllDonations());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BloodDonationDTO>> filterDonations(
            @RequestParam(required = false) UUID donorId,
            @RequestParam(required = false) UUID bloodBankId,
            @RequestParam(required = false) BloodType bloodType,
            @RequestParam(defaultValue = "desc") String sort // asc | desc
    ){

        List<BloodDonationDTO> result = donationService.filterDonations(donorId, bloodBankId, bloodType, sort);
        return ResponseEntity.ok(result);
    }


}

