package edu.ucsb.cs156.happiercows.formulas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CowHealthUpdateFormulas {

    @Autowired
    private CowHealthUpdateFormula[] formulas;

    public List<CowHealthUpdateFormula> getAll() {
        return List.of(formulas);
    }

    public Optional<CowHealthUpdateFormula> getById(String id) {
        for (CowHealthUpdateFormula formula : formulas) {
            if (formula.getId().equals(id)) {
                return Optional.of(formula);
            }
        }
        return Optional.empty();
    }

    public CowHealthUpdateFormula getDefault() {
        return formulas[0];
    }
}
