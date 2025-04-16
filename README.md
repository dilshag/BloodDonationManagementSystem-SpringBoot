🩸 Blood Donation Management System

  A full-stack web application designed to streamline the blood donation and request process for donors, recipients, and
  admins. This system allows efficient management of blood banks, inventory, donor registrations, and blood request approvals.

✨ Features

🔐 User Authentication
Secure registration and login
JWT-based authentication
Role-based access control (Admin / Donor / Recipient)
Email verification and status-based login handling

🧑‍🤝‍🧑 User Roles
Admin: Full control of system
Donor: Register, fill donor form, donate, view history
Recipient: Request blood, track status

🏥 Blood Bank Management
Admin can add and manage multiple blood banks

🗃️ Blood Inventory
Track blood type, quantity, expiry, and associated bank

💉 Donation Management
Donors can donate blood to selected blood banks

🩸 Blood Requests
Recipients can request blood by type, quantity, and location
Admin can accept or reject requests

🔔 Notification
Donor/recipient updates on donation and request status

🛠️ Technologies Used
Frontend:
HTML, CSS, JavaScript
AJAX, jQuery

Backend:
Java, Spring Boot
Spring Security (JWT Authentication)
Spring Data JPA, Hibernate
ModelMapper (Entity ↔ DTO)

Database:
MySQL

📂 Project Structure
arduino
Copy
Edit
src/

├── config/
├── controller/
├── dto/
├── entity/
├── repo/
├── service/
├── util/


🚀 How to Run

Clone the repository
Configure MySQL in application.properties
Run the Spring Boot application
Access the frontend via browser (HTML + AJAX)

