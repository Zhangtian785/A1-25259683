package assignmentone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstration class for the clinic appointment system.
 * <p>
 * The main method acts as a short, scripted simulation: it creates professional
 * and patient objects, books and cancels appointments, and prints the results
 * to the console. There is no user interface; this class stands in for real
 * users exercising the system.
 * </p>
 * <p>
 * Part 3 shows the different professional types stored and processed together
 * through their common {@link HealthProfessional} type. Part 6 demonstrates the
 * appointment manager end-to-end, covering both successful operations and
 * warning/error cases.
 * </p>
 */
public class AssignmentOne {

    /**
     * Entry point of the demonstration.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        demonstrateProfessionals();
        demonstrateAppointments();
    }

    /**
     * Part 3: create several professionals of different types, store them
     * together in one list, and print each one's full details.
     */
    private static void demonstrateProfessionals() {
        System.out.println("==================================================");
        System.out.println("PART 3: Health professionals in the clinic");
        System.out.println("==================================================");

        // All subtypes are held through the common abstract type, so the list
        // can mix GPs, specialists, nurses and dietitians without separate code.
        List<HealthProfessional> professionals = new ArrayList<>();

        professionals.add(new GeneralPractitioner(
                "101", "Alice Wong", Arrays.asList("Monday", "Wednesday", "Friday"), true));
        professionals.add(new GeneralPractitioner(
                "102", "Brian Lee", Arrays.asList("Tuesday", "Thursday"), true));
        professionals.add(new GeneralPractitioner(
                "103", "Carla Zhou", Arrays.asList("Monday", "Wednesday"), false));
        professionals.add(new Specialist(
                "201", "David Kim", Arrays.asList("Monday", "Thursday"), "Cardiology"));
        professionals.add(new Nurse(
                "301", "Emily Tran", Arrays.asList("Monday", "Tuesday", "Wednesday",
                        "Thursday", "Friday"), true));
        professionals.add(new Dietitian(
                "401", "Fiona Wu", Arrays.asList("Wednesday", "Friday"), "Diabetes nutrition"));

        for (HealthProfessional professional : professionals) {
            // Polymorphism: each object prints its own type-specific details.
            System.out.println("- " + professional);
        }
        System.out.println();
    }

    /**
     * Part 6: exercise the appointment manager end-to-end, including adding,
     * filtering, sorting, cancelling, and the cases where an action fails.
     */
    private static void demonstrateAppointments() {
        System.out.println("==================================================");
        System.out.println("PART 6: Appointment system demonstration");
        System.out.println("==================================================");

        AppointmentManager manager = new AppointmentManager();

        // Recreate the professionals used in the bookings.
        HealthProfessional gpAlice = new GeneralPractitioner(
                "101", "Alice Wong", Arrays.asList("Monday", "Wednesday", "Friday"), true);
        HealthProfessional gpBrian = new GeneralPractitioner(
                "102", "Brian Lee", Arrays.asList("Tuesday", "Thursday"), true);
        HealthProfessional cardiologist = new Specialist(
                "201", "David Kim", Arrays.asList("Monday", "Thursday"), "Cardiology");
        HealthProfessional nurseEmily = new Nurse(
                "301", "Emily Tran", Arrays.asList("Monday", "Friday"), true);
        HealthProfessional dietitianFiona = new Dietitian(
                "401", "Fiona Wu", Arrays.asList("Wednesday", "Friday"), "Diabetes nutrition");

        // Patients for the bookings.
        Patient alex = new Patient("Alex Chen", "0412345601");
        Patient bella = new Patient("Bella Smith", "0412345602");
        Patient chris = new Patient("Chris Brown", "0412345603");
        Patient diana = new Patient("Diana Prince", "0412345604");

        System.out.println("--- Adding appointments ---");
        book(manager, new Appointment(alex, gpAlice, "09:00"));
        book(manager, new Appointment(bella, cardiologist, "09:30"));
        book(manager, new Appointment(chris, nurseEmily, "10:00"));
        book(manager, new Appointment(diana, dietitianFiona, "10:30"));
        book(manager, new Appointment(alex, gpBrian, "11:00"));
        book(manager, new Appointment(bella, gpAlice, "11:30"));

        // Error cases: double-booking the same professional in the same slot,
        // and a slot outside the daily schedule.
        System.out.println();
        System.out.println("--- Rejected bookings (error cases) ---");
        book(manager, new Appointment(chris, gpAlice, "09:00"));
        book(manager, new Appointment(chris, nurseEmily, "17:00"));

        System.out.println();
        printAppointments("--- All appointments (sorted by time) ---",
                manager.getAllAppointments(), manager);

        System.out.println();
        printAppointments("--- Filtered: professional ID 101 ---",
                manager.filterByProfessionalId("101"), manager);

        System.out.println();
        printAppointments("--- Filtered: patient phone 0412345601 ---",
                manager.filterByPatientPhone("0412345601"), manager);

        System.out.println();
        System.out.println("--- Cancelling appointments ---");
        String cancelBySlot = manager.cancelByProfessionalAndTime("301", "10:00");
        report(cancelBySlot, "Cancelled nurse Emily Tran at 10:00");

        String cancelByPhone = manager.cancelByPatientPhone("0412345604");
        report(cancelByPhone, "Cancelled all appointments for Diana Prince");

        // Error case: cancelling an appointment that does not exist.
        String cancelMissing = manager.cancelByProfessionalAndTime("999", "08:00");
        report(cancelMissing, "Cancelled professional 999 at 08:00");

        String cancelUnknownPhone = manager.cancelByPatientPhone("0499999999");
        report(cancelUnknownPhone, "Cancelled patient 0499999999");

        System.out.println();
        printAppointments("--- Appointments after cancellation (sorted by time) ---",
                manager.getAllAppointments(), manager);
    }

    /**
     * Attempts to book an appointment and prints the outcome.
     *
     * @param manager     appointment manager
     * @param appointment appointment to book
     */
    private static void book(AppointmentManager manager, Appointment appointment) {
        String outcome = manager.addAppointment(appointment);
        if (outcome.isEmpty()) {
            System.out.println("Booked: " + appointment);
        } else {
            System.out.println(outcome);
        }
    }

    /**
     * Prints the result of a cancellation attempt.
     *
     * @param outcome  empty string on success, otherwise a warning message
     * @param attempt  description of the attempted cancellation
     */
    private static void report(String outcome, String attempt) {
        if (outcome.isEmpty()) {
            System.out.println("Success: " + attempt + ".");
        } else {
            System.out.println(outcome);
        }
    }

    /**
     * Prints a heading and the given appointments sorted earliest to latest.
     *
     * @param heading  section heading
     * @param list     appointments to display
     * @param manager  manager used to sort the list
     */
    private static void printAppointments(String heading, List<Appointment> list,
                                          AppointmentManager manager) {
        System.out.println(heading);
        if (list.isEmpty()) {
            System.out.println("  (no appointments)");
            return;
        }
        for (Appointment appointment : manager.sortedByTime(list)) {
            System.out.println("  " + appointment);
        }
    }
}
