package assignmentone;

import java.util.List;

/**
 * A dietitian.
 * <p>
 * A dietitian builds on {@link HealthProfessional} and records their area of
 * dietary focus, for example "Sport nutrition" or "Diabetes nutrition".
 * </p>
 */
public class Dietitian extends HealthProfessional {

    /** Area of dietary focus, for example "Diabetes nutrition". */
    private final String dietaryFocus;

    /**
     * Full constructor.
     *
     * @param id            numeric-only ID
     * @param name          dietitian's name
     * @param daysAvailable working weekdays
     * @param dietaryFocus  area of dietary specialisation
     */
    public Dietitian(String id, String name, List<String> daysAvailable, String dietaryFocus) {
        super(id, name, daysAvailable);
        if (dietaryFocus == null || dietaryFocus.trim().isEmpty()) {
            throw new IllegalArgumentException("Dietary focus cannot be blank.");
        }
        this.dietaryFocus = dietaryFocus.trim();
    }

    /**
     * Returns the dietitian role label together with the focus area.
     *
     * @return dietitian description
     */
    @Override
    public String roleDescription() {
        return "Dietitian (" + dietaryFocus + ")";
    }

    /**
     * Returns the dietary focus area.
     *
     * @return focus area
     */
    public String getDietaryFocus() {
        return dietaryFocus;
    }

    /**
     * Combines the shared details with the dietary focus.
     *
     * @return readable summary including focus area
     */
    @Override
    public String toString() {
        return super.toString() + ", Dietary focus: " + dietaryFocus;
    }
}
