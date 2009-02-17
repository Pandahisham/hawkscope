/*
 * Copyright (c) 2008-2009 Tomas Varaneckas
 * http://www.varaneckas.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.varaneckas.hawkscope.plugin;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;

import com.varaneckas.hawkscope.cfg.Configuration;
import com.varaneckas.hawkscope.cfg.ConfigurationFactory;
import com.varaneckas.hawkscope.gui.listeners.FolderMenuItemListener;
import com.varaneckas.hawkscope.gui.settings.AbstractSettingsTabItem;
import com.varaneckas.hawkscope.gui.settings.SettingsWindow;
import com.varaneckas.hawkscope.menu.FileMenuItem;
import com.varaneckas.hawkscope.menu.FolderMenu;
import com.varaneckas.hawkscope.menu.MainMenu;
import com.varaneckas.hawkscope.plugin.openwith.OpenWithPlugin;

/**
 * Plugin Manager
 * 
 * Works as a broker between Hawkscope and it's plugins
 * 
 * @author Tomas Varaneckas
 * @version $Id$
 */
public class PluginManager {
    
    /**
     * Singleton instance
     */
    private static final PluginManager instance = new PluginManager();
    
    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(PluginManager.class);
    
    /**
     * Private singleton constructor
     */
    private PluginManager() {
        //FIXME playing around
    	final Configuration cfg = ConfigurationFactory.getConfigurationFactory()
    		.getConfiguration();
        plugins.add(OpenWithPlugin.getInstance());
        for (final Plugin p : plugins) {
        	try {
        		String enabled = cfg.getProperties().get("plugin." 
	        			+ p.getClass().getName() + ".enabled");
        		if (enabled != null) {
        			p.setEnabled(enabled.equals("1") ? true : false); 
        		}
        	} catch (final Exception e) {
        		log.warn("Failed checking if plugin enabled: " + p.getName());
        	}
        }
    }
    
    /**
     * List of available {@link Plugin}s
     */
    private final List<Plugin> plugins = new LinkedList<Plugin>();
    
    /**
     * Gets the singleton instance of {@link PluginManager}
     * 
     * @return singleton instance
     */
    public static PluginManager getInstance() {
        return instance;
    }
    
    /**
     * Gets all {@link Plugin}s
     * 
     * @return
     */
    public List<Plugin> getAllPlugins() {
    	return plugins;
    }
    
    /**
     * Gets the list of active {@link Plugin}s
     * 
     * @return active plugins
     */
    public List<Plugin> getActivePlugins() {
    	final List<Plugin> active = new LinkedList<Plugin>();
    	for (final Plugin p : plugins) {
    		if (p.isEnabled()) {
    			active.add(p);
    		}
    	}
        return active;
    }
    
    /**
     * Enhances {@link FolderMenu} with Plugins
     * 
     * @param file
     * @param menu
     * @param submenu
     * @param listener
     */
    public void enhanceFolderMenu(final File file, final MenuItem menu, 
            final Menu submenu, final FolderMenuItemListener listener) {
        for (final Plugin plugin : getActivePlugins()) {
            if (plugin.canEnhanceFolderMenu())
                plugin.enhanceFolderMenu(file, menu, submenu, listener);
        }
    }

    /**
     * Enhances {@link FileMenuItem} with Plugins
     * 
     * @param menuItem
     * @param file
     */
    public void enhanceFileMenuItem(final MenuItem menuItem, final File file) {
        for (final Plugin plugin : getActivePlugins()) {
            if (plugin.canEnhanceFileMenuItem())
                plugin.enhanceFileMenuItem(menuItem, file);
        }
    }

    /**
     * Hooks in before Quick Access menu
     * 
     * @param mainMenu
     */
    public void beforeQuickAccess(final MainMenu mainMenu) {
        for (final Plugin plugin : getActivePlugins()) {
            if (plugin.canHookBeforeQuickAccessList())
                plugin.beforeQuickAccess(mainMenu);
        }
    }

    /**
     * Enhances Quick Access item with plugins
     * 
     * @param fm
     * @param custom
     */
    public void enhanceQuickAccessItem(final FolderMenu fm, final File custom) {
        for (final Plugin plugin : getActivePlugins()) {
            if (plugin.canEnhanceQuickAccessItem())
                plugin.enhanceQuickAccessItem(fm, custom);
        }
    }

    /**
     * Hooks before About menu item
     * 
     * @param mainMenu
     */
    public void beforeAboutMenuItem(final MainMenu mainMenu) {
        for (final Plugin plugin : getActivePlugins()) {
            if (plugin.canHookBeforeAboutMenuItem())
                plugin.beforeAboutMenuItem(mainMenu);
        }
    }
    
    /**
     * Intercepts file click with Plugins
     * 
     * @param file target file
     * @return
     */
    public boolean interceptClick(final File file) {
        boolean proceed = true;
        for (final Plugin plugin : getActivePlugins()) {
            if (plugin.canInterceptClick()) {
                proceed = plugin.interceptClick(file);
                if (!proceed) break;
            }
        }
        return proceed;
    }

    /**
     * Enhances {@link SettingsWindow}'s {@link TabFolder}
     * 
     * @param settingsTabFolder
     * @param tabList
     */
    public void enhanceSettings(final TabFolder settingsTabFolder, 
            final List<AbstractSettingsTabItem> tabList) {
        for (final Plugin plugin : getActivePlugins()) {
            try {
                plugin.enhanceSettings(settingsTabFolder, tabList);
            } catch (final Exception e) {
                log.warn("Failed enhancing settings tab for plugin: " 
                        + plugin.getName(), e);
            }
        }
    }

}