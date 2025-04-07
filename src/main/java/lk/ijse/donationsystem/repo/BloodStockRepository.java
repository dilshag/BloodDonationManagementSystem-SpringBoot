package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodStock;
import lk.ijse.donationsystem.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, UUID> {
    List<BloodStock> findByInventoryId(UUID inventoryId);
    Optional<BloodStock> findByInventoryIdAndBloodType(UUID inventoryId, BloodType bloodType);
}
