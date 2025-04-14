package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BloodInventoryRepository extends JpaRepository<BloodInventory, UUID> {
}
