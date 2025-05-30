/*
package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BloodDonationRepository extends JpaRepository<BloodDonation, UUID> {
    // Add custom query methods if needed


}
*/
package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.entity.BloodDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BloodDonationRepository extends JpaRepository<BloodDonation, UUID> {
    List<BloodDonation> findByDonorId(UUID donorId);
    List<BloodDonation> findByBloodBankId(UUID bloodBankId);
    List<BloodDonation> findByDonorIdAndBloodBankId(UUID donorId, UUID bloodBankId);
    List<BloodDonation> findByDonorIdAndBloodBankIdAndBloodType(UUID donorId, UUID bloodBankId, BloodType bloodType);



}