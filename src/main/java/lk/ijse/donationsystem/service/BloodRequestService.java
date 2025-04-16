package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.RequestStatus;
import lk.ijse.donationsystem.dto.BloodRequestDTO;
import lk.ijse.donationsystem.entity.BloodRequest;

import java.util.List;
import java.util.UUID;

public interface BloodRequestService {
    void createBloodRequest(BloodRequestDTO dto);
    void approveBloodRequest(UUID requestId);
    void rejectBloodRequest(UUID requestId);
    List<BloodRequestDTO> getAllRequests();
    List<BloodRequestDTO> getRequestsByStatus(RequestStatus status);



   /* void createBloodRequest(BloodRequestDTO dto);
    List<BloodRequestDTO> getAllBloodRequests();
    List<BloodRequestDTO> getBloodRequestsByStatus(RequestStatus status);

   // List<BloodRequestDTO> getBloodRequestsByStatus(RequestStatus status);

    BloodRequestDTO getBloodRequestById(UUID id);
*/}
