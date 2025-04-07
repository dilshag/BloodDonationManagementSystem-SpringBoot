package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.entity.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BloodInventoryRepository extends JpaRepository<BloodInventory, UUID> {
  //  BloodInventory findByBloodBankIdAndBloodType(UUID bloodBankId, String bloodType);
  Optional<BloodInventory> findByBloodBankId(UUID bloodBankId);
   // BloodInventory findByBloodBankId(UUID bloodBankId);

   // BloodInventory findByBloodBankIdAndBloodType(UUID bloodBankId, BloodType bloodType);
}
