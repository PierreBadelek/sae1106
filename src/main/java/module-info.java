module com.example.saemangemoi {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens sae.view to javafx.fxml;
    exports sae.view;
    exports sae;
    opens sae to javafx.fxml;
}