/*
package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.dto.RecipientDTO;
import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.entity.Recipient;
import lk.ijse.donationsystem.entity.User;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.repo.RecipientRepository;
import lk.ijse.donationsystem.repo.UserRepository;
import lk.ijse.donationsystem.service.RecipientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecipientServiceImpl implements RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BloodBankRepository bloodBankRepository;


    @Override
    public void saveRecipient(RecipientDTO dto) {
        // Get the logged-in user's email from the JWT
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Find the blood bank by name
        String bloodBankName = dto.getBloodBankName();
        BloodBank bloodBank = bloodBankRepository.findByName(bloodBankName)
                .orElseThrow(() -> new RuntimeException("Blood bank not found with name: " + bloodBankName));

        // Map DTO to entity and associate user and blood bank
        Recipient recipient = modelMapper.map(dto, Recipient.class);
        recipient.setUser(user);
        recipient.setBloodBank(bloodBank);

        // Save the recipient
        recipientRepository.save(recipient);
    }
}
   */
/* @Override
    public void saveRecipient(RecipientDTO dto) {
        UUID userId = UUID.fromString(dto.getUserId());
        UUID bloodBankId = UUID.fromString(dto.getBloodBankId());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId)
                .orElseThrow(() -> new RuntimeException("Blood bank not found with ID: " + bloodBankId));

        Recipient recipient = modelMapper.map(dto, Recipient.class);
        recipient.setUser(user);
        recipient.setBloodBank(bloodBank);

        recipientRepository.save(recipient);
    }

}*/

