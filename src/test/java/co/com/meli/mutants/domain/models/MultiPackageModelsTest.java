package co.com.meli.mutants.domain.models;

import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MultiPackageModelsTest {

    @Test
    public void validate() {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterMustExistRule(),
                        new GetterMustExistRule())
                .with(new SetterTester(),
                        new GetterTester())
                .build();
        final List<String> pojoClasses = new ArrayList<>();
        pojoClasses.add("co.com.meli.mutants.application.response");
        pojoClasses.add("co/com/meli/mutants/application/request");
        pojoClasses.add("co/com/meli/mutants/domain/models");
        pojoClasses.forEach(validator::validate);
    }
}
