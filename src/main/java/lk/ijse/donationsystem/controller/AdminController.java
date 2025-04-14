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

    /*
    * if (role === "ADMIN") {
                            setTimeout(() => window.location.href = "adminContain.html", 1500);
                        } else if (role === "USER") {
                            setTimeout(() => window.location.href = "userInterface.html", 1500);
                        } else if (role === "GIG") {
                            // Fetch gig details for this user
                            fetch(http://localhost:8080/api/v1/user/${userId}/gigs, {
                                headers: {
                                    "Authorization": Bearer ${response.data.token}
                                }
                            })
                            .then(gigResponse => {
                                if (!gigResponse.ok) throw new Error('Failed to fetch gigs');
                                return gigResponse.json();
                            })
                            .then(gigData => {
                                if (gigData.length > 0) {
                                    localStorage.setItem("gigProfile", JSON.stringify(gigData[0]));
                                    setTimeout(() => {
                                        window.location.href = "practice.html";
                                    }, 1500);
                                } else {
                                    setTimeout(() => {
                                        window.location.href = "gigRegister.html";
                                    }, 1500);
                                }
                            })
                            .catch(gigError => {
                                console.error("Gig fetch error:", gigError);
                                showToast("Error loading gig profile", "error");
                                loginButton.innerHTML = 'Login';
                                loginButton.disabled = false;
                            });
                        } else {
                            throw new Error("Unknown user role");
                        }
                    })
                    .catch(userError => {
                        console.error("User fetch error:", userError);
                        showToast("Error loading user details", "error");
                        loginButton.innerHTML = 'Login';
                        loginButton.disabled = false;
                    });
                } else {
                    throw new Error(response.message || "Login failed");
                }
            })
            .catch(error => {
                console.error("Login error:", error);
                showToast(error.message || "Login failed", "error");
                loginButton.innerHTML = 'Login';
                loginButton.disabled = false;
            });
        });*/




}