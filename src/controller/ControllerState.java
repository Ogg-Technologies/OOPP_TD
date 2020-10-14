package controller;

import application.Constant;
import model.game.tower.Tower;
import model.game.tower.TowerFactory;
import model.game.tower.concretetowers.*;
import view.ControllerStateValue;

/**
 * @author Sebastian, Samuel
 * The state of the controller.
 * Is used by controller.
 */
public class ControllerState implements ControllerStateValue {
    TowerProxy selectedTower;

    TowerProxy[] towerProxies;

    private int startIndex = 0;

    ControllerState(TowerFactory factory) {
        setupTowerProxies(factory);
    }

    private void setupTowerProxies(TowerFactory factory) {
        towerProxies = new TowerProxy[]{
                new TowerProxy(factory::createGrizzlyBear, Constant.getInstance().GRIZZLY_BEAR.RANGE, GrizzlyBear.class, Constant.getInstance().GRIZZLY_BEAR.COST),
                new TowerProxy(factory::createBearryPotter, Constant.getInstance().BEARRY_POTTER.RANGE, BearryPotter.class, Constant.getInstance().BEARRY_POTTER.COST),
                new TowerProxy(factory::createSniperBear, Constant.getInstance().SNIPER_BEAR.RANGE, SniperBear.class, Constant.getInstance().SNIPER_BEAR.COST),
                new TowerProxy(factory::createSovietBear, Constant.getInstance().SOVIET_BEAR.RANGE, SovietBear.class, Constant.getInstance().SOVIET_BEAR.COST),
                new TowerProxy(factory::createBarbearian, Constant.getInstance().BARBEARIAN.RANGE, Barbearian.class, Constant.getInstance().BARBEARIAN.COST),
                new TowerProxy(factory::createBearGrylls, Constant.getInstance().BEAR_GRYLLS.RANGE, BearGrylls.class, Constant.getInstance().BEAR_GRYLLS.COST),
        };
    }

    /**
     * Sets selected tower with an index of the corresponding button
     *
     * @param index the index of the button
     */
    public void setSelectedTowerWithIndex(int index) {
        if (startIndex + index < towerProxies.length) {
            selectedTower = towerProxies[startIndex + index];
        } else {
            selectedTower = null;
        }
    }

    @Override
    public Class<? extends Tower> getSelectedTowerType() {
        if (selectedTower == null) {
            return null;
        }
        return selectedTower.getTowerType();
    }

    @Override
    public double getSelectedTowerRange() {
        return selectedTower.getRange();
    }

    @Override
    public Class<? extends Tower>[] getAllTowerTypes() {
        Class<? extends Tower>[] towerTypes = new Class[towerProxies.length];
        for (int i = 0; i < towerProxies.length; i++) {
            towerTypes[i] = towerProxies[i].getTowerType();
        }
        return towerTypes;
    }

    @Override
    public int getTowerPrice(Class<? extends Tower> towerType) {
        for (TowerProxy towerProxy : towerProxies) {
            if (towerProxy.correctTowerType(towerType)) {
                return towerProxy.getPrice();
            }
        }
        return -1;
    }

    @Override
    public void changeStartIndex(int term) {
        if (term + startIndex < 0 || term + startIndex >= towerProxies.length) {
            return;
        }
        startIndex += term;
    }

    @Override
    public int getTowerPanelStartIndex() {
        return startIndex;
    }

    @Override
    public int getTotalAmountOfTowers() {
        return towerProxies.length;
    }
}
