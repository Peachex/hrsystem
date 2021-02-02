package com.epam.hrsystem.model.entity;

public class InterviewResult {
    private final long id;
    private byte rating;
    private String comment;

    public InterviewResult(long id, byte rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InterviewResult other = (InterviewResult) obj;
        return (this.id == other.id) && (this.rating == other.rating) &&
                (this.comment != null ? this.comment.equals(other.comment) : other.comment == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Byte.hashCode(rating);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("rating = ").append(rating).append("\n");
        sb.append("comment = ").append(comment).append("\n");
        return sb.toString();
    }
}
