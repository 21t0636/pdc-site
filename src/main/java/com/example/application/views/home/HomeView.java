package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Image;

@PageTitle("Home")
@Route(value = "Home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends Div {

    public HomeView() {
        addClassName("home-view");

        add(createTitle());
        add(createHometext());
        add(createPDCLogo());
        add(createSignature());
    }

    private Component createTitle() {
        return new H1("Willkommen auf der PDC Fan-Seite!");
    }

    private Component createHometext() {
        return new H2("Hier findest du alles rund um die Professional Darts Corporation, die besten Darts-Spieler der Welt und die neuesten Entwicklungen im Dartsport. Wir bieten dir aktuelle Ergebnisse, Spielpläne, Hintergrundinformationen und vieles mehr. Tauche ein in die faszinierende Welt des Darts und verpasse keine wichtigen Neuigkeiten mehr!");
    }

    private Image createPDCLogo() {
        Image pdcLogo = new Image("https://upload.wikimedia.org/wikipedia/de/thumb/2/29/Professional_Darts_Corporation_logo.svg/1200px-Professional_Darts_Corporation_logo.svg.png", "PDC-Logo");
        pdcLogo.addClassName("pdcLogo");
    
        return pdcLogo;
    }

    private Component createSignature() {
        return new H3("Raffael Barth");
    }
}
 