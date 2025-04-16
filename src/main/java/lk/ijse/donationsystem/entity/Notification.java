// Notification.java
package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lk.ijse.donationsystem.NotificationType;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String message;

    private LocalDateTime createdAt;

    @Column(name = "is_read")
    private boolean Read;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
