package com.example.application.views.orderofmerit;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@PageTitle("Order Of Merit")
@Route(value = "Order-of-Merit", layout = MainLayout.class)
public class OrderOfMeritView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public OrderOfMeritView() {
        constructUI();

        imageContainer.add(new OrderOfMeritViewCard(1, 
                "Michael Smith", 
                "£1,273,250", 
                "Bullyboy",
                "22g Unicorn",
                "England",
                "https://www.pdc.tv/sites/default/files/styles/player_desktop/public/players/profile/2023-01/2023WCF_MSmithTrophy7.jpg?h=cce03fa1&itok=f0IjNOwH"));

        imageContainer.add(new OrderOfMeritViewCard(2, 
                "Michael van Gerwen", 
                "£1,156,000", 
                "Mighty Mike",
                "21.5g Winmau",
                "Netherlands",
                "https://www.pdc.tv/sites/default/files/styles/player_desktop/public/players/profile/2021-09/WLDMYCHPLAY-QF-VANGERWEN4.jpg?itok=x2QRR5Hd"));

        imageContainer.add(new OrderOfMeritViewCard(3, 
                "Peter Wright", 
                "£1,153,250", 
                "Snakebite",
                "22g Red Dragon",
                "Schottland",
                "https://www.pdc.tv/sites/default/files/styles/player_desktop/public/players/profile/2021-09/WLDMTCHPLAY-FNL-WRIGHT32.jpg?h=8494ad1e&itok=mlTHppZl"));
     
        imageContainer.add(new OrderOfMeritViewCard(4, 
                "Gerwyn Price", 
                "£773,250", 
                "The Iceman",
                "22g Red Dragon",
                "Wales",
                "https://www.pdc.tv/sites/default/files/styles/player_desktop/public/players/profile/2021-09/WLDCHAMPS-FINAL-PRICE-26.jpg?h=923f9667&itok=8KkF21N4"));

        imageContainer.add(new OrderOfMeritViewCard(5, 
                "Rob Cross", 
                "£543,000", 
                "Voltage",
                "21g Target",
                "England",
                "https://www.pdc.tv/sites/default/files/styles/player_desktop/public/players/profile/2021-09/WLDMTCHPLAY-RD1-CROSS2.jpg?h=1a99f9b0&itok=hXw6j5r5"));

        imageContainer.add(new OrderOfMeritViewCard(6, 
                "Luke Humphries", 
                "£539,500", 
                "Cool Hand Luke",
                "22g Red Dragon",
                "England",
                "https://www.pdc.tv/sites/default/files/styles/player_desktop/public/players/profile/2021-09/WLDMTCHPLAY-RD1-HUMPHRIES9.jpg?h=6591a0e0&itok=2cMB53oW"));

    }

    private void constructUI() {
        addClassNames("order-of-merit-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Order Of Merit");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Aktuelle Order Of Merit");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer, sortBy);
        add(container, imageContainer);

    }
}
