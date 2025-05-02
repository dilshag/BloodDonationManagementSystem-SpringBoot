package lk.ijse.donationsystem.service.impl;


import lk.ijse.donationsystem.DonorStatus;
import lk.ijse.donationsystem.dto.DonorDTO;
import lk.ijse.donationsystem.dto.DonorProfileDTO;
import lk.ijse.donationsystem.entity.Donor;
import lk.ijse.donationsystem.entity.User;
import lk.ijse.donationsystem.repo.DonorRepository;
import lk.ijse.donationsystem.repo.UserRepository;
import lk.ijse.donationsystem.service.DonorService;
import lk.ijse.donationsystem.service.NotificationService;
import lk.ijse.donationsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DonorDTO getDonorByEmail(String email) {
        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        DonorDTO donorDTO = modelMapper.map(donor, DonorDTO.class);
        donorDTO.setName(donor.getUser().getName()); // ✅ Set name from User entity

        return donorDTO;
    }

    @Override
    public List<DonorDTO> getAllDonors() {
        List<Donor> donors = donorRepository.findAll();
        return donors.stream()
                .map(donor -> modelMapper.map(donor, DonorDTO.class))
                .toList();
    }

    @Override
    public boolean hasDonorProfile(String email) {
        return donorRepository.findByEmail(email) != null;
    }



  /* public void registerDonor(DonorDTO donorDTO) {
       Optional<User> userOptional = userRepository.findById(donorDTO.getId());

       if (userOptional.isPresent()) {
           User user = userOptional.get();

           Donor donor = new Donor();
           donor.setId(user.getId()); // Shared PK with User
           donor.setUser(user);
           donor.setEmail(user.getEmail()); // Getting from User entity
           donor.setBloodType(donorDTO.getBloodType());
           donor.setAddress(donorDTO.getAddress());
           donor.setPhoneNumber(donorDTO.getPhoneNumber());
           donor.setStatus(DonorStatus.ACTIVE); // Default
           donor.setProfilePictureUrl(donorDTO.getProfilePictureUrl());

           donorRepository.save(donor);
       } else {
           throw new RuntimeException("User not found for ID: " + donorDTO.getId());
       }
   }
*/
    @Transactional
    @Override
    public String saveDonor(DonorDTO donorDTO) {

        // Get authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // Security check: email match
        if (!loggedInEmail.equals(donorDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only create your own donor profile.");
        }

        // Validate user
        User user = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found. Register first."));

        // Prevent duplicate donor profiles
        if (donorRepository.findByEmail(loggedInEmail) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Donor profile already exists.");
        }

        //  Map DTO → Entity
        Donor donor = modelMapper.map(donorDTO, Donor.class);

        // Set the user (relationship binding)
        donor.setUser(user);
        user.setDonor(donor);

        // Set active status
        donor.setStatus(DonorStatus.ACTIVE);

        //  Set profile picture URL from DTO
        donor.setProfilePictureUrl(donorDTO.getProfilePictureUrl());

        // Save
        donorRepository.save(donor);

//NOTIFICATION WLATA
//  Fetch the admin user
        User adminUser = userService.getAdminUser();
//NOTIFICATION WLATA
//  Send a notification to the admin
        notificationService.notifyDonorAdded(adminUser, donor); // not "savedDonor", because you already have "donor"


        return "Donor registered successfully.";
    }


    @Transactional
    @Override
    public String updateDonorProfile(String email, DonorDTO donorDTO) {
        // Get the authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // Check if the logged-in user is trying to update their own profile
        if (!loggedInEmail.equals(loggedInEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own profile.");
        }
        // Find the donor by email
        Donor donor = donorRepository.findByEmail(loggedInEmail);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        //  Update the User's Name (if provided)
        if (donorDTO.getName() != null && !donorDTO.getName().isEmpty()) {
            donor.getUser().setName(donorDTO.getName());  // Update User's name
        }

        //  Update Donor's Phone & Address
        if (donorDTO.getPhoneNumber() != null) {
            donor.setPhoneNumber(donorDTO.getPhoneNumber());
        }
        if (donorDTO.getAddress() != null) {
            donor.setAddress(donorDTO.getAddress());
        }

        // Save the updated Donor (which also saves the User due to CascadeType.ALL)
        donorRepository.save(donor);

        return "Donor profile updated successfully.";
    }

    @Transactional
    @Override
    public String updateDonorStatus(String email, String status) {
       //donor find(email)
        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        // Update donor status
        if (status.equalsIgnoreCase("ACTIVE")) {
            donor.setStatus(DonorStatus.ACTIVE);
        } else if (status.equalsIgnoreCase("INACTIVE")) {
            donor.setStatus(DonorStatus.INACTIVE);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status. Use ACTIVE or INACTIVE.");
        }

        donorRepository.save(donor);
        return "Donor status updated successfully.";
    }

    private static final String UPLOAD_DIR = "uploads/profile_pictures/";

    /*@Transactional
    @Override
    public String uploadProfilePicture(String email, MultipartFile file) {

        // Validate donor
        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        // Validate file
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty.");
        }

        try {
            // Create upload directory if it doesn’t exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Generate unique file name
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;

            // Save the file
            file.transferTo(new File(filePath));

            // Save file path in the database
            donor.setProfilePictureUrl(filePath);
            donorRepository.save(donor);

            return "Profile picture updated successfully.";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed.");
        }
    }*/

    @Override
    public DonorProfileDTO getDonorProfileByEmail(String email) {
        Donor donor = donorRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Donor not found for email: " + email));

        return new DonorProfileDTO(
                donor.getId(),
                donor.getUser().getName(),
                donor.getEmail(),
                donor.getPhoneNumber(),
                donor.getAddress(),
                donor.getBloodType(),
                donor.getStatus(),
                donor.getProfilePictureUrl()
        );
    }
}






/*
package lk.ijse.donationsystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.donationsystem.DonorStatus;
import lk.ijse.donationsystem.dto.DonorDTO;
import lk.ijse.donationsystem.entity.Donor;
import lk.ijse.donationsystem.entity.User;
import lk.ijse.donationsystem.repo.DonorRepository;
import lk.ijse.donationsystem.repo.UserRepository;
import lk.ijse.donationsystem.service.DonorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DonorDTO getDonorByEmail(String email) {
        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        DonorDTO donorDTO = modelMapper.map(donor, DonorDTO.class);
        donorDTO.setName(donor.getUser().getName()); // ✅ Set name from User entity

        return donorDTO;
    }

    @Override
    public List<DonorDTO> getAllDonors() {
        List<Donor> donors = donorRepository.findAll();
        return donors.stream()
                .map(donor -> modelMapper.map(donor, DonorDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public String saveDonor(DonorDTO donorDTO) {
        // Check if user exists for the given email
        User user = userRepository.findByEmail(donorDTO.getEmail());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found. Register first.");
        }

        // Convert DonorDTO to Donor entity
        Donor donor = modelMapper.map(donorDTO, Donor.class);

        // Set the user reference
        donor.setUser(user);
        user.setDonor(donor);

        // Save donor
        donorRepository.save(donor);

        return "Donor registered successfully.";
    }


    @Transactional
    @Override
    public String updateDonorProfile(String email, DonorDTO donorDTO) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // Ensure the donor can only update their own profile
        if (!loggedInEmail.equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own profile.");
        }

        // Find the donor by email
        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        // ✅ Update the User's Name (if provided)
        if (donorDTO.getName() != null && !donorDTO.getName().isEmpty()) {
            donor.getUser().setName(donorDTO.getName());  // Update User's name
        }

        // ✅ Update Donor's Phone & Address
        donor.setPhoneNumber(donorDTO.getPhoneNumber());
        donor.setAddress(donorDTO.getAddress());

        // Save the updated Donor (which also saves the User due to CascadeType.ALL)
        donorRepository.save(donor);

        return "Donor profile updated successfully.";
    }

  */
/*  @Transactional
    @Override
    public String disableDonor(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        User adminUser = userRepository.findByEmail(loggedInEmail);
        if (adminUser == null || !adminUser.getRole().equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only Admins can disable donors.");
        }

        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        // Optional: Check if the donor is already inactive
        if (donor.getStatus().equals(DonorStatus.INACTIVE)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Donor is already disabled.");
        }
        donor.setStatus(DonorStatus.INACTIVE); // Set to INACTIVE using the DonorStatus enum
        donorRepository.save(donor);

        return "Donor has been disabled.";
    }

    @Transactional
    @Override
    public String activateDonor(String email) {
        // Find donor by email
        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        // Ensure that the donor is currently inactive
        if (donor.getStatus().equals(DonorStatus.ACTIVE)) { // Compare with DonorStatus.ACTIVE
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Donor is already active.");
        }

        // Change status to ACTIVE
        donor.setStatus(DonorStatus.ACTIVE); // Set to ACTIVE using the DonorStatus enum
        donorRepository.save(donor);

        return "Donor has been activated.";
    }
*//*


   */
/* @Transactional
    @Override
    public String disableDonor(String email) {
        // Find the donor by email
        Donor donor = donorRepository.findByEmail(email);
        if (donor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Donor not found.");
        }

        // Mark donor as INACTIVE
        donor.setStatus("INACTIVE");
        donorRepository.save(donor);

        return "Donor account disabled successfully.";
    }*//*


}*/
