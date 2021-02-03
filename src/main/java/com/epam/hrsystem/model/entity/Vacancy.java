package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

public class Vacancy {
    private final long id;
    private boolean isAvailable;
    private String position;
    private String description;
    private String country;
    private String city;
    private LocalDate creationDate;
    private User employee;

    public Vacancy(long id, boolean isAvailable, String position, String description, String country, String city,
                   LocalDate creationDate, User employee) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.position = position;
        this.description = description;
        this.country = country;
        this.city = city;
        this.creationDate = creationDate;
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getEmployee() {
        return employee;
    }

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
