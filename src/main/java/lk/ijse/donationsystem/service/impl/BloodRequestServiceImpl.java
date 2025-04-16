/*
package lk.ijse.donationsystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.donationsystem.dto.BloodRequestDTO;
import lk.ijse.donationsystem.entity.BloodRequest;
import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.RequestStatus;
import lk.ijse.donationsystem.entity.BloodStock;
import lk.ijse.donationsystem.entity.User;
import lk.ijse.donationsystem.repo.BloodRequestRepository;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.repo.UserRepository;
import lk.ijse.donationsystem.service.BloodRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BloodRequestServiceImpl implements BloodRequestService {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

   */
/* @Autowired
    private RecipientRepository recipientRepository;
*//*

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public void createBloodRequest(BloodRequestDTO dto) {
        // Get currently logged-in user (Recipient)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User recipient = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find Blood Bank
        BloodBank bloodBank = bloodBankRepository.findByName(dto.getBloodBankName())
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));

        // Map DTO → Entity
        BloodRequest request = modelMapper.map(dto, BloodRequest.class);

        // Manually set fields not in DTO
        request.setId(UUID.randomUUID());
        request.setRequestStatus(RequestStatus.PENDING);
        request.setRequestDate(LocalDateTime.now());
        request.setRecipient(recipient);
        request.setBloodBank(bloodBank);

        // Save
        bloodRequestRepository.save(request);



    }




    @Override
    @Transactional
    public void approveBloodRequest(UUID requestId) {
        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getRequestStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        BloodStock stock = bloodStockRepository.findByBloodTypeAndBloodBank(
                request.getBloodType(), request.getBloodBank());

        if (stock == null || stock.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        stock.setQuantity(stock.getQuantity() - request.getQuantity());
        bloodStockRepository.save(stock);

        request.setRequestStatus(RequestStatus.APPROVED);
        request.setApprovedDate(LocalDateTime.now());
        bloodRequestRepository.save(request);
    }

    @Override
    @Transactional
    public void rejectBloodRequest(UUID requestId) {
        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getRequestStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        request.setRequestStatus(RequestStatus.REJECTED);
        request.setApprovedDate(LocalDateTime.now());
        bloodRequestRepository.save(request);
    }

    @Override
    public List<BloodRequestDTO> getAllRequests() {
        return bloodRequestRepository.findAll().stream()
                .map(req -> modelMapper.map(req, BloodRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BloodRequestDTO> getRequestsByStatus(RequestStatus status) {
        return bloodRequestRepository.findByRequestStatus(status).stream()
                .map(req -> modelMapper.map(req, BloodRequestDTO.class))
                .collect(Collectors.toList());
    }





}
*/
