package io.hdavid.backend;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a beverage review.
 */
public class Review implements Serializable {

    private Long id = null;
    private int score;
    private String name;
    private LocalDate date;
    private Category category;
    private int count;

    /**
     * Default constructor.
     */
    public Review() {
        reset();
    }

    /**
     * Constructs a new instance with the given data.
     *
     * @param score
     *            Review score
     * @param name
     *            Name of beverage reviewed
     * @param date
     *            Last review date
     * @param category
     *            Category of beverage
     * @param count
     *            Times tasted
     */
    public Review(int score, String name, LocalDate date, Category category,
            int count) {
        this.score = score;
        this.name = name;
        this.date = date;
        this.category = new Category(category);
        this.count = count;
    }

    /**
     * Copy constructor.
     *
     * @param other
     *            The instance to copy
     */
    public Review(Review other) {
        this(other.getScore(), other.getName(), other.getDate(),
                other.getCategory(), other.getCount());
        this.id = other.getId();
    }

    /**
     * Resets all fields to their default values.
     */
    public void reset() {
        this.id = null;
        this.score = 1;
        this.name = "";
        this.date = LocalDate.now();
        this.category = null;
        this.count = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the value of score
     *
     * @return the value of score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the value of score
     *
     * @param score
     *            new value of Score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name
     *
     * @param name
     *            new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of category
     *
     * @return the value of category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of category
     *
     * @param category
     *            new value of category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets the value of date
     *
     * @return the value of date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the value of date
     *
     * @param date
     *            new value of date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the value of count
     *
     * @return the value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of count
     *
     * @param count
     *            new value of count
     */
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        // Must use getters instead of direct member access,
        // to make it work with proxy objects generated by the view model
        return "Review{" + "id=" + getId() + ", score=" + getScore() + ", name="
                + getName() + ", category=" + getCategory() + ", date="
                + getDate() + ", count=" + getCount() + '}';
    }

}
