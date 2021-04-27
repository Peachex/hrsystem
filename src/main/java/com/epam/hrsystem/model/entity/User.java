package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

/**
 * Entity class represents a user.
 *
 * @author Aleksey Klevitov
 */
public class User {
    private long id;
    private UserRole role;
    private boolean isActive;
    private String photoName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;

    /**
     * Constructs an User object.
     */
    public User() {
    }

    /**
     * Constructs an User object with given id.
     *
     * @param id long value of user's id.
     */
    public User(long id) {
        this.id = id;
    }

    /**
     * Constructs an User object with given id, role, isActive, photoName, firstName, lastName, dateOfBirth, phoneNumber, email.
     *
     * @param id          long value of user's id.
     * @param role        UserRole object of user's role.
     * @param isActive    boolean value of user's status.
     * @param photoName   String object of user's photoName.
     * @param firstName   String object of user's first name.
     * @param lastName    String object of user's last name.
     * @param dateOfBirth LocalDate object of user's date of birth.
     * @param phoneNumber String object of user's phone number.
     * @param email       String object of user's email.
     */
    public User(long id, UserRole role, boolean isActive, String photoName, String firstName, String lastName,
                LocalDate dateOfBirth, String phoneNumber, String email) {
        this.id = id;
        this.role = role;
        this.isActive = isActive;
        this.photoName = photoName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Constructs an User object with given role, isActive, photoName, firstName, lastName, dateOfBirth, phoneNumber, email.
     *
     * @param role        UserRole object of user's role.
     * @param isActive    boolean value of user's status.
     * @param photoName   String object of user's photoName.
     * @param firstName   String object of user's first name.
     * @param lastName    String object of user's last name.
     * @param dateOfBirth LocalDate object of user's date of birth.
     * @param phoneNumber String object of user's phone number.
     * @param email       String object of user's email.
     */
    public User(UserRole role, boolean isActive, String photoName, String firstName, String lastName,
                LocalDate dateOfBirth, String phoneNumber, String email) {
        this.role = role;
        this.isActive = isActive;
        this.photoName = photoName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Getter method of user's id.
     *
     * @return long value of user's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter method of user's role.
     *
     * @return UserRole object of user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Setter method of user's role.
     *
     * @param role UserRole object of user's role.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Getter method of user's status.
     *
     * @return boolean value of user's status.
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Setter method of user's status.
     *
     * @param active boolean value of user's status.
     */
    public void setIsActive(boolean active) {
        isActive = active;
    }

    /**
     * Getter method of user's photo name.
     *
     * @return String object of user's photo name.
     */
    public String getPhotoName() {
        return photoName;
    }

    /**
     * Setter method of user's photo name.
     *
     * @param photoName String object of user's photo name.
     */
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    /**
     * Getter method of user's firstName.
     *
     * @return String object of user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method of user's first name.
     *
     * @param firstName String object of user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method of user's last name.
     *
     * @return String object of user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method of user's last name.
     *
     * @param lastName String object of user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method of user's date of birth.
     *
     * @return LocalDate object of user's date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter method of user's date of birth.
     *
     * @param dateOfBirth LocalDate object of user's date of birth.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Getter method of user's phone number.
     *
     * @return String object of user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter method of user's phone number.
     *
     * @param phoneNumber String object of user's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter method of user's email.
     *
     * @return String object of user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method of user's email.
     *
     * @param email String object of user's email.
     */
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
                (this.dateOfBirth != null ? this.dateOfBirth.compareTo(other.dateOfBirth) == 0 : other.dateOfBirth == null) &&
                (this.photoName != null ? this.photoName.equals(other.photoName) : other.photoName == null) &&
                (this.firstName != null ? this.firstName.equals(other.firstName) : other.firstName == null) &&
                (this.lastName != null ? this.lastName.equals(other.lastName) : other.lastName == null) &&
                (this.phoneNumber != null ? this.phoneNumber.equals(other.phoneNumber) : other.phoneNumber == null) &&
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
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ("id = " + id +
                "\nrole = " + role +
                "\nis active = " + isActive +
                "\nphoto name = " + photoName +
                "\nfirst name = " + firstName +
                "\nlast name = " + lastName +
                "\ndate of birth = " + dateOfBirth +
                "\nphone number = " + phoneNumber +
                "\nemail = " + email);
    }
}
