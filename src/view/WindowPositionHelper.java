package view;

import utils.Vector;

public interface WindowPositionHelper {
    Vector convertFromRealPosToTilePos(Vector realPos);

    Vector getOffset();
}
