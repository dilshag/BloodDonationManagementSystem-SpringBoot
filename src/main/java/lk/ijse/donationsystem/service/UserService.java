package lk.ijse.donationsystem.service;


import lk.ijse.donationsystem.UserStatus;
import lk.ijse.donationsystem.dto.UserDTO;

import java.util.List;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    List<UserDTO> getAllUsers();

    boolean deleteUser(String email);
  //  void updateUserStatus(String email, boolean enabled);

    void updateUserStatus(String email, UserStatus status);
}