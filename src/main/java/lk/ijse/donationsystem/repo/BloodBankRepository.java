package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.BloodBankStatus;
import lk.ijse.donationsystem.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BloodBankRepository extends JpaRepository<BloodBank, UUID> {
    //BloodBank findById(UUID id);
    Optional<BloodBank> findByName(String name);
    List<BloodBank> findAllByStatus(BloodBankStatus status);


}