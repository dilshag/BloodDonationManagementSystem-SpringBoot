package lk.ijse.donationsystem.service;

import jakarta.transaction.Transactional;
import lk.ijse.donationsystem.dto.DonorDTO;
import lk.ijse.donationsystem.dto.DonorProfileDTO;
import lk.ijse.donationsystem.entity.Donor;
import lk.ijse.donationsystem.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DonorService {
   /* public String saveDonor(DonorDTO donorDTO);*/
   //public void registerDonor(DonorDTO donorDTO);
  // public DonorDTO saveDonor(DonorDTO donorDTO);
   public String saveDonor(DonorDTO donorDTO);
    DonorDTO getDonorByEmail(String email);
    List<DonorDTO> getAllDonors();

    public DonorProfileDTO getDonorProfileByEmail(String email);
  /*  @Transactional
   String updateDonorProfile(DonorDTO donorDTO);
*/
    @Transactional
    String updateDonorStatus(String email, String status);
     String updateDonorProfile(String email, DonorDTO donorDTO);

    // ✅ New Method to Upload Profile Picture
  /*  String uploadProfilePicture(String email, MultipartFile file);*/
  /*  @Transactional
    String disableDonor(String email);

    String activateDonor(String email);
*/

}
