package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.entity.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BloodInventoryRepository extends JpaRepository<BloodInventory, UUID> {
    Optional<BloodInventory> findByBloodBank(BloodBank bank);
}
