package edu.ucsb.cs156.happiercows.formulas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CowHealthUpdateFormulas {

    private CowHealthUpdateFormula[] formulas;

    @Autowired
    public void setFormulas(CowHealthUpdateFormula[] formulas) {
        this.formulas = formulas;

        var ids = new HashSet<String>();
        for (var formula : formulas) {
            if (!ids.add(formula.getId())) {
                throw new RuntimeException("Multiple cow health update formulas found with id \"" + formula.getId() + "\"");
            }
        }
    }

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
