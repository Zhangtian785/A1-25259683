package assignmentone;

import java.util.List;

/**
 * A general practitioner (GP).
 * <p>
 * A GP is a type of health professional who can prescribe medication. This
 * class reuses all common fields and behaviour from
 * {@link HealthProfessional} and adds the GP-specific capability flag.
 * </p>
 */
public class GeneralPractitioner extends HealthProfessional {

    /** Whether this GP is authorised to prescribe medication. */
    private final boolean canPrescribeMedication;

    /**
     * Full constructor.
     *
     * @param id                      numeric-only ID
     * @param name                    GP's name
     * @param daysAvailable           working weekdays
     * @param canPrescribeMedication  medication prescribing authority
     */
    public GeneralPractitioner(String id, String name, List<String> daysAvailable,
                                boolean canPrescribeMedication) {
        super(id, name, daysAvailable);
        this.canPrescribeMedication = canPrescribeMedication;
    }

    /**
     * Convenience constructor that defaults to prescribing authority.
     *
     * @param id            numeric-only ID
     * @param name          GP's name
     * @param daysAvailable working weekdays
     */
    public GeneralPractitioner(String id, String name, List<String> daysAvailable) {
        this(id, name, daysAvailable, true);
    }

    /**
     * Returns the GP role label.
     *
     * @return "General Practitioner (GP)"
     */
    @Override
    public String roleDescription() {
        return "General Practitioner (GP)";
    }

    /**
     * Reports whether this GP can prescribe medication.
     *
     * @return true if medication can be prescribed
     */
    public boolean canPrescribeMedication() {
        return canPrescribeMedication;
    }

    /**
     * Combines the shared details with the GP-specific flag.
     *
     * @return readable summary including prescribing authority
     */
    @Override
    public String toString() {
        return super.toString() + ", Prescribes medication: " + canPrescribeMedication;
    }
}
