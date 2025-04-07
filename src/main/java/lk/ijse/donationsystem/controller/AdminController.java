package lk.ijse.donationsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin("*")
public class AdminController {
    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String check(){
        return "passed~!1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('DONOR')")
    public String checks(){
        return "passed~!2";
    }
    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('RECIPIENT')")
    public String checkss(){
        return "passed~!2";
    }

   // @PreAuthorize("hasAuthority('ADMIN')")
   @GetMapping("/checkRole")
   public String checkRole() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String role = authentication.getAuthorities().stream()
               .map(authority -> authority.getAuthority())
               .findFirst()
               .orElse("UNKNOWN");

       return "{\"role\": \"" + role + "\"}";
   }


      /*  @GetMapping("/admin-dashboard")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<?> getAdminDashboard() {
            return ResponseEntity.ok("Welcome Admin");
        }

*/

}
