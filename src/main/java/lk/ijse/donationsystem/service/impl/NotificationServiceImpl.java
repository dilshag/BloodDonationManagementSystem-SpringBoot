package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.NotificationType;
import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.entity.Donor;
import lk.ijse.donationsystem.entity.Notification;
import lk.ijse.donationsystem.entity.User;
import lk.ijse.donationsystem.repo.NotificationRepository;
import lk.ijse.donationsystem.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void notifyBloodBankAdded(User admin, BloodBank bloodBank) {
        Notification notification = new Notification();
        notification.setMessage("New Blood Bank Registered: " + bloodBank.getName());
        notification.setUser(admin);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    @Override
    public void notifyDonorAdded(User admin, Donor donor) {
        Notification notification = new Notification();
        notification.setUser(admin);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(true);
        //notification.setIsRead(false);
       // notification.setMessage("New Donor Registered: " + donor.getName());
        // When creating the notification for a new donor
        notification.setMessage("New Donor Registered: " + donor.getEmail());
        notification.setType(NotificationType.DONOR_REGISTERED);
        notificationRepository.save(notification);
    }
}
