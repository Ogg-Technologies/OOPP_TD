package application.mapreadservice;

/**
 * @author Erik
 * <p>
 * Factory for concrete implementations of the MapReader interface
 */
public final class MapReaderServiceFactory {

    private MapReaderServiceFactory() {
    }

    public static MapReader createMapReader() {
        return new MapFileReader();
    }
}
