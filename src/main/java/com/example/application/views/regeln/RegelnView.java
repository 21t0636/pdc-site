package com.example.application.views.regeln;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Image;

@PageTitle("Regeln")
@Route(value = "Regeln", layout = MainLayout.class)
public class RegelnView extends Div {

    public RegelnView() {
        addClassName("regeln-view");

        add(createTitle());
        add(createRuleText());
        add(createMainRules());
        add(createDartBoard());
        add(createDartPositioning());
    }

    private Component createTitle() {
        return new H1("Dart-Regeln");
    }

    private Component createRuleText() {
        Paragraph ruleText = new Paragraph("Eine kleine Zusammenfassung zu den wichtigesten Regeln des Dartsports");
        ruleText.addClassName("ruleText");
        return ruleText;
    }

    private Component createMainRules() {
        Div mainRules = new Div();
        mainRules.addClassName("mainRules");

        UnorderedList rules = new UnorderedList();
        rules.add(new ListItem("Jeder Spieler hat drei Würfe"));
        rules.add(new ListItem("Im Standartmodus wird von 501 Punkten rückwärts gezählt"));
        rules.add(new ListItem("Die Spieler versuchen, möglichst schnell auf den Punktestand 0 zu kommen"));
        rules.add(new ListItem("Es muss genau auf 0 Punkte gespielt werden. Zu hohe Punktzahlen sind \"überworfen\""));
        rules.add(new ListItem("Es werden nur Pfeile gewertet, die auch im Brett stecken bleiben"));
        mainRules.add(new H2("Hauptregeln"), rules);

        return mainRules;
    }

    private Component createDartBoard() {
        Div dartboard = new Div();
        dartboard.addClassName("dartboard");

        Image dartboardImage = new Image();
        dartboardImage.setClassName("dartBoardImage");
        dartboardImage.setSrc("https://images.squarespace-cdn.com/content/v1/5be4b4ace2ccd17c42eff299/1582316191661-W0FW6FGYK50IH1OKZXK4/Dart+Board.png?format=1000w");
        dartboardImage.setAlt("dartboardImage");

        UnorderedList points = new UnorderedList();
        points.add(new ListItem("Single (Einfache Punktzahl)"));
        points.add(new ListItem("Double (Doppelte Punktzahl)"));
        points.add(new ListItem("Triple (Dreifache Punktzahl)"));
        points.add(new ListItem("Speziell ist das Bull in der Mitte, welches Single (25 Punkte) und Double (50 Punkte) gibt"));

        dartboard.add(new H2("Das Dartboard"), new H3("Folgende Punktzahlen können geworfen werden:"), points, dartboardImage);

        return dartboard;
    }

    private Component createDartPositioning() {
        Div dartboard = new Div();
        dartboard.addClassName("dartboard");

        Image dartboardImage = new Image();
        dartboardImage.setClassName("dartBoardImage");
        dartboardImage.setSrc("https://dimensionsguide.s3.amazonaws.com/11-SPORTS/DARTS/THROWING-DISTANCE/Dimensions-Guide-Sports-Darts-Throwing-Distance.jpg");
        dartboardImage.setAlt("dartboardImage");

        dartboard.add(new H2("Positionierung am Board"), dartboardImage);

        return dartboard;
    }
}
