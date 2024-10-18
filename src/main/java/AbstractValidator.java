public abstract class AbstractValidator implements Validator {
    protected Validator next;

    @Override
    public void setNext(Validator next) {
        this.next = next;
    }

    @Override
    public boolean validate(String line, ValidationContext context) {
        if (isValid(line, context)) {
            return next == null || next.validate(line, context);
        }
        return false;
    }

    protected abstract boolean isValid(String line, ValidationContext context);
}
