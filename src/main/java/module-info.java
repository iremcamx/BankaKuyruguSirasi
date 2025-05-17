module com.example.bankakuyrugusirasi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.bankakuyrugusirasi to javafx.fxml;
    exports com.example.bankakuyrugusirasi;
}