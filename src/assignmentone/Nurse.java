package assignmentone;

import java.util.List;

/**
 * A clinic nurse.
 * <p>
 * A nurse builds on {@link HealthProfessional} and records whether they are
 * authorised to take blood samples.
 * </p>
 */
public class Nurse extends HealthProfessional {

    /** Whether this nurse is authorised to take blood samples. */
    private final boolean canTakeBloodSample;

    /**
     * Full constructor.
     *
     * @param id                  numeric-only ID
     * @param name                nurse's name
     * @param daysAvailable       working weekdays
     * @param canTakeBloodSample  blood-sample collection authority
     */
    public Nurse(String id, String name, List<String> daysAvailable,
                 boolean canTakeBloodSample) {
        super(id, name, daysAvailable);
        this.canTakeBloodSample = canTakeBloodSample;
    }

    /**
     * Convenience constructor that defaults to blood-sample authority.
     *
     * @param id            numeric-only ID
     * @param name          nurse's name
     * @param daysAvailable working weekdays
     */
    public Nurse(String id, String name, List<String> daysAvailable) {
        this(id, name, daysAvailable, true);
    }

    /**
     * Returns the nurse role label.
     *
     * @return "Nurse"
     */
    @Override
    public String roleDescription() {
        return "Nurse";
    }

    /**
     * Reports whether this nurse can take blood samples.
     *
     * @return true if blood samples can be collected
     */
    public boolean canTakeBloodSample() {
        return canTakeBloodSample;
    }

    /**
     * Combines the shared details with the blood-sample flag.
     *
     * @return readable summary including sample authority
     */
    @Override
    public String toString() {
        return super.toString() + ", Takes blood samples: " + canTakeBloodSample;
    }
}
