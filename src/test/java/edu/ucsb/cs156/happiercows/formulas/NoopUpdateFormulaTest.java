package edu.ucsb.cs156.happiercows.formulas;

import edu.ucsb.cs156.happiercows.entities.UserCommons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoopUpdateFormulaTest {

    @Test
    void check_properties() {
        var formula = new NoopUpdateFormula();
        assertEquals("noop", formula.getId());
        assertEquals("Do nothing", formula.getName());
        assertEquals("Cow health does not change.", formula.getDescription());
    }

    @Test
    void calculateNewCowHealth_does_not_update_health() {
        var formula = new NoopUpdateFormula();
        var user = UserCommons.builder()
                .cowHealth(50)
                .build();

        assertEquals(50, formula.calculateNewCowHealth(null, user, 50));
        assertEquals(50, formula.calculateNewCowHealth(null, user, 100));
    }
}
