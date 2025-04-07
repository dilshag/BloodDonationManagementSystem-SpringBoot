package lk.ijse.donationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class AuthDTO {
    private String email;
    private String token;
    private boolean isDonorRegistered;

    // Manually adding getter and setter
    public boolean isDonorRegistered() {
        return isDonorRegistered;
    }

    public void setIsDonorRegistered(boolean isDonorRegistered) {
        this.isDonorRegistered = isDonorRegistered;
    }
}