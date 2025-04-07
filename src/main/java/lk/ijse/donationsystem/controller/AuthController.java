package lk.ijse.donationsystem.controller;



import lk.ijse.donationsystem.UserStatus;
import lk.ijse.donationsystem.dto.AuthDTO;
import lk.ijse.donationsystem.dto.ResponseDTO;
import lk.ijse.donationsystem.dto.UserDTO;
import lk.ijse.donationsystem.service.impl.UserServiceImpl;
import lk.ijse.donationsystem.utill.JwtUtil;
import lk.ijse.donationsystem.utill.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://localhost:63342")

//@CrossOrigin("*")

public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final ResponseDTO responseDTO;

    //constructor injection
    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserServiceImpl userService, ResponseDTO responseDTO) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.responseDTO = responseDTO;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(VarList.Unauthorized, "Invalid Credentials", e.getMessage()));
        }

        UserDTO loadedUser = userService.loadUserDetailsByUsername(userDTO.getEmail());
        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
        }

        // **Check if the user is enabled**
        /*  if (!loadedUser.isEnabled())*/
       /* if (loadedUser.getUserStatus() == UserStatus.DISABLED) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ResponseDTO(VarList.Forbidden, "User account is disabled. Contact admin.", null));
            }
         */
        // **Check if the user is enabled**
        if (!loadedUser.getStatus().equals(UserStatus.ENABLED)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(VarList.Forbidden, "User account is disabled. Contact admin.", null));
        }

        // **Check if the user is a registered donor**
        boolean isDonorRegistered = userService.isDonorRegistered(loadedUser.getEmail());

        String token = jwtUtil.generateToken(loadedUser);
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
            }

            AuthDTO authDTO = new AuthDTO();
            authDTO.setEmail(loadedUser.getEmail());
            authDTO.setToken(token);
           // authDTO.setDonorRegistered(isDonorRegistered);
            authDTO.setIsDonorRegistered(isDonorRegistered);  // Use the correct setter

        //authDTO.setIsDonorRegistered(isDonorRegistered); // Add donor registration status

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created, "Success", authDTO));
        }

    }


