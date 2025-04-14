


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
