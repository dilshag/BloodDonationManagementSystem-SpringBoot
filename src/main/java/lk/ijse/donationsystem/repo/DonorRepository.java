package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DonorRepository extends JpaRepository<Donor, UUID> {
    Donor findByEmail(String email);  // Example search by email
    boolean existsByEmail(String email);  // Check if email exists
}
