package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

public class User {
    private final long id;
    private UserRole role;
    private boolean isActive;
    private String photoName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;

    public User(long id, UserRole role, boolean isActive, String photoName, String firstName, String lastName,
                LocalDate dateOfBirth, String phone, String email) {
        this.id = id;
        this.role = role;
        this.isActive = isActive;
        this.photoName = photoName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
    }

    public User(long id, UserRole role, boolean isActive, String email) {
        this.id = id;
        this.role = role;
        this.isActive = isActive;
        this.email = email;
    }

    //todo add necessary constructors and remove unnecessary

    public long getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return (this.id == other.id && this.role == other.role && this.isActive == other.isActive) &&
                (this.dateOfBirth.compareTo(other.dateOfBirth) == 0) &&
                (this.photoName != null ? this.photoName.equals(other.photoName) : other.photoName == null) &&
                (this.firstName != null ? this.firstName.equals(other.firstName) : other.firstName == null) &&
                (this.lastName != null ? this.lastName.equals(other.lastName) : other.lastName == null) &&
                (this.phone != null ? this.phone.equals(other.phone) : other.phone == null) &&
                (this.email != null ? this.email.equals(other.email) : other.email == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + Boolean.hashCode(isActive);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("role = ").append(role).append("\n");
        sb.append("is active = ").append(isActive).append("\n");
        sb.append("photo name = ").append(photoName).append("\n");
        sb.append("first name = ").append(firstName).append("\n");
        sb.append("last name = ").append(lastName).append("\n");
        sb.append("date of birth = ").append(dateOfBirth).append("\n");
        sb.append("phone = ").append(phone).append("\n");
        sb.append("email = ").append(email).append("\n");
        return sb.toString();
    }
}
