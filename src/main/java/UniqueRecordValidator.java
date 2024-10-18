public class UniqueRecordValidator extends AbstractValidator {
    private final int recordNumberStart;
    private final int recordNumberLength;

    public UniqueRecordValidator(ConfigLoader configLoader) {
        this.recordNumberStart = configLoader.getRecordNumberStart();
        this.recordNumberLength = configLoader.getRecordNumberLength();
    }

    @Override
    protected boolean isValid(String line, ValidationContext context) {
        String recordNumber = line.substring(recordNumberStart, recordNumberStart + recordNumberLength).trim();
        return context.addUniqueRecord(recordNumber);
    }
}
