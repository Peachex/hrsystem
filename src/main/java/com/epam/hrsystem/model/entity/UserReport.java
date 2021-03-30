package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

public class UserReport {
    private LocalDate creationDate;
    private long id;
    private boolean isAvailable;
    private String subject;
    private String comment;
    private String response;
    private User user;

    public UserReport() {
    }

    public UserReport(long id, boolean isAvailable, String subject, String comment, LocalDate creationDate, User user) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.subject = subject;
        this.comment = comment;
        this.creationDate = creationDate;
        this.user = user;
    }

    public UserReport(boolean isAvailable, String subject, String comment, LocalDate creationDate) {
        this.isAvailable = isAvailable;
        this.subject = subject;
        this.comment = comment;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public User getUser() {
        return user;
    }

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
