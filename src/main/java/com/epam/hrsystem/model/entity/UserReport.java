package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

/**
 * Entity class represents a user report.
 */
public class UserReport {
    private long id;
    private boolean isAvailable;
    private LocalDate creationDate;
    private String subject;
    private String comment;
    private String response;
    private User user;

    /**
     * Constructs a UserReport object.
     */
    public UserReport() {
    }

    /**
     * Constructs a UserReport object with given id, status, subject, comment, creationDate, user.
     *
     * @param id           long value of user report's id.
     * @param isAvailable  boolean value of user report's status.
     * @param subject      String object of user report's subject.
     * @param comment      String object of user report's comment.
     * @param creationDate LocalDate object of user report's creation date.
     * @param user         User object of user report's user.
     */
    public UserReport(long id, boolean isAvailable, String subject, String comment, LocalDate creationDate, User user) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.subject = subject;
        this.comment = comment;
        this.creationDate = creationDate;
        this.user = user;
    }

    /**
     * Constructs a UserReport object with given status, subject, comment, creationDate.
     *
     * @param isAvailable  boolean value of user report's status.
     * @param subject      String object of user report's subject.
     * @param comment      String object of user report's comment.
     * @param creationDate LocalDate object of user report's creation date.
     */
    public UserReport(boolean isAvailable, String subject, String comment, LocalDate creationDate) {
        this.isAvailable = isAvailable;
        this.subject = subject;
        this.comment = comment;
        this.creationDate = creationDate;
    }

    /**
     * Getter method of user report's id.
     *
     * @return long value of user report's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter method of user report's status.
     *
     * @return boolean value of user report's status.
     */
    public boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * Setter method of user report's status.
     *
     * @param available boolean value of user report's status.
     */
    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Getter method of user report's subject.
     *
     * @return String object of user report's subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter method of user report's subject.
     *
     * @param subject String object of user report's subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Getter method of user report's comment.
     *
     * @return String object of user report's comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter method of user report's comment.
     *
     * @param comment String object of user report's comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter method of user report's creation date.
     *
     * @return LocalDate object of user report's creation date.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Getter method of user report's response.
     *
     * @return String object of user report's response.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Setter method of user report's response.
     *
     * @param response String object of user report's response.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Getter method of user report's user.
     *
     * @return User object of user report's user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter method of user report's user.
     *
     * @param user User object of user report's user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserReport other = (UserReport) obj;
        return (this.id == other.id && this.isAvailable == other.isAvailable) &&
                (this.user != null ? this.user.equals(other.user) : other.user == null) &&
                (this.subject != null ? this.subject.equals(other.subject) : other.subject == null) &&
                (this.comment != null ? this.comment.equals(other.comment) : other.comment == null) &&
                (this.response != null ? this.response.equals(other.response) : other.response == null) &&
                (this.creationDate != null ? this.creationDate.compareTo(other.creationDate) == 0 : other.creationDate == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Boolean.hashCode(isAvailable);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("is available = ").append(isAvailable).append("\n");
        sb.append("subject = ").append(subject).append("\n");
        sb.append("comment = ").append(comment).append("\n");
        sb.append("response = ").append(response).append("\n");
        sb.append("creation date = ").append(creationDate).append("\n");
        sb.append("user id = ").append(user.getId()).append("\n");
        return sb.toString();
    }
}
