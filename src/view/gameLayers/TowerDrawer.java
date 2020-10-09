package view.gameLayers;

import application.Constant;
import model.game.tower.Tower;
import model.game.tower.TowerVisitor;
import model.game.tower.concretetowers.*;
import utils.Vector;
import view.WindowState;
import view.texture.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Oskar, Sebastian, Samuel, Erik
 * Display towers.
 * Is used by swingView.
 */

public class TowerDrawer implements TowerVisitor {

    private final Graphics graphics;
    private final WindowState windowState;

    public TowerDrawer(Graphics g, WindowState windowState) {
        graphics = g;
        this.windowState = windowState;
    }


    @Override
    public void visit(Tower tower) {
        throw new RuntimeException("This tower: " + tower.getClass().getSimpleName() + " is not implemented in view");
    }

    @Override
    public void visit(GrizzlyBear tower) { //TODO Possible more refactoring by adding a getAngle to tower interface

        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage(Constant.getInstance().IMAGE_PATH.GRIZZLY_BEAR, tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    @Override
    public void visit(BearryPotter tower) {

        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage(Constant.getInstance().IMAGE_PATH.BEARRY_POTTER, tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    @Override
    public void visit(SniperBear tower) {

        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage(Constant.getInstance().IMAGE_PATH.SNIPER_BEAR, tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    @Override
    public void visit(SovietBear tower) {

        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage("resource/sovietBear.png", tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    @Override
    public void visit(Barbearian tower) {
        Vector pos = getRealPos(tower.getPos());
        BufferedImage rotatedImage = ImageHandler.getImage("resource/barbearian.png", tower.getAngle());
        drawTower(rotatedImage, pos, tower.getAngle());
    }

    private void drawTower(Image image, Vector pos, double angle) {

        final double sin = Math.abs(Math.sin(angle));
        final double cos = Math.abs(Math.cos(angle));

        double width = windowState.getTileSize() * cos + windowState.getTileSize() * sin;
        int offset = (int) ((width - windowState.getTileSize()) / 2);

        graphics.drawImage(image, pos.getIntX() - offset, pos.getIntY() - offset, (int) width, (int) width, null);
    }

    private Vector getRealPos(Vector pos) {
        int x = (int) (windowState.getTileSize() * pos.x + windowState.getTileMapOffset().x);
        int y = (int) (windowState.getTileSize() * pos.y + windowState.getTileMapOffset().y);
        return new Vector(x, y);
    }

}
