package ru.returnonintelligence.testask.service;

import ru.returnonintelligence.testask.service.interfaces.ValidateService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Anton on 29.03.2015.
 */
public class ValidateServiceImplementation implements ValidateService {

    ValidatorFactory validatorFactory;
    public ValidateServiceImplementation(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }
    /**
     * Return true if bean is valid or false otherwise
     * @param object - bean for validate
     * @return true or false
     */
    @Override
    public Boolean validate(Object object) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        Integer size = constraintViolations.size();
        // good validation
        if (size == 0) {
            return true;
        } else {
            for(ConstraintViolation<Object> cv : constraintViolations) {
                System.err.println("Error validation: " + cv.getMessage() + " value: " + cv.getInvalidValue());
            }
            return false;
        }

    }
}
