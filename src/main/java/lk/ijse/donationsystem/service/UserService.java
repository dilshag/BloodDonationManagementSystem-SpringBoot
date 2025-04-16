package lk.ijse.donationsystem.service;


import lk.ijse.donationsystem.UserStatus;
import lk.ijse.donationsystem.dto.UserDTO;
import lk.ijse.donationsystem.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    //NOTIFICATION EKATA MEKA
    User getUserById(UUID userId);
    public User getAdminUser();


    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    List<UserDTO> getAllUsers();

    boolean deleteUser(String email);
  //  void updateUserStatus(String email, boolean enabled);

    void updateUserStatus(String email, UserStatus status);
}