package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.DonorStatus;
import lk.ijse.donationsystem.dto.DonorDTO;
import lk.ijse.donationsystem.dto.DonorProfileDTO;
import lk.ijse.donationsystem.service.DonorService;

import lk.ijse.donationsystem.service.NotificationService;
import lk.ijse.donationsystem.utill.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/donor")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:63342")

public class DonorController {

    private final DonorService donorService;

    @Autowired
    private NotificationService notificationService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    private static final String UPLOAD_DIR = "uploads/";


    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerDonorProfile(
            @RequestParam("bloodType") String bloodType,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("image") MultipartFile image) throws IOException {

        // 1. Get logged-in user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // 2. Check if image is not empty
        if (image.isEmpty()) {
            throw new RuntimeException("Profile picture is required!");
        }

        // 3. Create upload directory if it doesn't exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 4. Save image with a unique filename
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, image.getBytes());

        // 5. Map to DonorDTO
        DonorDTO donorDTO = new DonorDTO();
        donorDTO.setEmail(loggedInEmail);
        donorDTO.setBloodType(BloodType.valueOf(bloodType));
        donorDTO.setAddress(address);
        donorDTO.setPhoneNumber(phoneNumber);
        donorDTO.setProfilePictureUrl("/" + UPLOAD_DIR + fileName); // this will be saved in DB

        // 6. Save donor using service
        String message = donorService.saveDonor(donorDTO);

        // Notify admins
       // notificationService.notifyAdmins("New donor registered: " + loggedInEmail);

        return ResponseEntity.ok(message);

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
   // @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DONOR')")
    @PreAuthorize("hasAuthority('ADMIN')")
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


    @GetMapping("/profile")
    public ResponseEntity<DonorProfileDTO> getLoggedInDonorProfile(Principal principal) {
        String email = principal.getName(); // Extract from JWT or SecurityContext
        DonorProfileDTO donorProfile = donorService.getDonorProfileByEmail(email);
        return ResponseEntity.ok(donorProfile);
    }

    @GetMapping("/has-profile")
    public ResponseEntity<Boolean> hasProfile(Principal principal) {
        String email = principal.getName(); // logged-in user's email
        boolean exists = donorService.hasDonorProfile(email);
        return ResponseEntity.ok(exists);
    }

}





