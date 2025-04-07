package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.DonorDTO;
import lk.ijse.donationsystem.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/donor")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:63342")

public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    //donor profile ekata register wenawa
    @PostMapping("/register/profile")
    public ResponseEntity<String> registerDonorProfile(@RequestBody DonorDTO donorDTO) {

        // Get authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // Set logged-in email to donorDTO to ensure security
        donorDTO.setEmail(loggedInEmail);



        try {
            String resultMessage = donorService.saveDonor(donorDTO);
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/search/{email}")
    public ResponseEntity<DonorDTO> getDonorByEmail(@PathVariable String email) {
        try {
            DonorDTO donorDTO = donorService.getDonorByEmail(email);
            return ResponseEntity.ok(donorDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DONOR')")
    @GetMapping("/all")
    public ResponseEntity<List<DonorDTO>> getAllDonors() {
        List<DonorDTO> donors = donorService.getAllDonors();
        return ResponseEntity.ok(donors);
    }

    // donor profile eka update knna=>donor log unoth witharai
    @PutMapping("/update/profile")
    public ResponseEntity<String> updateDonorProfile(@RequestBody DonorDTO donorDTO) {
        // Get authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName(); // This will fetch the email of the logged-in user

        // Pass the email to the service for profile update
        try {
            String message = donorService.updateDonorProfile(loggedInEmail, donorDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }


    // Update donor status (ACTIVE/INACTIVE)
    @PutMapping("/update/status/{status}")
    public ResponseEntity<String> updateDonorStatus(@PathVariable String status) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loggedInEmail = authentication.getName();
            String message = donorService.updateDonorStatus(loggedInEmail, status);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    // ✅ Profile Picture Upload Endpoint
    @PostMapping("/upload/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(
            @RequestParam("file") MultipartFile file) {

        // Get the logged-in donor's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        try {
            String imageUrl = donorService.uploadProfilePicture(loggedInEmail, file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}






/*
package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.DonorDTO;
import lk.ijse.donationsystem.service.DonorService;
import lk.ijse.donationsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/donor")
public class DonorController {

    private final DonorService donorService;
    private final UserService userService;

    public DonorController(DonorService donorService, UserService userService) {
        this.donorService = donorService;
        this.userService = userService;
    }

    // Donor profile creation - after user registration, they can provide donor details
    @PostMapping("/register/profile")
    public ResponseEntity<String> registerDonorProfile(@RequestBody DonorDTO donorDTO) {
        try {
            String resultMessage = donorService.saveDonor(donorDTO);
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }


    // Get Donor details by email
    @GetMapping("/search/{email}")
    public ResponseEntity<DonorDTO> getDonorByEmail(@PathVariable String email) {
        try {
            DonorDTO donorDTO = donorService.getDonorByEmail(email);
            return ResponseEntity.ok(donorDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DonorDTO>> getAllDonors() {
        List<DonorDTO> donors = donorService.getAllDonors();
        return ResponseEntity.ok(donors);
    }

    // ✅ Update Donor Profile (Donor can only update their own profile)
    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateDonorProfile(@PathVariable String email, @RequestBody DonorDTO donorDTO) {
        try {
            String resultMessage = donorService.updateDonorProfile(email, donorDTO);
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }


        @PutMapping("/update/profile")
        public ResponseEntity<String> updateDonorProfile(@RequestBody DonorDTO donorDTO){
            // Get authenticated donor email from Spring Security
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loggedInEmail = authentication.getName();

            String message = donorService.updateDonorProfile(loggedInEmail, donorDTO);
            return ResponseEntity.ok(message);
        }


    }

*/
/*

    // Endpoint to disable a donor (only Admins can perform this action)
    @PutMapping("/disable/{email}")
    public ResponseEntity<String> disableDonor(@PathVariable String email) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // Check if the logged-in user is an Admin
        if (!isAdmin(loggedInEmail)) {
            return ResponseEntity.status(403).body("Error: Only Admins can disable donors.");
        }

        try {
            String message = donorService.disableDonor(email);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    // Endpoint to activate a donor (only Admins can perform this action)
    @PutMapping("/activate/{email}")
    public ResponseEntity<String> activateDonor(@PathVariable String email) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // Check if the logged-in user is an Admin
        if (!isAdmin(loggedInEmail)) {
            return ResponseEntity.status(403).body("Error: Only Admins can activate donors.");
        }

        try {
            String message = donorService.activateDonor(email);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
*//*


   */
/* // Helper method to check if the logged-in user is an Admin
    private boolean isAdmin(String email) {
        // You can fetch the user details from the database (assuming UserRepository has a method to find by email)
        // and check if the user's role is ADMIN. For simplicity, let's assume that role is stored in the User entity.
        // Example: Fetching the user by email and checking if they have an "ADMIN" role.

        // Assuming `userService` is available in this controller (or use `userRepository` if needed)
        // User user = userRepository.findByEmail(email);

        // Return true if the user's role is ADMIN
        return true;  // This is a placeholder for your role-checking logic.
    }
*//*



    private boolean isAdmin(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
    }

}*/
