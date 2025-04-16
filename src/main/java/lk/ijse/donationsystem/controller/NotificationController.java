package lk.ijse.donationsystem.controller;

import lk.ijse.donationsystem.dto.NotificationDTO;
import lk.ijse.donationsystem.entity.User;
import lk.ijse.donationsystem.repo.UserRepository;
import lk.ijse.donationsystem.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:63342")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private  NotificationService notificationService;
@Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDTO>> getUserNotifications(@PathVariable UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<NotificationDTO> notifications = user.getNotifications()
                .stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(notifications);
    }
}
