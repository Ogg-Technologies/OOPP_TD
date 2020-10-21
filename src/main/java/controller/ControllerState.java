package controller;

import config.Config;
import model.game.tower.Tower;
import model.game.tower.TowerFactory;
import model.game.tower.concretetowers.*;
import view.ControllerStateValues;

/**
 * @author Sebastian, Samuel, Erik
 * The state of the controller.
 * Is used by controller.
 */
public class ControllerState implements ControllerStateValues {

    private final TowerProxy[] towerProxies;

    private TowerProxy selectedTower;
    private int startIndex = 0;

    ControllerState(TowerFactory factory) {
        this.towerProxies = setupTowerProxies(factory);
    }

    private TowerProxy[] setupTowerProxies(TowerFactory factory) {
        return new TowerProxy[]{
                new TowerProxy(factory::createGrizzlyBear, Config.GrizzlyBear.RANGE, GrizzlyBear.class, Config.GrizzlyBear.COST),
                new TowerProxy(factory::createBearryPotter, Config.BearryPotter.RANGE, BearryPotter.class, Config.BearryPotter.COST),
                new TowerProxy(factory::createBarbearian, Config.Barbearian.RANGE, Barbearian.class, Config.Barbearian.COST),
                new TowerProxy(factory::createJustinBeeBear, Config.JustinBeeBear.RANGE, JustinBeeBear.class, Config.JustinBeeBear.COST),
                new TowerProxy(factory::createSovietBear, Config.SovietBear.RANGE, SovietBear.class, Config.SovietBear.COST),
                new TowerProxy(factory::createBearGrylls, Config.BearGrylls.RANGE, BearGrylls.class, Config.BearGrylls.COST),
                new TowerProxy(factory::createRubixCubeBear, Config.RubixCubeBear.RANGE, RubixCubeBear.class, Config.RubixCubeBear.COST),
                new TowerProxy(factory::createBeer, Config.Beer.RANGE, Beer.class, Config.Beer.COST),
                new TowerProxy(factory::createBazookaBear, Config.BazookaBear.RANGE, BazookaBear.class, Config.BazookaBear.COST),
                new TowerProxy(factory::createSniperBear, Config.SniperBear.RANGE, SniperBear.class, Config.SniperBear.COST),
                new TowerProxy(factory::createBearon, Config.Bearon.RANGE, Bearon.class, Config.Bearon.COST),
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
