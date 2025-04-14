package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.entity.BloodInventory;
import lk.ijse.donationsystem.entity.BloodStock;
import lk.ijse.donationsystem.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, UUID> {
    Optional<BloodStock> findByBloodTypeAndInventory(BloodType bloodType, BloodInventory inventory);



    /*Optional<BloodStock> findByBloodTypeAndBloodBankAndInventory(
            BloodType bloodType,
            BloodBank bloodBank,
            BloodInventory inventory);

    Optional<BloodStock> findByBloodTypeAndInventory(BloodType bloodType, BloodInventory inventory);
    // BloodStock findByBloodTypeAndBloodBank(BloodType bloodType, BloodBank bloodBank);
*/}




/*
package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodStock;
import lk.ijse.donationsystem.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface BloodStockRepository extends JpaRepository<BloodStock, UUID> {
    List<BloodStock> findByInventoryId(UUID inventoryId);

    @Query("SELECT s FROM BloodStock s WHERE s.inventory.bloodBank.id = :bloodBankId AND s.bloodType = :bloodType")
    List<BloodStock> findByBloodBankAndType(UUID bloodBankId, BloodType bloodType);

    @Query("SELECT s FROM BloodStock s WHERE s.expiryDate BETWEEN :startDate AND :endDate")
    List<BloodStock> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT s FROM BloodStock s WHERE s.inventory.bloodBank.id = :bloodBankId AND s.quantity < :threshold")
    List<BloodStock> findLowStockItems(UUID bloodBankId, int threshold);

    Collection<Object> findAvailableByBloodTypeAndBank(String bloodType, UUID bloodBankId);
}*/