package com.example.application.views.teilnehmen;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroup;

@PageTitle("Teilnehmen")
@Route(value = "Teilnehmen", layout = MainLayout.class)
@Uses(Icon.class)
public class TeilnehmenView extends Div {

    private ComboBox<String> tournaments = new ComboBox<>("Turnier");
    private TextField firstName = new TextField("Vorname");
    private TextField lastName = new TextField("Nachname");
    private EmailField email = new EmailField("Email");
    private DatePicker dateOfBirth = new DatePicker("Geburtsdatum");
    private RadioButtonGroup<String> playerHand = new RadioButtonGroup<>();
    private CheckboxGroup<String> agreements = new CheckboxGroup<>();

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<SamplePerson> binder = new Binder<>(SamplePerson.class);

    public TeilnehmenView(SamplePersonService personService) {
        addClassName("teilnehmen-view");

        tournaments.setItems("a", "b", "c");

        playerHand.setLabel("Wurfhand");
        playerHand.setItems("Links", "Rechts");

        agreements.setItems("Ich bestätige, dass ich über 18 Jahre als bin",
                            "Ich akzeptiere die Regeln und Vorschriften des angewählten Turniers",
                            "Ich erkläre mich bereit, Fotos oder Videos meiner Person während des Turniers zu veröffentlichen");

        add(createTitle());
        add(tournaments);
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Schreibe dich für ein Turnier in deiner Nähe ein!");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(tournaments, firstName, lastName, dateOfBirth, email, playerHand, agreements);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}
