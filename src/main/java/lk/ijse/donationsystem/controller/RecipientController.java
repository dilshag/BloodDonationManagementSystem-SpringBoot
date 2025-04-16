/*
package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.RecipientDTO;
import lk.ijse.donationsystem.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipients")
@CrossOrigin
public class RecipientController {

    @Autowired
    private RecipientService recipientService;

    @PostMapping
    public ResponseEntity<String> createRecipient(@RequestBody RecipientDTO dto) {
        recipientService.saveRecipient(dto);
        return ResponseEntity.ok("Recipient registered successfully");
    }

}
*/
