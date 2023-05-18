package com.example.application.views.newsletter;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Newsletter")
@Route(value = "Newsletter", layout = MainLayout.class)
public class NewsletterView extends HorizontalLayout {

    private EmailField email = new EmailField("Email");

    public NewsletterView() {
        setMargin(true);
        setVerticalComponentAlignment(Alignment.END);

        add(createFormLayout());
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(createTitle(), createNewsletterText(), email);
        return formLayout;
    }

    private Component createTitle() {
        return new H3("Schreibe dich für unseren Newsletter ein!");
    }

    private Component createNewsletterText() {
        return new H4("Bekomme jeden Monat ein Email, um auf dem aktuellen Stand zu bleiben, was in der Dart-Welt passiert!*");
    }
}
