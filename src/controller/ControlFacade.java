package controller;

import utils.Vector;

public interface ControlFacade {
    Vector convertFromRealPosToTilePos(Vector realPos);
    Vector getOffset();
}
