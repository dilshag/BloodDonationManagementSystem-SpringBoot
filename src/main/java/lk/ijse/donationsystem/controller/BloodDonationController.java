package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.dto.DonationRequestDTO;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodDonationController {

    @Autowired
    private BloodDonationService donationService;

    @PostMapping
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


/*
package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.repo.DonorRepository;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/donations")
public class BloodDonationController {

    @Autowired
    private BloodDonationService bloodDonationService;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @PostMapping
    public ResponseEntity<BloodDonationDTO> makeDonation(@RequestBody BloodDonationDTO donationDTO) {
        // Validate that donor and blood bank exist
        if (!donorRepository.existsById(donationDTO.getDonorId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Donor not found
        }
        if (!bloodBankRepository.existsById(donationDTO.getBloodBankId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Blood bank not found
        }

        // Call the service layer to process the donation
        try {
            BloodDonationDTO savedDonationDTO = bloodDonationService.makeDonation(donationDTO);
            return new ResponseEntity<>(savedDonationDTO, HttpStatus.CREATED); // Donation created successfully
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Server error
        }
    }
}
*/
