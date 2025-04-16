package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.NotificationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private UUID id;
    private String message;
    private LocalDateTime createdAt;
    private boolean isRead;
    private NotificationType type;
    private UUID userId;
}
