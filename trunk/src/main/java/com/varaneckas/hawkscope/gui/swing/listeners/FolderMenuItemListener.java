package com.varaneckas.hawkscope.gui.swing.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.varaneckas.hawkscope.menu.DynamicFileFilter;
import com.varaneckas.hawkscope.menu.ExecutableMenuItem;
import com.varaneckas.hawkscope.menu.FolderMenu;
import com.varaneckas.hawkscope.menu.MenuFactory;
import com.varaneckas.hawkscope.util.IconFactory;
import com.varaneckas.hawkscope.util.MenuUtils;

/**
 * Folder Menu Item Listener
 * 
 * Loads menu contents on mouse over
 *
 * @author Tomas Varaneckas
 * @version $Id$
 */
public class FolderMenuItemListener implements MenuItemListener {
    
    /**
     * Lazy loading flag
     */
    private boolean loaded = false;
    
    /**
     * Target menu
     */
    private final FolderMenu folderMenu;
    
    /**
     * Target folder
     */
    private final File file;
    
    /**
     * Number of Menu Items in one Menu
     */
    private static final int MENU_SIZE = MenuUtils.getAutoMenuSize();
    
    /**
     * Constructor
     * 
     * @param menu tagert
     * @param file target
     */
    public FolderMenuItemListener(final FolderMenu menu, final File file) {
        this.folderMenu = menu;
        this.file = file;
    }

    @Override
    public synchronized void itemSelected() {
        if (!loaded && file != null && file.isDirectory()) {
            final File[] files = file.listFiles(DynamicFileFilter.getInstance());
            long counter = 0L;
            FolderMenu workMenu = folderMenu;
            if (files == null || files.length == 0) {                
                final ExecutableMenuItem empty = MenuFactory.getMenuFactory().newExecutableMenuItem();
                empty.setIcon(IconFactory.getIconFactory().getIcon("empty"));
                empty.setText("Empty...");
                empty.setEnabled(false);
                workMenu.addMenuItem(empty);
            } else {
                Arrays.sort(files);
                for (final File ff : files) {
                    if (ff.isDirectory()) {
                        workMenu.addMenuItem(MenuFactory.getMenuFactory().newFolderMenu(ff));
                    } else {
                        workMenu.addMenuItem(MenuFactory.getMenuFactory().newFileMenuItem(ff));
                    }
                    if (++counter % MENU_SIZE == 0) {
                        FolderMenu more = MenuFactory.getMenuFactory().newFolderMenu(null);
                        more.setIcon(IconFactory.getIconFactory().getIcon("more"));
                        more.setText("More");
                        workMenu.addSeparator();
                        workMenu.addMenuItem(more);
                        workMenu = more;
                    }
                }
            }
            loaded = true;
        }
    }
    
    

    @Override
    public void itemClicked() {
         try {
            Desktop.getDesktop().open(file);
            MenuFactory.getMenuFactory().getMainMenu().forceHide();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}