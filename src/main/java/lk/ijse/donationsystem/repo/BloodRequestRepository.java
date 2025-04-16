package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodRequest;
import lk.ijse.donationsystem.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, UUID> {
    List<BloodRequest> findByRequestStatus(RequestStatus requestStatus);
}
