package edu.ucsb.cs156.happiercows.formulas;

import edu.ucsb.cs156.happiercows.entities.Commons;
import edu.ucsb.cs156.happiercows.entities.UserCommons;
import org.springframework.stereotype.Component;

@Component
public class LinearUpdateFormula implements CowHealthUpdateFormula {

    @Override
    public String getId() {
        return "linear";
    }

    @Override
    public String getName() {
        return "Linear";
    }

    @Override
    public String getDescription() {
        return "Cow health changes proportionally to the number of cows over/under the carrying capacity, controlled by the degradation rate.";
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public double calculateNewCowHealth(Commons commons, UserCommons user, int totalCows) {
        return user.getCowHealth() - (totalCows - commons.getCarryingCapacity()) * commons.getDegradationRate();
    }
}
