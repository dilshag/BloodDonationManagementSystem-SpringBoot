package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.Role;
import lk.ijse.donationsystem.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private UUID id;
    private String email;
    private String password;
    private String name;
    private Role role;
    private UserStatus status;



    public UserStatus getUserStatus() {
        return status;
    }

    public void setUserStatus(UserStatus status) {
        this.status = status;
    }

    // private boolean enabled;
}
