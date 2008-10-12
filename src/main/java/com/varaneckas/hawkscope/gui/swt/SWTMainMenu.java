package com.varaneckas.hawkscope.gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.varaneckas.hawkscope.menu.MainMenu;
import com.varaneckas.hawkscope.menu.state.MenuClosedState;
import com.varaneckas.hawkscope.menu.state.State;
import com.varaneckas.hawkscope.tray.TrayManagerFactory;

public class SWTMainMenu extends MainMenu {

    private static SWTMainMenu instance = null;
    
    private final Menu menu;
    
    private State state = MenuClosedState.getInstance();

    private SWTMainMenu() {
        menu = new Menu(((SWTTrayManager) TrayManagerFactory
                .getTrayManager()).getShell(), SWT.POP_UP);
        menu.addMenuListener(new MenuAdapter() {
            @Override
            public void menuHidden(MenuEvent e) {
                setState(MenuClosedState.getInstance());
            }
        });
    }

    public static SWTMainMenu getInstance() {
        if (instance == null) {
            instance = new SWTMainMenu();
        }
        return instance;
    }
    
    @Override
    public void clearMenu() {
        for (MenuItem item : menu.getItems()) {
            item.dispose();
        }
    }

    @Override
    public void forceHide() {
        setState(MenuClosedState.getInstance());
        menu.setVisible(false);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
        state.init();
    }

    @Override
    public void showMenu(int x, int y) {
        menu.setLocation(x, y);
        menu.setVisible(true);
    }

    @Override
    public void addMenuItem(com.varaneckas.hawkscope.menu.MenuItem item) {
        if (item instanceof SWTMenuItem) {
            ((SWTMenuItem) item).createMenuItem(menu);
        }
    }

    @Override
    public void addSeparator() {
        new MenuItem(menu, SWT.SEPARATOR);
    }

}
