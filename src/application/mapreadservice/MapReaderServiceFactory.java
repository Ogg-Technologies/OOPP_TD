package application.mapreadservice;

public final class MapReaderServiceFactory {

    private MapReaderServiceFactory() {
    }

    public static MapReader createMapReader() {
        return new MapFileReader();
    }
}
