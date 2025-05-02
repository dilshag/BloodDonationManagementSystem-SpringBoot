package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.entity.BloodInventory;
import lk.ijse.donationsystem.entity.BloodStock;
import lk.ijse.donationsystem.entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, UUID> {
    Optional<BloodStock> findByBloodTypeAndInventory(BloodType bloodType, BloodInventory inventory);

    Optional<BloodStock> findByBloodType(BloodType bloodType);

    List<BloodStock> findByInventory(BloodInventory inventory);



}



