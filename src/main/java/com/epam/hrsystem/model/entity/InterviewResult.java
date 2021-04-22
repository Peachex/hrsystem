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
    private InterviewType type;

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
     * @param type InterviewType object of interview result's type.
     */
    public InterviewResult(long id, byte rating, String comment, InterviewType type) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.type = type;
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

    /**
     * Getter method of interview result's type.
     *
     * @return InterviewType object of interview result's type.
     */
    public InterviewType getType() {
        return type;
    }

    /**
     * Setter method of interview result's type.
     *
     * @param type InterviewType object of interview result's type.
     */
    public void setType(InterviewType type) {
        this.type = type;
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
        return (this.id == other.id) && (this.rating == other.rating) && (this.type == other.type) &&
                (this.comment != null ? this.comment.equals(other.comment) : other.comment == null);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Byte.hashCode(rating);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ("id = " + id +
                "\nrating = " + rating +
                "\ncomment = " + comment +
                "\ntype = " + type);
    }
}
