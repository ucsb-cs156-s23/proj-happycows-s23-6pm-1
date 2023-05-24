package edu.ucsb.cs156.happiercows.formulas;

import edu.ucsb.cs156.happiercows.entities.Commons;
import edu.ucsb.cs156.happiercows.entities.UserCommons;
import org.springframework.stereotype.Component;

@Component
public class NoopUpdateFormula implements CowHealthUpdateFormula {

    @Override
    public String getId() {
        return "noop";
    }

    @Override
    public String getName() {
        return "Do nothing";
    }

    @Override
    public String getDescription() {
        return "Cow health does not change.";
    }

    @Override
    public int getOrder() {
        return 1000;
    }

    @Override
    public double calculateNewCowHealth(Commons commons, UserCommons user, int totalCows) {
        return user.getCowHealth();
    }
}
