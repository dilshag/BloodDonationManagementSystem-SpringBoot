package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BloodDonationRepository extends JpaRepository<BloodDonation, UUID> {
    // Add custom query methods if needed
}
