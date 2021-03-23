package com.epam.hrsystem.model.entity;

import java.time.LocalDate;

public class UserReport {
    private long id;
    private String subject;
    private String comment;
    private final LocalDate creationDate;
    private User user;

    public UserReport(long id, String subject, String comment, LocalDate creationDate, User user) {
        this.id = id;
        this.subject = subject;
        this.comment = comment;
        this.creationDate = creationDate;
        this.user = user;
    }

    public UserReport(String subject, String comment, LocalDate creationDate, User user) {
        this.subject = subject;
        this.comment = comment;
        this.creationDate = creationDate;
        this.user = user;
    }

    public long getId() {
        return id;
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
        return (this.id == other.id) && (this.user != null ? this.user.equals(other.user) : other.user == null) &&
                (this.subject != null ? this.subject.equals(other.subject) : other.subject == null) &&
                (this.comment != null ? this.comment.equals(other.comment) : other.comment == null) &&
                (this.creationDate != null ? this.creationDate.compareTo(other.creationDate) == 0 : other.creationDate == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("subject = ").append(subject).append("\n");
        sb.append("comment = ").append(comment).append("\n");
        sb.append("creation date = ").append(creationDate).append("\n");
        sb.append("user id = ").append(user.getId()).append("\n");
        return sb.toString();
    }
}
