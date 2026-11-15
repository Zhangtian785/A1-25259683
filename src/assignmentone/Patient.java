package assignmentone;

import java.util.Objects;

/**
 * A clinic patient.
 * <p>
 * Stores the basic patient details required for an appointment: the patient's
 * name and a mobile contact number. Validation in the constructor guarantees
 * a patient object can never be created with missing details.
 * </p>
 */
public class Patient {

    /** Patient's full name. */
    private final String name;

    /** Patient's mobile phone number. */
    private final String mobile;

    /**
     * Constructs a patient.
     *
     * @param name   patient's full name
     * @param mobile patient's mobile number
     * @throws IllegalArgumentException if either value is blank
     */
    public Patient(String name, String mobile) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be blank.");
        }
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient mobile number cannot be blank.");
        }
        this.name = name.trim();
        this.mobile = mobile.trim();
    }

    /**
     * Returns the patient's name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's mobile number.
     *
     * @return mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Two patients are equal when their name and mobile number match.
     *
     * @param o object to compare
     * @return true if both patients have the same name and mobile
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) o;
        return Objects.equals(name, other.name) && Objects.equals(mobile, other.mobile);
    }

    /**
     * Returns the hash code based on name and mobile.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, mobile);
    }

    /**
     * Returns the patient details in a readable form.
     *
     * @return readable summary of the patient
     */
    @Override
    public String toString() {
        return name + " (" + mobile + ")";
    }
}
