package edu.ucsb.cs156.happiercows.formulas;

import edu.ucsb.cs156.happiercows.entities.Commons;
import edu.ucsb.cs156.happiercows.entities.UserCommons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantUpdateFormulaTest {

    @Test
    void calculateNewCowHealth_decreases_cow_health_by_constant_rate() {
        var formula = new ConstantUpdateFormula();
        var commons = Commons.builder()
                .degradationRate(0.1)
                .carryingCapacity(100)
                .build();
        var user = UserCommons.builder()
                .cowHealth(50)
                .build();

        assertEquals(49.9, formula.calculateNewCowHealth(commons, user, 110));
        assertEquals(49.9, formula.calculateNewCowHealth(commons, user, 120));
    }

    @Test
    void calculateNewCowHealth_increases_cow_health_by_constant_rate() {
        var formula = new ConstantUpdateFormula();
        var commons = Commons.builder()
                .degradationRate(0.1)
                .carryingCapacity(100)
                .build();
        var user = UserCommons.builder()
                .cowHealth(50)
                .build();

        assertEquals(50.1, formula.calculateNewCowHealth(commons, user, 90));
        assertEquals(50.1, formula.calculateNewCowHealth(commons, user, 80));
    }

}
