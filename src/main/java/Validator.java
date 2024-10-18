public interface Validator {
    void setNext(Validator next);
    boolean validate(String line, ValidationContext context);
}
