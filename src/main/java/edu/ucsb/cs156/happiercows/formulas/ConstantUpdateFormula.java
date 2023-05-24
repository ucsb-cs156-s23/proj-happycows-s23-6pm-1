package edu.ucsb.cs156.happiercows.formulas;

import edu.ucsb.cs156.happiercows.entities.Commons;
import edu.ucsb.cs156.happiercows.entities.UserCommons;
import org.springframework.stereotype.Component;

@Component
public class ConstantUpdateFormula implements CowHealthUpdateFormula {

    @Override
    public String getId() {
        return "constant";
    }

    @Override
    public String getName() {
        return "Constant";
    }

    @Override
    public String getDescription() {
        return "Cow health increases/decreases at a constant rate, controlled by the degradation rate.";
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public double calculateNewCowHealth(Commons commons, UserCommons user, int totalCows) {
        if (totalCows < commons.getCarryingCapacity()) {
            return user.getCowHealth() + commons.getDegradationRate();
        } else {
            return user.getCowHealth() - commons.getDegradationRate();
        }
    }
}
