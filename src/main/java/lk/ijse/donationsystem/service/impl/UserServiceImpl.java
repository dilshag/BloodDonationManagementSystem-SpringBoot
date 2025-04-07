package lk.ijse.donationsystem.service.impl;


import lk.ijse.donationsystem.UserStatus;
import lk.ijse.donationsystem.dto.UserDTO;
import lk.ijse.donationsystem.entity.User;
import lk.ijse.donationsystem.repo.UserRepository;
import lk.ijse.donationsystem.service.UserService;
import lk.ijse.donationsystem.utill.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), getAuthority(user));
    }

   /* @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }*/

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return modelMapper.map(user, UserDTO.class);
    }

  /*  public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }*/

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name())); // FIXED ENUM ISSUE
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        return userRepository.findByEmail(username)
                .map(user -> modelMapper.map(user, UserDTO.class)) // Convert User -> UserDTO
                .orElse(null); // Return null if user not found
    }


   /* @Override
    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)) {
            User user=userRepository.findByEmail(username);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }*/

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            // Set the status if it's null (default to ENABLED)
            if (userDTO.getStatus() == null) {
                userDTO.setStatus(UserStatus.ENABLED);
            }

            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }

/*@Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }*/
@Override
public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
            .map(user -> modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());
}

    @Override
    public boolean deleteUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }

/*

@Override
    public boolean deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

*/

    @Override
    public void updateUserStatus(String email, UserStatus status) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Set the user status based on the provided status
            user.setStatus(status);
            userRepository.save(user); // Save the updated user
        } else {
            throw new RuntimeException("User not found!");
        }
    }


  /*  @Override
    public void updateUserStatus(String email, boolean enabled) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.updateUserStatus(email, enabled);
        } else {
            throw new RuntimeException("User not found!");
        }
    }*/


    public boolean isDonorRegistered(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user -> user.getDonor() != null).orElse(false);
    }

}
