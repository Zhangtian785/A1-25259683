package assignmentone;

import java.util.List;

/**
 * A specialist, such as a cardiologist, radiologist or paediatrician.
 * <p>
 * A specialist builds on {@link HealthProfessional} and records the named
 * medical specialty they practise in.
 * </p>
 */
public class Specialist extends HealthProfessional {

    /** Medical specialty, for example "Cardiology". */
    private final String specialty;

    /**
     * Full constructor.
     *
     * @param id            numeric-only ID
     * @param name          specialist's name
     * @param daysAvailable working weekdays
     * @param specialty     medical specialty practised
     */
    public Specialist(String id, String name, List<String> daysAvailable, String specialty) {
        super(id, name, daysAvailable);
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialty cannot be blank.");
        }
        this.specialty = specialty.trim();
    }

    /**
     * Returns the specialist role label together with the specialty.
     *
     * @return specialist description
     */
    @Override
    public String roleDescription() {
        return "Specialist (" + specialty + ")";
    }

    /**
     * Returns the medical specialty.
     *
     * @return specialty name
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Combines the shared details with the specialty.
     *
     * @return readable summary including specialty
     */
    @Override
    public String toString() {
        return super.toString() + ", Specialty: " + specialty;
    }
}
