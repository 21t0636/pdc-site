package com.example.application.views.teilnehmen;

import java.time.LocalDate;
import java.time.ZoneId;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.value.ValueChangeMode;

@PageTitle("Teilnehmen")
@Route(value = "Teilnehmen", layout = MainLayout.class)
@Uses(Icon.class)
public class TeilnehmenView extends Div {

    private Button cancel = new Button("Zurücksetzen");
    private Button save = new Button("Speichern");

    private Binder<SamplePerson> binder = new Binder<>(SamplePerson.class);

    private static SamplePerson person = new SamplePerson();
    private ComboBox<String> tournaments = new ComboBox<>("Turnier");
    private TextField firstName = new TextField("Vorname");
    private TextField lastName = new TextField("Nachname");
    private DatePicker dateOfBirth = new DatePicker("Geburtsdatum");
    private EmailField email = new EmailField("Email");
    private RadioButtonGroup<String> playerHand = new RadioButtonGroup<>();
    private Checkbox age = new Checkbox();
    private Checkbox rules = new Checkbox();
    private Checkbox allowPhotos = new Checkbox();

    public TeilnehmenView(SamplePersonService personService) {
        addClassName("teilnehmen-view");

        validateFields();

        tournaments.setItems("Turnier Zürich Zentrum (20.08.2023)", "Turnier Luzern (22.08.2023)", "Bodensee Open (25.08.2023)");
        tournaments.setAllowCustomValue(false);
        tournaments.addClassName("tournaments");

        playerHand.setLabel("Wurfhand");
        playerHand.setItems("Links", "Rechts");

        age.setLabel("Ich bestätige, dass ich über 18 Jahre alt bin");
        rules.setLabel("Ich akzeptiere die Regeln und Vorschriften des angewählten Turniers");
        allowPhotos.setLabel("Ich erkläre mich bereit, Fotos oder Videos meiner Person während des Turniers zu veröffentlichen (Freiwillig)");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        //clearForm();

        binder.readBean(person);
        binder.setBean(person);

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            //personService.update(binder.getBean());

            if (checkClickedItems()) {
                firstName.setLabel("Yes");
                Notification.show("Danke für deine Teilnahme! Du wirst eine Email mit Informationen zum Turnier erhalten.");
                clearForm();
            } else {
                firstName.setLabel("No");
                Notification.show("Bitte fülle alle Daten richtig aus.");
            } 
        }); 
    }
 
    private boolean checkClickedItems() {
        boolean checkEmpty = (!tournaments.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !dateOfBirth.isEmpty() && !email.isEmpty() && !age.isEmpty() && !rules.isEmpty());
        boolean checkValid = !email.isInvalid() && !dateOfBirth.isInvalid();
        
        return checkEmpty && checkValid;
    }

    private void validateFields() {
        firstName.setValueChangeMode(ValueChangeMode.EAGER); 
        lastName.setValueChangeMode(ValueChangeMode.EAGER); 

        firstName.addValueChangeListener(e -> {
            String value = e.getValue();
            if (value != null && !value.matches("[a-zA-Z]*")) {
                firstName.setValue(value.replaceAll("[^a-zA-Z]", ""));
            }
        });

        lastName.addValueChangeListener(e -> {
            String value = e.getValue();
            if (value != null && !value.matches("[a-zA-Z]*")) {
                lastName.setValue(value.replaceAll("[^a-zA-Z]", ""));
            }
        });

        dateOfBirth.setInvalid(true);

        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        dateOfBirth.setMax(now.minusYears(18));

        email.setInvalid(true);
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Schreibe dich für ein Turnier in deiner Nähe ein!");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.addClassName("form-layout");
        email.setErrorMessage("Please enter a valid email address");
        VerticalLayout agreements = new VerticalLayout(age, rules, allowPhotos);

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
