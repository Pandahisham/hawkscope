package com.varaneckas.hawkscope.plugin;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;

import com.varaneckas.hawkscope.cfg.Configuration;
import com.varaneckas.hawkscope.gui.listeners.FolderMenuItemListener;
import com.varaneckas.hawkscope.gui.settings.AbstractSettingsTabItem;
import com.varaneckas.hawkscope.menu.FolderMenu;
import com.varaneckas.hawkscope.menu.MainMenu;

public abstract class PluginAdapter implements Plugin {
    
    protected Log log = LogFactory.getLog(getClass());
    
    protected boolean canEnhanceFileMenuItem = false;
    
    protected boolean canEnhanceFolderMenu = false;
    
    protected boolean canEnhanceQuickAccessItem = false;
    
    protected boolean canHookBeforeAboutMenuItem = false;
    
    protected boolean canHookBeforeQuickAccessList = false;
    
    protected boolean canInterceptClick = false;
    
    public boolean canEnhanceFileMenuItem() {
        return canEnhanceFileMenuItem;
    }
    
    public boolean canEnhanceFolderMenu() {
        return canEnhanceFolderMenu;
    }
    
    public boolean canEnhanceQuickAccessItem() {
        return canEnhanceQuickAccessItem;
    }
    
    public boolean canHookBeforeAboutMenuItem() {
        return canHookBeforeAboutMenuItem;
    }
    
    public boolean canHookBeforeQuickAccessList() {
        return canHookBeforeQuickAccessList;
    }
    
    public boolean canInterceptClick() {
        return canInterceptClick;
    }

    public void beforeAboutMenuItem(MainMenu mainMenu) {
        log.debug("hooking before about menu item");
    }

    public void beforeQuickAccess(MainMenu mainMenu) {
        log.debug("hooking before quick access");
    }

    public void enhanceFileMenuItem(MenuItem menuItem, File file) {
        log.debug("hooking before file menu item");
    }

    public void enhanceFolderMenu(File file, MenuItem menu, Menu submenu,
            FolderMenuItemListener listener) {
        log.debug("hooking before folder menu item");
    }

    public void enhanceQuickAccessItem(FolderMenu fm, File custom) {
        log.debug("hhooking into quick access item");
    }
    
    public boolean interceptClick(File file) {
        log.debug("Intercepting click");
        //continue with other actions
        return true;
    }
    
    public void enhanceSettings(final TabFolder folder, final List<AbstractSettingsTabItem> tabItems) {
        log.debug("Skipping enhance settings on plugin");
    }

    public String getId() {
        return getClass().getSimpleName();
    }
    
}