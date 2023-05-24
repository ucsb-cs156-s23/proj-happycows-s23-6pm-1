package edu.ucsb.cs156.happiercows.formulas;

import edu.ucsb.cs156.happiercows.entities.Commons;
import edu.ucsb.cs156.happiercows.entities.UserCommons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearUpdateFormulaTest {


    @Test
    void check_properties() {
        var formula = new LinearUpdateFormula();
        assertEquals("linear", formula.getId());
        assertEquals("Linear", formula.getName());
        assertEquals("Cow health increases/decreases at a rate proportional to the number of cows over/under the carrying capacity, controlled by the degradation rate.", formula.getDescription());
        assertEquals(1, formula.getOrder());
    }

    @Test
    void calculateNewCowHealth_decreases_proportional_to_cows_over_carrying_rate() {
        var formula = new LinearUpdateFormula();
        var commons = Commons.builder()
                .degradationRate(0.01)
                .carryingCapacity(100)
                .build();
        var user = UserCommons.builder()
                .cowHealth(50)
                .build();
        int totalCows = 110;

        assertEquals(49.9, formula.calculateNewCowHealth(commons, user, totalCows));
    }

    @Test
    void calculateNewCowHealth_increases_proportional_to_cows_over_carrying_rate() {
        var formula = new LinearUpdateFormula();
        var commons = Commons.builder()
                .degradationRate(0.01)
                .carryingCapacity(100)
                .build();
        var user = UserCommons.builder()
                .cowHealth(50)
                .build();
        int totalCows = 90;

        assertEquals(50.1, formula.calculateNewCowHealth(commons, user, totalCows));
    }
}
