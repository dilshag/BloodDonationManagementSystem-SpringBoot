package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.RequestStatus;
import lk.ijse.donationsystem.dto.BloodRequestDTO;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.repo.BloodRequestRepository;
import lk.ijse.donationsystem.repo.UserRepository;
import lk.ijse.donationsystem.service.BloodRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/requests")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodRequestController {


    @Autowired
    private BloodRequestService bloodRequestService;

    @Autowired
    private BloodRequestRepository bloodRequestRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody BloodRequestDTO dto) {
            bloodRequestService.createRequest(dto);
        return ResponseEntity.ok(Map.of("message", "Request submitted."));
        }


    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveRequest(@PathVariable UUID id) {
        bloodRequestService.approveRequest(id);
        return ResponseEntity.ok("Request Approved and Stock Updated");
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectRequest(@PathVariable UUID id) {
        bloodRequestService.rejectRequest(id);
        return ResponseEntity.ok("Request Rejected");
    }

@GetMapping("/status/{status}")
public ResponseEntity<List<BloodRequestDTO>> getRequestsByStatus(@PathVariable RequestStatus status) {
    List<BloodRequestDTO> requests = bloodRequestService.getRequestsByStatus(status);
    return ResponseEntity.ok(requests);
}


    @GetMapping("/all")
    public ResponseEntity<List<BloodRequestDTO>> getAllRequests() {
        return ResponseEntity.ok(bloodRequestService.getAllRequests());
    }




    }







  /*@PostMapping("/create")
    //<?> = (anytype) => eka dammana mokk hri response type ekk return krnna pluwn
    //String, Map, custom ResponseDTO wge
    public ResponseEntity<String> create(@RequestBody BloodRequestDTO dto) {
        requestService.createRequest(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Request submitted.");
    }*/




/*

@PostMapping("/create")
public ResponseEntity<Map<String, String>> create(@RequestBody BloodRequestDTO dto) {
    bloodRequestService.createRequest(dto);
    return ResponseEntity.ok(Map.of("message", "Request submitted."));
}
// 1. Find recipient by email
       */
/* User recipient = userRepository.findById(dto.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Recipient Id not found "));

        // 2. Find blood bank
        BloodBank bank = bloodBankRepository.findById(dto.getBloodBankId())
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));

        // 3. Create blood request
        BloodRequest request = new BloodRequest();
        request.setBloodType(dto.getBloodType());
        request.setQuantity(dto.getQuantity());
        request.setMedicalCondition(dto.getMedicalCondition());
        request.setContactNumber(dto.getContactNumber());
        request.setRequestedDate(LocalDate.now());
        request.setRequestStatus(RequestStatus.PENDING);
        request.setRecipient(recipient);  // Set recipient using email
        request.setBloodBank(bank);

        // 4. Save
        bloodRequestRepository.save(request);*//*


// return ResponseEntity.status(HttpStatus.CREATED).body("Request submitted.");
    }


*/



