package assignmentone;

/**
 * A single appointment.
 * <p>
 * An appointment links a {@link Patient} to a chosen {@link HealthProfessional}
 * at a specific time slot on the current day. Because it references the generic
 * {@link HealthProfessional} type, it can work with any professional subtype.
 * </p>
 * <p>
 * The class implements {@link Comparable} so that a collection of appointments
 * can be ordered from earliest to latest. The slot is stored as a "HH:mm"
 * string, whose natural ordering matches chronological ordering.
 * </p>
 */
public class Appointment implements Comparable<Appointment> {

    /** The patient who booked the appointment. */
    private final Patient patient;

    /** The professional the patient wants to see. */
    private final HealthProfessional professional;

    /** Booked time slot in "HH:mm" format, for example "09:30". */
    private final String timeSlot;

    /**
     * Constructs an appointment.
     *
     * @param patient      booking patient
     * @param professional chosen health professional
     * @param timeSlot     booked slot in "HH:mm" format
     * @throws IllegalArgumentException if any required detail is missing
     */
    public Appointment(Patient patient, HealthProfessional professional, String timeSlot) {
        if (patient == null) {
            throw new IllegalArgumentException("An appointment must have a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("An appointment must have a professional.");
        }
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            throw new IllegalArgumentException("An appointment must have a time slot.");
        }
        this.patient = patient;
        this.professional = professional;
        this.timeSlot = timeSlot.trim();
    }

    /**
     * Returns the patient.
     *
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the chosen professional.
     *
     * @return health professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the booked time slot.
     *
     * @return time slot in "HH:mm" format
     */
    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Orders appointments by time slot. Slots at the same time are considered
     * equal for ordering purposes; different professionals may then appear in
     * any relative order.
     *
     * @param other appointment to compare against
     * @return negative, zero, or positive as this slot is earlier, equal, or
     *         later than the other slot
     */
    @Override
    public int compareTo(Appointment other) {
        return this.timeSlot.compareTo(other.timeSlot);
    }

    /**
     * Returns the appointment details, including the full professional details.
     *
     * @return readable summary of the appointment
     */
    @Override
    public String toString() {
        return "Time: " + timeSlot
                + " | Patient: " + patient
                + " | Professional: " + professional;
    }
}
