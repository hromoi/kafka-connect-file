import io.streamthoughts.kafka.connect.filepulse.data.TypedStruct;
import io.streamthoughts.kafka.connect.filepulse.processor.RecordProcessor;
import io.streamthoughts.kafka.connect.filepulse.processor.ProcessResult;
import io.streamthoughts.kafka.connect.filepulse.processor.RecordContext;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;

public class ValidationProcessor implements RecordProcessor {

    private final AtomicBoolean isInvalid = new AtomicBoolean(false);
    private final Set<String> uniqueRecords = ConcurrentHashMap.newKeySet();
    private ValidatorChain validatorChain;
    private ConfigLoader configLoader;

    @Override
    public void init(Map<String, ?> configs) {
        String configFilePath = (String) configs.get("filters.MyValidation.config.file");
        this.configLoader = new ConfigLoader(configFilePath);
        this.validatorChain = new ValidatorChain(configLoader);
        uniqueRecords.clear();
        isInvalid.set(false);
    }

    @Override
    public ProcessResult process(TypedStruct record, RecordContext context) {
        if (isInvalid.get()) {
            return ProcessResult.success(record);
        }

        String line = record.getString(Constants.VALUE_KEY);
        ValidationContext validationContext = new ValidationContext(uniqueRecords);

        if (!validatorChain.validate(line, validationContext)) {
            isInvalid.set(true);
            context.metadata().put(Constants.STATUS_KEY, FileStatus.INVALID.name());
        }

        return ProcessResult.success(record);
    }
}
