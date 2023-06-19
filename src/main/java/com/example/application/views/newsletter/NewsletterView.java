package com.example.application.views.newsletter;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

@PageTitle("Newsletter")
@Route(value = "Newsletter", layout = MainLayout.class)
public class NewsletterView extends HorizontalLayout {
     
    private Button save = new Button("Abschicken");
    private Binder<SamplePerson> binder = new Binder<>(SamplePerson.class);

    private static SamplePerson person = new SamplePerson();
    private EmailField email = new EmailField("Email");

    public NewsletterView(SamplePersonService personService) {
        addClassName("teilnehmen-view");

        add(createTitle());
        add(createNewsletterText());
        add(createFormLayout());
        add(createButtonLayout());
        add(infoText());

        binder.bindInstanceFields(this);
        binder.readBean(person);
        binder.setBean(person);
        //clearForm();

        save.addClickListener(e -> {
            personService.update(binder.getBean());

            if (email.isEmpty()) {
                Notification.show("Bitte gebe eine Email an!");
                return;
            }

            if (email.isInvalid()) {
                Notification.show("Die angegebene Email ist nicht korrekt!");
                return;
            }
                   
            Notification.show("Vielen Dank! Du wirst monatlich eine Newsletter-Email erhalten!");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Schreibe dich für unseren Newsletter ein!");
    }

    private Component createNewsletterText() {
        return new H4("Bekomme jeden Monat eine Email, um auf dem aktuellen Stand zu bleiben, was in der Dart-Welt passiert!*");
    }

    private Component infoText() {
        Paragraph infoText = new Paragraph("*Du kannst dich jederzeit von unserem Newsletter abmelden!");
        infoText.addClassName("infoText");
        return infoText;
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.addClassName("form-layout");
        email.setErrorMessage("Please enter a valid email address");
        email.addClassName("Email");
        formLayout.add(email);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClassName("senden-button");
        buttonLayout.add(save);
        return buttonLayout;
    }
}
