package edu.ucsb.cs156.happiercows.formulas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CowHealthUpdateFormulasTest.Config.class)
class CowHealthUpdateFormulasTest {

    @TestConfiguration
    @ComponentScan(basePackageClasses = CowHealthUpdateFormulas.class)
    static class Config {
    }

    @Autowired
    CowHealthUpdateFormulas cowHealthUpdateFormulas;

    @Test
    void getAll_returns_all_formulas() {
        var formulas = cowHealthUpdateFormulas.getAll();
        // not an exhaustive list
        var expectedClasses = new Class<?>[]{
                LinearUpdateFormula.class,
                ConstantUpdateFormula.class,
                NoopUpdateFormula.class
        };
        for (var expectedClass : expectedClasses) {
            var found = formulas.stream().filter(expectedClass::isInstance).findAny();
            assertTrue(found.isPresent(), "Expected to find " + expectedClass.getName() + " in formulas");
        }
    }

    @Test
    void getById_returns_formula_if_id_exists() {
        var formula = cowHealthUpdateFormulas.getById("constant");
        assertTrue(formula.isPresent());
        assertInstanceOf(ConstantUpdateFormula.class, formula.get());
    }

    @Test
    void getById_returns_empty_if_id_does_not_exist() {
        var formula = cowHealthUpdateFormulas.getById("does-not-exist");
        assertTrue(formula.isEmpty());
    }


    @Test
    void no_errors_if_no_duplicate() {
        var formulasService = new CowHealthUpdateFormulas();
        assertDoesNotThrow(() -> formulasService.setFormulas(new CowHealthUpdateFormula[]{
                new ConstantUpdateFormula(),
                new LinearUpdateFormula(),
        }));
    }

    @Test
    void errors_if_duplicate_id_found() {
        var formulasService = new CowHealthUpdateFormulas();
        assertThrows(RuntimeException.class, () -> formulasService.setFormulas(new CowHealthUpdateFormula[]{
                new ConstantUpdateFormula(),
                new ConstantUpdateFormula(),
        }));
    }
}
