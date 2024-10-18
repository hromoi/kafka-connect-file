public class ValidatorChain {
    private final Validator firstValidator;

    public ValidatorChain(ConfigLoader configLoader) {
        Validator lengthValidator = new LengthValidator(configLoader);
        Validator uniqueRecordValidator = new UniqueRecordValidator(configLoader);
        Validator fillerValidator = new FillerValidator(configLoader);

        lengthValidator.setNext(uniqueRecordValidator);
        uniqueRecordValidator.setNext(fillerValidator);

        this.firstValidator = lengthValidator;
    }

    public boolean validate(String line, ValidationContext context) {
        return firstValidator.validate(line, context);
    }
}
