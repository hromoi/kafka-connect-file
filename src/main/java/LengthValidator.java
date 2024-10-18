public class LengthValidator extends AbstractValidator {
    private final int expectedLength;

    public LengthValidator(ConfigLoader configLoader) {
        this.expectedLength = configLoader.getLineLength();
    }

    @Override
    protected boolean isValid(String line, ValidationContext context) {
        return line.length() == expectedLength;
    }
}
