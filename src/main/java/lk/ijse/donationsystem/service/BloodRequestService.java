package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.RequestStatus;
import lk.ijse.donationsystem.dto.BloodRequestDTO;
import lk.ijse.donationsystem.entity.BloodRequest;

import java.util.List;
import java.util.UUID;

public interface BloodRequestService {
    void createRequest(BloodRequestDTO dto);
    public void approveRequest(UUID requestId);
   // void approveRequest(UUID requestId);
    void rejectRequest(UUID requestId);
    List<BloodRequestDTO> getRequestsByStatus(RequestStatus status);


    List<BloodRequestDTO> getAllRequests();
}
