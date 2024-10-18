import java.util.Set;

public class ValidationContext {
    private final Set<String> uniqueRecords;

    public ValidationContext(Set<String> uniqueRecords) {
        this.uniqueRecords = uniqueRecords;
    }

    public boolean addUniqueRecord(String record) {
        return uniqueRecords.add(record);
    }
}
