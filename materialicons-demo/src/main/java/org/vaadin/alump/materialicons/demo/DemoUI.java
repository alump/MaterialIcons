package org.vaadin.alump.materialicons.demo;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.alump.materialicons.MaterialIcons;


@Theme("demo")
@Title("Material Icons Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private final static int WINDOW_WIDTH_PX = 500;
    private final static int WINDOW_HEIGHT_PX = 350;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        Label header = new Label(MaterialIcons.SENTIMENT_SATISFIED.getHtml() + " Material Icons Vaadin add-on");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setContentMode(ContentMode.HTML);

        Label description = new Label("This add-on brings Material Design Icons by Google to your Vaadin application");

        Panel panel = new Panel(createButtons());
        panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        panel.setSizeFull();

        HorizontalLayout links = new HorizontalLayout();
        links.setWidth(100, Unit.PERCENTAGE);

        Link link = new Link("Browse Icons (material.io/icons)", new ExternalResource("https://material.io/icons/"));
        link.setIcon(MaterialIcons.LINK);
        Link link2 = new Link("Vaadin add-on project GitHub page (source code, issues, ...)",
                new ExternalResource("https://github.com/alump/materialicons/"));
        link2.setIcon(MaterialIcons.CODE);
        links.addComponents(link, link2);

        layout.addComponents(header, description, panel, links);
        layout.setExpandRatio(panel, 1f);
    }

    private Component createButtons() {
        CssLayout layout = new CssLayout();
        layout.addStyleName("icons-layout");
        MaterialIcons[] options = MaterialIcons.values();
        for(int i = 0; i < options.length; ++i) {
            MaterialIcons icon = options[i];
            Button button = new Button(icon);
            button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
            button.addStyleName("icon-button");
            button.setData(icon);
            button.addClickListener(this::onIconButtonClicked);
            layout.addComponent(button);
        }
        return layout;
    }

    private void onIconButtonClicked(Button.ClickEvent event) {
        MaterialIcons icon = (MaterialIcons)event.getButton().getData();
        Window window = new Window("Icon details");
        window.addStyleName("icon-details");
        window.setWidth(WINDOW_WIDTH_PX, Unit.PIXELS);
        window.setHeight(WINDOW_HEIGHT_PX, Unit.PIXELS);
        VerticalLayout layout = new VerticalLayout();
        window.setContent(layout);
        Label look = new Label(icon.getHtml(), ContentMode.HTML);
        look.setCaption("Visual:");
        look.addStyleName("look-label");
        Label name = new Label("MaterialIcons." + icon.name());
        name.setWidth(100, Unit.PERCENTAGE);
        name.setCaption("Java:");
        name.setContentMode(ContentMode.PREFORMATTED);
        Label css = new Label(
                "font-family: \"MaterialIcons\";\ncontent: \"\\"
                        + Integer.toString(icon.getCodepoint(), 16) + "\";");
        css.setWidth(100, Unit.PERCENTAGE);
        css.setContentMode(ContentMode.PREFORMATTED);
        css.setCaption("CSS:");
        layout.addComponents(look, name, css);
        getUI().addWindow(window);

        int x = event.getClientX() - (WINDOW_WIDTH_PX / 2);
        if(x < 0) {
            x = 0;
        } else if(x + WINDOW_WIDTH_PX > Page.getCurrent().getBrowserWindowWidth()) {
            x = Page.getCurrent().getBrowserWindowWidth() - WINDOW_WIDTH_PX;
        }

        window.setPositionX(x);

        int y = event.getClientY() - (WINDOW_HEIGHT_PX / 2);
        if(y < 0) {
            y = 0;
        } else if(y + WINDOW_HEIGHT_PX > Page.getCurrent().getBrowserWindowHeight()) {
            y = Page.getCurrent().getBrowserWindowHeight() - WINDOW_HEIGHT_PX;
        }

        window.setPositionY(y);
    }
}
