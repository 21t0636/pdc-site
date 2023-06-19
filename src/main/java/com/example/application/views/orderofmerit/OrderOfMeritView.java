package com.example.application.views.orderofmerit;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H3;
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
import java.util.Comparator;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

@PageTitle("Order Of Merit")
@Route(value = "Order-of-Merit", layout = MainLayout.class)
public class OrderOfMeritView extends Main {

    private OrderedList imageContainer;
    private Select<String> sortBy = new Select<>();
    private Player[] players;

    public OrderOfMeritView() {
        constructUI();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("data/Players.json");

        try {
            players = objectMapper.readValue(file, Player[].class);
            fillImageContainer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillImageContainer() {
        for (Player player : players) {
            imageContainer.add(createOrderOfMeritViewCard(player));
        }
    }

    private void constructUI() {
        addClassNames("order-of-merit-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H3 header = new H3("Order Of Merit");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph(
                "Die Order of Merit ist eine Rangliste der Spieler in der Professional Darts Corporation (PDC), die basierend auf den Preisgeldern, die sie bei PDC-Turnieren gewonnen haben, erstellt wird. Sie dient als Maßstab für die Leistung und den Erfolg der Spieler innerhalb der PDC.");
        description.addClassName("OOMDescription");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        setupSortBy();
        container.add(headerContainer, sortBy);

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer, sortBy);
        add(container, imageContainer);
    }

    private OrderOfMeritViewCard createOrderOfMeritViewCard(Player player) {
        return new OrderOfMeritViewCard(player.getId(), player.getName(), player.getEarnings(),
                player.getNickname(), player.getDarts(), player.getNationality(), player.getImage());
    }

    private void setupSortBy() {
        sortBy.setLabel("Sortieren nach");
        sortBy.setItems("Ranking", "Name", "Nationalität");
        sortBy.setValue("Ranking");
        sortBy.addValueChangeListener(e -> sortPlayers());
    }

    private void sortPlayers() {
        switch (sortBy.getValue()) {
            case "Ranking":
                Arrays.sort(players, Comparator.comparingInt(Player::getId));
                imageContainer.removeAll();
                fillImageContainer();
                break;
            case "Name":
                Arrays.sort(players, Comparator.comparing(Player::getName));
                imageContainer.removeAll();
                fillImageContainer();
                break;
            case "Nationalität":
                Arrays.sort(players, Comparator.comparing(Player::getNationality));
                imageContainer.removeAll();
                fillImageContainer();
                break;
            default:
                return;
        }
    }

}
