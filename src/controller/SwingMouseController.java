package controller;

import model.ModelInputListener;
import model.game.tower.Tower;
import utils.Vector;
import view.ButtonClickHandler;
import view.MouseViewObserver;
import view.OnClickWithoutArgument;
import view.WindowPositionHelper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian, Samuel, Erik
 * A class that trigger events in other classes if a specific mouse action has happened
 * This is used by Controller and View
 */
public class SwingMouseController implements MouseListener, MouseMotionListener, ButtonClickHandler {

    private final WindowPositionHelper windowPositionHelper;
    private final MouseViewObserver viewObserver;
    private final ModelInputListener modelInputListener;


    private Vector tilePressed;
    private Button buttonPressed;

    private List<Button> buttonList = new ArrayList<>();

    /**
     * @param windowPositionHelper a helper class, that convert a "real" position to a wanted position
     * @param viewObserver         an observer for mouse actions for the view
     * @param modelInputListener   an event handler that activate for some mouse action
     */
    public SwingMouseController(WindowPositionHelper windowPositionHelper, MouseViewObserver viewObserver, ModelInputListener modelInputListener) {
        this.windowPositionHelper = windowPositionHelper;
        this.viewObserver = viewObserver;
        this.modelInputListener = modelInputListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Saves some data when mouse is pressed, for when it is released.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        tilePressed = getClickedTile(e);
        buttonPressed = getClickedButton(e);
        ControllerState.selectedTower = null;
    }



    /**
     * Compares if the mouse release was over the same object as when mouse was pressed.
     * If yes, send a method call to model.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("x: " + (e.getX() - windowPositionHelper.getOffset().x) + ", y: " + (e.getY() - windowPositionHelper.getOffset().y));
        Vector clickedTile = getClickedTile(e);
        if (clickedTile != null && clickedTile.equals(tilePressed)) {
            modelInputListener.onTileClick(clickedTile);
        }

        Button clickedButton = getClickedButton(e);
        if(clickedButton != null && clickedButton.equals(buttonPressed)){
            buttonPressed.doAction();
        }
    }

    /**
     * Given a MouseEvent it calculates which tile was clicked and returns it, or null if none was
     *
     * @param e The MouseEvent created by Swing
     * @return The tile position clicked
     */
    private Vector getClickedTile(MouseEvent e) {
        return windowPositionHelper.convertFromRealPosToTilePos(
                new Vector(
                        e.getX() - windowPositionHelper.getOffset().x,
                        e.getY() - windowPositionHelper.getOffset().y
                )
        );
    }

    private Button getClickedButton(MouseEvent e) {
        double x = (e.getX() - windowPositionHelper.getOffset().x) / windowPositionHelper.getWindowSize().x;
        double y = (e.getY() - windowPositionHelper.getOffset().y) / windowPositionHelper.getWindowSize().y;
        for(Button b : buttonList){
            if(b.positionIsInsideButton(new Vector(x, y))){
                return b;
            }
        }
        return null;
    }

    /**
     * Sends the mouse position from the MouseEvent to View to make View update its displaying of mouse position
     * @param e The MouseEvent that triggered the call
     */
    private void updateViewMousePosition(MouseEvent e) {
        Vector pos = new Vector(e.getX() - windowPositionHelper.getOffset().x, e.getY() - windowPositionHelper.getOffset().y);
        viewObserver.updateMousePosition(pos);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateViewMousePosition(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateViewMousePosition(e);
    }

    @Override
    public void addButtonWithoutArgument(Vector percentStartPos, Vector percentSize, OnClickWithoutArgument onClickWithoutArgument) {
        buttonList.add(new Button(percentStartPos, percentSize, onClickWithoutArgument));
    }

    @Override
    public void setSelectedTower(Class<? extends Tower> towerClass) {
        ControllerState.selectedTower = towerClass;
        System.out.println(towerClass.getSimpleName());
    }

    /**
     * This inner classes only objective is to be able to store multiple values as one.
     * It stores the method to be called if button is pressed, and size and position for the button.
     * It is only used in this class.
     *
     * The only thing to think about here is that the position is stored as a percent value,
     * instead of fixed pixel position, to make it more flexible.
     */
    static private class Button {
        private final Vector startPos;
        private final Vector size;

        private final OnClickWithoutArgument onClickWithoutArgument;

        public Button(Vector startPos, Vector size, OnClickWithoutArgument onClickWithoutArgument){
            this.startPos = startPos;
            this.size = size;
            this.onClickWithoutArgument = onClickWithoutArgument;
        }

        public boolean positionIsInsideButton(Vector percentPos){
            return startPos.x <= percentPos.x && startPos.x + size.x >= percentPos.x &&
                    startPos.y <= percentPos.y && startPos.y + size.y >= percentPos.y;
        }

        public void doAction(){
            if(onClickWithoutArgument != null){
                onClickWithoutArgument.onClick();
            }
        }
    }
}
