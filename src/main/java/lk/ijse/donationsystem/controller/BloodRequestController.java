/*
package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.BloodRequestDTO;
import lk.ijse.donationsystem.service.BloodRequestService;
import lk.ijse.donationsystem.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blood-requests")
@CrossOrigin(origins = "http://localhost:63342")
public class BloodRequestController {

    @Autowired
    private BloodRequestService bloodRequestService;

    @PostMapping
    public ResponseEntity<String> requestBlood(@RequestBody BloodRequestDTO dto) {
        bloodRequestService.createBloodRequest(dto);
        return ResponseEntity.ok("Blood request submitted successfully");
    }



    */
/*
    @PostMapping
    public ResponseEntity<String> createBloodRequest(@RequestBody BloodRequestDTO dto) {
        bloodRequestService.createBloodRequest(dto);
        return ResponseEntity.ok("Blood Request created successfully");
    }

    @GetMapping
    public ResponseEntity<List<BloodRequestDTO>> getAllBloodRequests() {
        return ResponseEntity.ok(bloodRequestService.getAllBloodRequests());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BloodRequestDTO>> getBloodRequestsByStatus(@PathVariable RequestStatus status) {
        return ResponseEntity.ok(bloodRequestService.getBloodRequestsByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequestDTO> getBloodRequestById(@PathVariable UUID id) {
        return ResponseEntity.ok(bloodRequestService.getBloodRequestById(id));
    }*//*

}
*/
