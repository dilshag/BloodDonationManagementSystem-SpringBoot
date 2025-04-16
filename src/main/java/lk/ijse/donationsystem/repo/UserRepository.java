package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.Role;
import lk.ijse.donationsystem.UserStatus;
import lk.ijse.donationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
   // User findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);


 @Modifying
 @Transactional
 @Query("UPDATE User u SET u.status = :status WHERE u.email = :email")
 void updateUserStatus(@Param("email") String email, @Param("status") UserStatus status);
 Optional<User> findByEmail(String email);
    /*@Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled = :enabled WHERE u.email = :email")
    void updateUserStatus(@Param("email") String email, @Param("enabled") boolean enabled);
    Optional<User> findByEmail(String email);*/


//notification wlta
 //Optional<User> findByRole(Role role);
 Optional<User> findFirstByRole(Role role);

 //List<User> findByRole(Role role);

}