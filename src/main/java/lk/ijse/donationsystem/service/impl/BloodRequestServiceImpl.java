package lk.ijse.donationsystem.service.impl;


import lk.ijse.donationsystem.RequestStatus;
import lk.ijse.donationsystem.dto.BloodRequestDTO;
import lk.ijse.donationsystem.entity.*;
import lk.ijse.donationsystem.repo.*;
import lk.ijse.donationsystem.service.BloodRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BloodRequestServiceImpl implements BloodRequestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;

    @Autowired
    private BloodStockRepository bloodStockRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void createRequest(BloodRequestDTO dto) {

        //  recipient
        User recipient = userRepository.findById(dto.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        // blood bank
        BloodBank bank = bloodBankRepository.findById(dto.getBloodBankId())
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));

        // 3. Map and create BloodRequest
        BloodRequest request = new BloodRequest();
        request.setBloodType(dto.getBloodType());
        request.setQuantity(dto.getQuantity());
        request.setMedicalCondition(dto.getMedicalCondition());
        request.setContactNumber(dto.getContactNumber());
        request.setRequestedDate(LocalDate.now());
        if (dto.getRequestStatus() == null) {
            request.setRequestStatus(RequestStatus.PENDING);
        }

       // request.setRequestStatus(RequestStatus.PENDING); // default value
        request.setRecipient(recipient);
        request.setBloodBank(bank);

        // 4. Save
        bloodRequestRepository.save(request);
    }

    @Override
    public void approveRequest(UUID requestId) {
        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getRequestStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        // Get inventory for blood bank
        BloodInventory inventory = bloodInventoryRepository.findByBloodBank(request.getBloodBank())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        // Filter blood stock for matching blood type
        Optional<BloodStock> matchingStock = inventory.getBloodStockList().stream()
                .filter(stock -> stock.getBloodType().equals(request.getBloodType()))
                .findFirst();

        if (matchingStock.isEmpty()) {
            throw new RuntimeException("Requested blood type not available in stock");
        }

        BloodStock stock = matchingStock.get();

        if (stock.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Not enough stock available");
        }

        // Reduce stock
        stock.setQuantity(stock.getQuantity() - request.getQuantity());

        // Update status
        request.setRequestStatus(RequestStatus.APPROVED);

        // Save all
        bloodStockRepository.save(stock);
        bloodRequestRepository.save(request);
    }


    @Override
    public void rejectRequest(UUID requestId) {
        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getRequestStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request already processed");
        }

        request.setRequestStatus(RequestStatus.REJECTED);
        bloodRequestRepository.save(request);
    }


    @Override
    public List<BloodRequestDTO> getRequestsByStatus(RequestStatus requestStatus) {
        List<BloodRequest> requests = bloodRequestRepository.findByRequestStatus(requestStatus);
        return requests.stream()
                .map(request -> modelMapper.map(request, BloodRequestDTO.class))
                .collect(Collectors.toList());
    }


    public List<BloodRequestDTO> getAllRequests() {
        List<BloodRequest> requests = bloodRequestRepository.findAll();
        return requests.stream()
                .map(request -> modelMapper.map(request, BloodRequestDTO.class))
                .collect(Collectors.toList());
    }


}





