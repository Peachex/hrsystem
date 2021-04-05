package com.epam.hrsystem.model.entity;

/**
 * Entity class represents an interview result.
 *
 * @author Aleksey Klevitov
 */
public class InterviewResult {
    private long id;
    private byte rating;
    private String comment;

    /**
     * Constructs an InterviewResult object.
     */
    public InterviewResult() {
    }

    /**
     * Constructs an InterviewResult object with given id, rating, comment.
     *
     * @param id      long value of interview result's id.
     * @param rating  byte value of interview result's rating.
     * @param comment String object of interview result's comment.
     */
    public InterviewResult(long id, byte rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Constructs an InterviewResult object with given rating, comment.
     *
     * @param rating  byte value of interview result's rating.
     * @param comment String object of interview result's comment.
     */
    public InterviewResult(byte rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Getter method of interview result's id.
     *
     * @return long value of interview result's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter method of interview result's rating.
     *
     * @return byte value of interview result's rating.
     */
    public byte getRating() {
        return rating;
    }

    /**
     * Setter method of interview result's rating.
     *
     * @param rating byte value of interview result's rating.
     */
    public void setRating(byte rating) {
        this.rating = rating;
    }

    /**
     * Getter method of interview result's comment.
     *
     * @return String object of interview result's comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter method of interview result's comment.
     *
     * @param comment String object of interview result's comment.
     */
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
