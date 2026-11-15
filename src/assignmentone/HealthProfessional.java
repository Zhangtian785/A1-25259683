package assignmentone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a generic health professional.
 * <p>
 * This abstract class captures the information and behaviour that are common
 * to every health professional in the clinic, regardless of their specialty.
 * It acts as the foundation for the more specific professional types
 * (General Practitioner, Specialist, Nurse, Dietitian) created in Part 2.
 * </p>
 * <p>
 * Encapsulation: the fields are private and can only be read through getters.
 * Validation in the constructor ensures an object can never be placed into an
 * invalid state. The class is abstract, so a "plain" professional with no
 * specialty cannot be instantiated directly.
 * </p>
 */
public abstract class HealthProfessional {

    /** Numeric-only identifier of the professional. */
    private final String id;

    /** Full name of the professional. */
    private final String name;

    /** Fixed set of weekdays the professional works on. */
    private final List<String> daysAvailable;

    /**
     * Full constructor.
     *
     * @param id            numeric-only professional identifier
     * @param name          professional's full name
     * @param daysAvailable weekdays the professional is available
     * @throws IllegalArgumentException if any value is missing or invalid
     */
    protected HealthProfessional(String id, String name, List<String> daysAvailable) {
        if (id == null || !id.trim().matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must contain digits only.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Professional name cannot be blank.");
        }
        if (daysAvailable == null || daysAvailable.isEmpty()) {
            throw new IllegalArgumentException("At least one available day must be provided.");
        }

        this.id = id.trim();
        this.name = name.trim();
        // Defensive copy: the internal list is not exposed to external mutation.
        this.daysAvailable = new ArrayList<>(daysAvailable);
    }

    /**
     * Convenience constructor that uses a default working-day set when the
     * caller does not supply one.
     *
     * @param id   numeric-only professional identifier
     * @param name professional's full name
     */
    protected HealthProfessional(String id, String name) {
        this(id, name, new ArrayList<>(Arrays.asList("Monday", "Wednesday", "Friday")));
    }

    /**
     * Returns a short label describing the professional's type/specialty.
     * Each concrete subclass supplies its own wording.
     *
     * @return human-readable role description
     */
    public abstract String roleDescription();

    /**
     * Returns the professional ID.
     *
     * @return numeric ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the professional's name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the available working days, so the internal
     * collection cannot be modified from outside.
     *
     * @return copy of the available weekdays
     */
    public List<String> getDaysAvailable() {
        return new ArrayList<>(daysAvailable);
    }

    /**
     * Two health professionals are considered equal when they share the same
     * ID, because the ID uniquely identifies a person in the system.
     *
     * @param o object to compare
     * @return true if both professionals have the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional other = (HealthProfessional) o;
        return Objects.equals(id, other.id);
    }

    /**
     * Returns the hash code based on the unique ID.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns all common details in a readable form. Subclasses extend this
     * representation with their own specialty-specific information.
     *
     * @return readable summary of the professional
     */
    @Override
    public String toString() {
        return "[" + roleDescription() + "] ID: " + id
                + ", Name: " + name
                + ", Available: " + String.join(", ", daysAvailable);
    }
}
