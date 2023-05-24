package edu.ucsb.cs156.happiercows.formulas;

import edu.ucsb.cs156.happiercows.entities.Commons;
import edu.ucsb.cs156.happiercows.entities.UserCommons;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * A strategy for updating cow health.
 * <p>
 * Implementations should be annotated with {@link Component} so they can be automatically
 * discovered by {@link CowHealthUpdateFormulas}.
 * <p>
 * {@link #getOrder()} determines the order in which formulas are shown to the user.
 */
public interface CowHealthUpdateFormula extends Ordered {
    /**
     * A unique identifier for this strategy. This should not be changed, as this is what is stored in the database.
     */
    String getId();

    /**
     * A user-friendly name for this formula.
     */
    String getName();

    /**
     * A user-friendly description of this formula.
     */
    String getDescription();

    /**
     * Calculates new cow health using this strategy.
     * The result will be clamped between 0 and 100.
     */
    double calculateNewCowHealth(Commons commons, UserCommons user, int totalCows);
}
