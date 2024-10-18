import java.util.Arrays;

public class FillerValidator extends AbstractValidator {
    private final int[] fillerPositions;
    private final String fillerCharacter;
    private final int fillerLength;

    public FillerValidator(ConfigLoader configLoader) {
        this.fillerPositions = configLoader.getFillerPositions();
        this.fillerCharacter = configLoader.getFillerCharacter();
        this.fillerLength = configLoader.getFillerLength();
    }

    @Override
    protected boolean isValid(String line, ValidationContext context) {
        return Arrays.stream(fillerPositions)
                .mapToObj(pos -> line.substring(pos, pos + fillerLength))
                .allMatch(filler -> filler.equals(fillerCharacter.repeat(fillerLength)));
    }
}
