package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.Notification;
import lk.ijse.donationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}
