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
package com.varaneckas.hawkscope.gui.settings;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.varaneckas.hawkscope.cfg.Configuration;
import com.varaneckas.hawkscope.gui.SharedStyle;
import com.varaneckas.hawkscope.util.OSUtils;
import com.varaneckas.hawkscope.util.OSUtils.OS;

/**
 * General Settings {@link TabItem}
 * 
 * @author Tomas Varaneckas
 * @version $Id$
 */
public class GeneralSettingsTabItem extends AbstractSettingsTabItem {
	
    /**
     * Label "Updates"
     */
	private Label updates;
	
	/**
	 * Checkbox "[] Check for updates"
	 */
	private Button checkForUpdates;
	
	/**
	 * Label "Menu"
	 */
	private Label menu;
	
	/**
	 * Label "Reload delay (sec)"
	 */
	private Label reloadDelaySec;
	
	/**
	 * Text input for reload delay
	 */
	private Text reloadDelayInput;
	
	/**
	 * Checkbox "[] Use OS icons"
	 */
	private Button useOsIcons;
	
	/**
	 * Checkbox "[] Mac menubar blues workaround"
	 */
	private Button menubarBlues;
	
	/**
	 * Creates the General settings {@link TabItem}
	 * 
	 * @param folder Settings {@link TabFolder}
	 */
	public GeneralSettingsTabItem(final TabFolder folder) {
		super(folder, "&General");
		createUpdatesSection();
		createMenuSection();
		if (OSUtils.CURRENT_OS.equals(OS.MAC)) {
			createMacSection();
		}
	}
	
	/**
	 * Creates the "Updates" section
	 */
	private void createUpdatesSection() {
		//Updates
	    updates = addSectionLabel("Updates");
		updates.setLayoutData(SharedStyle.relativeTo(null, null));	
		
		//Check for updates
		checkForUpdates = 
			addCheckbox("Check for &updates on startup");
		checkForUpdates.setToolTipText("Should Hawkscope check for " +
				"updates on startup? Please mind the HTTP Proxy settings " +
				"in Network tab!");
		checkForUpdates.setLayoutData(ident(SharedStyle
				.relativeTo(updates, null)));
		checkForUpdates.setSelection(cfg.checkForUpdates());
		checkForUpdates.setToolTipText("Should Hawkscope check for updates " +
			"on startup? Please mind the HTTP Proxy settings in Network tab!");
	}
	
	/**
	 * Creates the Menu section
	 */
	private void createMenuSection() {
	    menu = addSectionLabel("Menu");
		menu.setLayoutData(SharedStyle.relativeTo(checkForUpdates, null));
		
		reloadDelaySec = addLabel("Reload delay (seconds):");
		reloadDelaySec.setLayoutData(ident(SharedStyle.relativeTo(menu, null)));
		
		createReloadDelayInput();

		useOsIcons = addCheckbox("Use operating system &icons");
		useOsIcons.setLayoutData(ident(SharedStyle
				.relativeTo(reloadDelayInput, null)));
		useOsIcons.setSelection(cfg.useOsIcons());
		useOsIcons.setToolTipText("Let Hawkscope look for Operating " +
        		"System icons?");
	}

	/**
	 * Creates the Menu Reload Delay input
	 */
	private void createReloadDelayInput() {
		final String reloadDelay = String.valueOf((cfg.getMenuReloadDelay() 
		        / 1000.0));
		reloadDelayInput = addText(reloadDelay, 4);
		final FormData style = ident(SharedStyle.relativeTo(menu, reloadDelaySec));
		style.width = 40;
		//up a little, to center with reloadDelaySec
		style.top.offset += SharedStyle.TEXT_TOP_OFFSET_ADJUST;
		reloadDelayInput.setLayoutData(style);
		reloadDelayInput.setToolTipText("After how many seconds " +
                "of being idle Hawkscope should reload it's main menu " +
                "(to find new devices, refresh Quick Access List, " +
                "re-apply Blacklist etc.)? The reload is fast unless you " +
                "have got many slow network drives.");
		reloadDelayInput.addListener(SWT.FocusOut, new Listener() {
            public void handleEvent(final Event event) {
                try {
                    final double d = Double.valueOf(reloadDelayInput.getText());
                    if (d <= 0) {
                    	reloadDelayInput.setText("0.1");
                    }
                    if (d > 9999) {
                    	reloadDelayInput.setText("9999");
                    }
                } catch (final Exception e) {
                	reloadDelayInput.setText(reloadDelay);
                }
            }
        });
	}
	
	/**
	 * Creates the Mac section
	 */
	private void createMacSection() {
		//Mac
		final Label macintosh = addSectionLabel("Mac");
		macintosh.setLayoutData(SharedStyle.relativeTo(useOsIcons, null));
		
		//[ ] Menubar icon blues
		menubarBlues = addCheckbox("Use &Mac Menubar icon " +
				"blues workaround");
		menubarBlues.setLayoutData(ident(SharedStyle.relativeTo(macintosh, null)));
		menubarBlues.setSelection(cfg.getProperties()
				.get(Configuration.MAC_MENUBAR_BLUES_WORKAROUND).equals("1"));
	}

    @Override
    protected void saveConfiguration() {
        cfg.getProperties().put(Configuration.CHECK_FOR_UPDATES, 
                checkForUpdates.getSelection() ? "1" : "0");
        cfg.getProperties().put(Configuration.MENU_RELOAD_DELAY, String.valueOf( 
                Math.round(Double.valueOf(reloadDelayInput.getText()) * 1000)));
        cfg.getProperties().put(Configuration.USE_OS_ICONS, 
                useOsIcons.getSelection() ? "1" : "0");
        if (OSUtils.CURRENT_OS.equals(OS.MAC)) {
        	cfg.getProperties().put(Configuration.MAC_MENUBAR_BLUES_WORKAROUND,  
        			menubarBlues.getSelection() ? "1" : "0");
        }
    }
}