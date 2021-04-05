package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

/**
 * Entity class represents a vacancy.
 *
 * @author Aleksey Klevitov
 */
public class Vacancy {
    private long id;
    private boolean isAvailable;
    private String position;
    private String description;
    private String country;
    private String city;
    private LocalDate creationDate;
    private User employee;

    /**
     * Constructs a Vacancy object.
     */
    public Vacancy() {
    }

    /**
     * Constructs a Vacancy object with given id, isAvailable, position, description, creationDate, country, city, employee.
     *
     * @param id           long value of vacancy's id.
     * @param isAvailable  boolean value of vacancy's status.
     * @param position     String object of vacancy's position.
     * @param description  String object of vacancy's description.
     * @param creationDate LocalDate object of vacancy's creation date.
     * @param country      String object of vacancy's country.
     * @param city         String object of vacancy's city.
     * @param employee     Employee object of vacancy's employee.
     */
    public Vacancy(long id, boolean isAvailable, String position, String description, LocalDate creationDate,
                   String country, String city, User employee) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.position = position;
        this.description = description;
        this.creationDate = creationDate;
        this.country = country;
        this.city = city;
        this.employee = employee;
    }

    /**
     * Constructs a Vacancy object with given isAvailable, position, description, country, city, creationDate.
     *
     * @param isAvailable  boolean value of vacancy's status.
     * @param position     String object of vacancy's position.
     * @param description  String object of vacancy's description.
     * @param country      String object of vacancy's country.
     * @param city         String object of vacancy's city.
     * @param creationDate LocalDate object of vacancy's creation date.
     */
    public Vacancy(boolean isAvailable, String position, String description, String country, String city, LocalDate creationDate) {
        this.isAvailable = isAvailable;
        this.position = position;
        this.description = description;
        this.country = country;
        this.city = city;
        this.creationDate = creationDate;
    }

    /**
     * Getter method of vacancy's id.
     *
     * @return long value of vacancy's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter method of vacancy's status.
     *
     * @return boolean value of vacancy's status.
     */
    public boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * Setter method of vacancy's status.
     *
     * @param available boolean value of vacancy's status.
     */
    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Getter method of vacancy's position.
     *
     * @return String object of vacancy's position.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Setter method of vacancy's position.
     *
     * @param position String object of vacancy's position.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Getter method of vacancy's description.
     *
     * @return String object of vacancy's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method of vacancy's description.
     *
     * @param description String object of vacancy's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method of vacancy's country.
     *
     * @return String object of vacancy's country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter method of vacancy's country.
     *
     * @param country String object of vacancy's country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter method of vacancy's city.
     *
     * @return String object of vacancy's city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method of vacancy's city.
     *
     * @param city String object of vacancy's city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter method of vacancy's creation date.
     *
     * @return LocalDate object of vacancy's creation date.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Getter method of vacancy's employee.
     *
     * @return Employee object of vacancy's employee.
     */
    public User getEmployee() {
        return employee;
    }

    /**
     * Setter method of vacancy's employee.
     *
     * @param employee Employee object of vacancy's employee.
     */
    public void setEmployee(User employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vacancy other = (Vacancy) obj;
        return (this.id == other.id && this.isAvailable == other.isAvailable) &&
                (this.position != null ? this.position.equals(other.position) : other.position == null) &&
                (this.description != null ? this.description.equals(other.description) : other.description == null) &&
                (this.country != null ? this.country.equals(other.country) : other.country == null) &&
                (this.city != null ? this.city.equals(other.city) : other.city == null) &&
                (this.creationDate != null ? this.creationDate.compareTo(other.creationDate) == 0 : other.creationDate == null) &&
                (this.employee != null ? this.employee.equals(other.employee) : other.employee == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Boolean.hashCode(isAvailable);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("is available = ").append(isAvailable).append("\n");
        sb.append("position = ").append(position).append("\n");
        sb.append("description = ").append(description).append("\n");
        sb.append("country = ").append(country).append("\n");
        sb.append("city = ").append(city).append("\n");
        sb.append("creation date = ").append(creationDate).append("\n");
        sb.append("employee id = ").append(employee.getId()).append("\n");
        return sb.toString();
    }
}
