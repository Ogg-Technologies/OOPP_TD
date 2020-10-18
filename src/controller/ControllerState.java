package controller;

import config.Config;
import model.game.tower.Tower;
import model.game.tower.TowerFactory;
import model.game.tower.concretetowers.*;
import view.ControllerStateValue;

/**
 * @author Sebastian, Samuel, Erik
 * The state of the controller.
 * Is used by controller.
 */
public class ControllerState implements ControllerStateValue {

    private final TowerProxy[] towerProxies;

    private TowerProxy selectedTower;
    private int startIndex = 0;

    ControllerState(TowerFactory factory) {
        this.towerProxies = setupTowerProxies(factory);
    }

    private TowerProxy[] setupTowerProxies(TowerFactory factory) {
        return new TowerProxy[]{
                new TowerProxy(factory::createGrizzlyBear, Config.INSTANCE.GRIZZLY_BEAR.RANGE, GrizzlyBear.class, Config.INSTANCE.GRIZZLY_BEAR.COST),
                new TowerProxy(factory::createBearryPotter, Config.INSTANCE.BEARRY_POTTER.RANGE, BearryPotter.class, Config.INSTANCE.BEARRY_POTTER.COST),
                new TowerProxy(factory::createSniperBear, Config.INSTANCE.SNIPER_BEAR.RANGE, SniperBear.class, Config.INSTANCE.SNIPER_BEAR.COST),
                new TowerProxy(factory::createSovietBear, Config.INSTANCE.SOVIET_BEAR.RANGE, SovietBear.class, Config.INSTANCE.SOVIET_BEAR.COST),
                new TowerProxy(factory::createBarbearian, Config.INSTANCE.BARBEARIAN.RANGE, Barbearian.class, Config.INSTANCE.BARBEARIAN.COST),
                new TowerProxy(factory::createBearGrylls, Config.INSTANCE.BEAR_GRYLLS.RANGE, BearGrylls.class, Config.INSTANCE.BEAR_GRYLLS.COST),
                new TowerProxy(factory::createBeer, Config.INSTANCE.BEER.RANGE, Beer.class, Config.INSTANCE.BEER.COST),
                new TowerProxy(factory::createRubixCubeBear, Config.INSTANCE.RUBIX_CUBE_BEAR.RANGE, RubixCubeBear.class, Config.INSTANCE.RUBIX_CUBE_BEAR.COST),
        };
    }

    public TowerProxy getSelectedTower() {
        return selectedTower;
    }

    public void deselectTower() {
        this.selectedTower = null;
    }

    /**
     * Sets selected tower with an index of the corresponding button + startIndex
     * Cannot reliably handle negative numbers
     *
     * @param index the index of the button
     */
    public void setSelectedTowerWithStartIndex(int index) {
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
        throw new IllegalArgumentException("The tower " + towerType.getSimpleName()
                + " does not have a corresponding tower proxy added");
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
