package com.varaneckas.hawkscope.gui.swt;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.cloudgarden.resource.SWTResourceManager;
import com.varaneckas.hawkscope.Version;
import com.varaneckas.hawkscope.gui.AboutWindow;
import com.varaneckas.hawkscope.util.IOUtils;
import com.varaneckas.hawkscope.util.IconFactory;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SWTAboutShell extends org.eclipse.swt.widgets.Dialog implements AboutWindow {

    private Shell dialogShell;
    private Canvas logoCanvas;
    private Label appNameLabel;
    private Label appSloganLabel;
    private Label appVersion;
    private Label appHomepageValue;
    private Button copyReportButton;
    private Button closeButton;
    private Label environmentLabel;
    private Text environmentTextArea;
    private Label appHomepageLabel;
    private Label appReleasedValue;
    private Label appReleasedLabel;
    private Label appVersionValue;

    /**
    * Auto-generated main method to display this 
    * org.eclipse.swt.widgets.Dialog inside a new Shell.
    */
    public static void main(String[] args) {
        try {
            Display display = Display.getDefault();
            Shell shell = new Shell(display);
            SWTAboutShell inst = new SWTAboutShell(shell, SWT.NULL);
            inst.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SWTAboutShell(Shell parent, int style) {
        super(parent, style);
    }

    public void open() {
        try {
            Shell parent = getParent();
            dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

            {
                //Register as a resource user - SWTResourceManager will
                //handle the obtaining and disposing of resources
                SWTResourceManager.registerResourceUser(dialogShell);
            }
            
            
            dialogShell.setLayout(new FormLayout());
            dialogShell.layout();
            dialogShell.pack();            
            dialogShell.setSize(516, 322);
            {
                copyReportButton = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
                FormData copyReportButtonLData = new FormData();
                copyReportButtonLData.width = 125;
                copyReportButtonLData.height = 29;
                copyReportButtonLData.left =  new FormAttachment(0, 1000, 314);
                copyReportButtonLData.top =  new FormAttachment(0, 1000, 252);
                copyReportButton.setLayoutData(copyReportButtonLData);
                copyReportButton.addMouseListener(new MouseAdapter() {
                   @Override
                   public void mouseUp(MouseEvent event) {
                       IOUtils.copyToClipboard(Version.getEnvironmentReport());
                   } 
                });
                copyReportButton.setText("Copy to Clipboard");
            }
            {
                closeButton = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
                FormData closeButtonLData = new FormData();
                closeButtonLData.width = 47;
                closeButtonLData.height = 29;
                closeButtonLData.left =  new FormAttachment(0, 1000, 451);
                closeButtonLData.top =  new FormAttachment(0, 1000, 252);
                closeButton.setLayoutData(closeButtonLData);
                closeButton.addMouseListener(new MouseAdapter() {
                   @Override
                    public void mouseUp(MouseEvent event) {
                       dialogShell.setVisible(false);
                    } 
                });
                closeButton.setText("Close");
            }
            {
                environmentLabel = new Label(dialogShell, SWT.NONE);
                FormData environmentLabelLData = new FormData();
                environmentLabelLData.width = 486;
                environmentLabelLData.height = 17;
                environmentLabelLData.left =  new FormAttachment(0, 1000, 12);
                environmentLabelLData.top =  new FormAttachment(0, 1000, 127);
                environmentLabel.setLayoutData(environmentLabelLData);
                environmentLabel.setText("Environment");
                environmentLabel.setFont(SWTResourceManager.getFont("Sans", 10, 1, false, false));
            }
            {
                environmentTextArea = new Text(dialogShell, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
                FormData environmentTextAreaLData = new FormData();
                environmentTextAreaLData.width = 468;
                environmentTextAreaLData.height = 90;
                environmentTextAreaLData.left =  new FormAttachment(0, 1000, 12);
                environmentTextAreaLData.top =  new FormAttachment(0, 1000, 150);
                environmentTextArea.setLayoutData(environmentTextAreaLData);
                environmentTextArea.setText(Version.getSystemProperties());
                environmentTextArea.setEditable(false);
            }
            {
                appHomepageValue = new Label(dialogShell, SWT.NONE);
                FormData appHomepageValueLData = new FormData();
                appHomepageValueLData.width = 242;
                appHomepageValueLData.height = 17;
                appHomepageValueLData.left =  new FormAttachment(0, 1000, 256);
                appHomepageValueLData.top =  new FormAttachment(0, 1000, 104);
                appHomepageValue.setLayoutData(appHomepageValueLData);
                appHomepageValue.setText(Version.HOMEPAGE);
            }
            {
                appHomepageLabel = new Label(dialogShell, SWT.NONE);
                FormData appHomepageLabelLData = new FormData();
                appHomepageLabelLData.width = 94;
                appHomepageLabelLData.height = 17;
                appHomepageLabelLData.left =  new FormAttachment(0, 1000, 156);
                appHomepageLabelLData.top =  new FormAttachment(0, 1000, 104);
                appHomepageLabel.setLayoutData(appHomepageLabelLData);
                appHomepageLabel.setText("Homepage:");
                appHomepageLabel.setFont(SWTResourceManager.getFont("Sans", 10, 1, false, false));
            }
            {
                appReleasedValue = new Label(dialogShell, SWT.NONE);
                FormData appReleasedValueLData = new FormData();
                appReleasedValueLData.width = 242;
                appReleasedValueLData.height = 17;
                appReleasedValueLData.left =  new FormAttachment(0, 1000, 256);
                appReleasedValueLData.top =  new FormAttachment(0, 1000, 81);
                appReleasedValue.setLayoutData(appReleasedValueLData);
                appReleasedValue.setText(Version.VERSION_DATE);
            }
            {
                appReleasedLabel = new Label(dialogShell, SWT.NONE);
                FormData appReleasedLabelLData = new FormData();
                appReleasedLabelLData.width = 77;
                appReleasedLabelLData.height = 17;
                appReleasedLabelLData.left =  new FormAttachment(0, 1000, 156);
                appReleasedLabelLData.top =  new FormAttachment(0, 1000, 81);
                appReleasedLabel.setLayoutData(appReleasedLabelLData);
                appReleasedLabel.setText("Released:");
                appReleasedLabel.setFont(SWTResourceManager.getFont("Sans", 10, 1, false, false));
            }
            {
                appVersionValue = new Label(dialogShell, SWT.NONE);
                FormData appVersionValueLData = new FormData();
                appVersionValueLData.width = 242;
                appVersionValueLData.height = 17;
                appVersionValueLData.left =  new FormAttachment(0, 1000, 256);
                appVersionValueLData.top =  new FormAttachment(0, 1000, 58);
                appVersionValue.setLayoutData(appVersionValueLData);
                appVersionValue.setText(Version.VERSION_NUMBER);
            }
            {
                appVersion = new Label(dialogShell, SWT.NONE);
                FormData appVersionLData = new FormData();
                appVersionLData.width = 77;
                appVersionLData.height = 17;
                appVersionLData.left =  new FormAttachment(0, 1000, 156);
                appVersionLData.top =  new FormAttachment(0, 1000, 58);
                appVersion.setLayoutData(appVersionLData);
                appVersion.setText("Version:");
                appVersion.setFont(SWTResourceManager.getFont("Sans", 10, 1, false, false));
            }
            {
                appSloganLabel = new Label(dialogShell, SWT.WRAP);
                FormData appSloganLabelLData = new FormData();
                appSloganLabelLData.width = 342;
                appSloganLabelLData.height = 17;
                appSloganLabelLData.left =  new FormAttachment(0, 1000, 156);
                appSloganLabelLData.top =  new FormAttachment(0, 1000, 35);
                appSloganLabel.setLayoutData(appSloganLabelLData);
                appSloganLabel.setText("Access anything with single click!");
            }
            {
                appNameLabel = new Label(dialogShell, SWT.NONE);
                FormData appNameLabelLData = new FormData();
                appNameLabelLData.width = 342;
                appNameLabelLData.height = 17;
                appNameLabelLData.left =  new FormAttachment(0, 1000, 156);
                appNameLabelLData.top =  new FormAttachment(0, 1000, 12);
                appNameLabel.setLayoutData(appNameLabelLData);
                appNameLabel.setText("Hawkscope");
                appNameLabel.setFont(SWTResourceManager.getFont("Sans", 10, 1, false, false));
            }
            {
                final FormData logoCanvasLData = new FormData();
                logoCanvasLData.width = 114;
                logoCanvasLData.height = 109;
                logoCanvasLData.left =  new FormAttachment(0, 1000, 12);
                logoCanvasLData.top =  new FormAttachment(0, 1000, 12);
                logoCanvas = new Canvas(dialogShell, SWT.RESIZE);
                logoCanvas.addPaintListener(new PaintListener() {
                    @Override
                    public void paintControl(PaintEvent e) {
                        e.gc.drawImage((Image) IconFactory.getIconFactory()
                                .getUncachedIcon("hawkscope128.png"), 0, 0, 128, 
                                128, 0, 0, 114, 109);
                    }
                });
                logoCanvas.setLayoutData(logoCanvasLData);
            }
            dialogShell.setLocation(getParent().toDisplay(100, 100));
            dialogShell.open();
            Display display = dialogShell.getDisplay();
            while (!dialogShell.isDisposed()) {
                if (!display.readAndDispatch())
                    display.sleep();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideObject() {
        dialogShell.setVisible(false);
    }

    @Override
    public void showObject() {
        open();
        //dialogShell.setVisible(true);
    }
    
}