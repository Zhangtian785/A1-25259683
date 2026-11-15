package assignmentone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the collection of appointments for the clinic.
 * <p>
 * The manager supports adding appointments, filtering them by professional ID
 * or patient phone number, sorting them by time, and cancelling them. A
 * {@link List} is used to store the appointments because the collection grows
 * and shrinks dynamically and needs to be traversed for filtering and sorting.
 * </p>
 * <p>
 * The clinic's fixed daily schedule of time slots is held in one constant
 * collection. Changing the schedule only requires editing this class, and the
 * rest of the booking system works with the new schedule unchanged.
 * </p>
 */
public class AppointmentManager {

    /** Fixed daily schedule: 08:00 to 16:00 in 30-minute intervals. */
    private static final List<String> TIME_SLOTS = buildTimeSlots();

    /** All appointments currently held by the clinic. */
    private final List<Appointment> appointments = new ArrayList<>();

    /**
     * Builds the fixed daily schedule from 08:00 to 16:00 in 30-minute steps.
     *
     * @return ordered list of "HH:mm" slot strings
     */
    private static List<String> buildTimeSlots() {
        List<String> slots = new ArrayList<>();
        for (int minutes = 8 * 60; minutes <= 16 * 60; minutes += 30) {
            int hour = minutes / 60;
            int minute = minutes % 60;
            slots.add(String.format("%02d:%02d", hour, minute));
        }
        return slots;
    }

    /**
     * Returns a copy of the fixed daily time slots.
     *
     * @return copy of the schedule
     */
    public static List<String> getTimeSlots() {
        return new ArrayList<>(TIME_SLOTS);
    }

    /**
     * Attempts to add an appointment.
     * <p>
     * The booking is rejected if the slot is outside the daily schedule, or if
     * the same professional is already booked into that slot.
     * </p>
     *
     * @param appointment appointment to add
     * @return an empty string on success, otherwise a warning message
     */
    public String addAppointment(Appointment appointment) {
        if (appointment == null) {
            return "Warning: appointment cannot be null.";
        }
        if (!TIME_SLOTS.contains(appointment.getTimeSlot())) {
            return "Warning: " + appointment.getTimeSlot()
                    + " is not a valid time slot in the daily schedule.";
        }
        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(appointment.getProfessional())
                    && existing.getTimeSlot().equals(appointment.getTimeSlot())) {
                return "Warning: " + appointment.getProfessional().getName()
                        + " is already booked at " + appointment.getTimeSlot() + ".";
            }
        }
        appointments.add(appointment);
        return "";
    }

    /**
     * Returns all appointments booked with the professional matching the ID.
     *
     * @param professionalId professional ID to search for
     * @return matching appointments (empty list if none match)
     */
    public List<Appointment> filterByProfessionalId(String professionalId) {
        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /**
     * Returns all appointments booked by the patient matching the phone number.
     *
     * @param phone patient mobile number to search for
     * @return matching appointments (empty list if none match)
     */
    public List<Appointment> filterByPatientPhone(String phone) {
        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(phone)) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /**
     * Returns a sorted copy of the given appointments, earliest slot first.
     * The source collection is not modified.
     *
     * @param source appointments to sort
     * @return new list ordered by time slot
     */
    public List<Appointment> sortedByTime(List<Appointment> source) {
        List<Appointment> copy = new ArrayList<>(source);
        Collections.sort(copy);
        return copy;
    }

    /**
     * Cancels the appointment for the given professional ID at the given slot.
     *
     * @param professionalId professional ID of the appointment to cancel
     * @param timeSlot       time slot of the appointment to cancel
     * @return an empty string on success, otherwise a warning message
     */
    public String cancelByProfessionalAndTime(String professionalId, String timeSlot) {
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)
                    && appointment.getTimeSlot().equals(timeSlot)) {
                appointments.remove(appointment);
                return "";
            }
        }
        return "Warning: no appointment found for professional ID " + professionalId
                + " at " + timeSlot + ".";
    }

    /**
     * Cancels every appointment booked under the given patient phone number.
     *
     * @param phone patient mobile number whose appointments are cancelled
     * @return an empty string on success, otherwise a warning message
     */
    public String cancelByPatientPhone(String phone) {
        List<Appointment> toRemove = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(phone)) {
                toRemove.add(appointment);
            }
        }
        if (toRemove.isEmpty()) {
            return "Warning: no appointment found for patient phone number " + phone + ".";
        }
        appointments.removeAll(toRemove);
        return "";
    }

    /**
     * Returns a copy of all current appointments.
     *
     * @return copy of the appointment list
     */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }
}
