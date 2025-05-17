package com.example.bankakuyrugusirasi;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class HelloApplication extends Application {

    // Kuyruklar için ObservableList'ler
    private ObservableList<String> bireyselKuyrukListesi = FXCollections.observableArrayList();
    private ObservableList<String> kurumsalKuyrukListesi = FXCollections.observableArrayList();
    private ObservableList<String> oncelikliKuyrukListesi = FXCollections.observableArrayList();

    // Kuyruklar
    private Queue<String> bireyselKuyruk = new LinkedList<>();
    private Queue<String> kurumsalKuyruk = new LinkedList<>();
    private Queue<String> oncelikliKuyruk = new LinkedList<>();

    // UI Bileşenleri
    private ListView<String> bireyselListView;
    private ListView<String> kurumsalListView;
    private ListView<String> oncelikliListView;
    private RadioButton bireyselRadio, kurumsalRadio, oncelikliRadio;
    private Button ekleButton, cagirButton;
    private Label cagrilanMusteriLabel;
    private ToggleGroup musteriTuruGroup, kuyrukSecimiGroup;

    private int musteriNumarasi = 1;

    @Override
    public void start(Stage primaryStage) {
        // Ana düzen
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Kuyruk Gösterimleri
        VBox kuyruklarVBox = createKuyruklarVBox();
        root.setCenter(kuyruklarVBox);

        // Müşteri Ekleme Bölümü
        VBox musteriEkleVBox = createMusteriEkleVBox();
        root.setLeft(musteriEkleVBox);

        // Müşteri Çağırma Bölümü
        VBox musteriCagirVBox = createMusteriCagirVBox();
        root.setRight(musteriCagirVBox);

        // Sahne
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Banka Sıra Uygulaması");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Kuyruk Gösterimleri için VBox
    private VBox createKuyruklarVBox() {
        VBox kuyruklarVBox = new VBox(10);
        kuyruklarVBox.setPadding(new Insets(10));

        Label bireyselLabel = new Label("Bireysel Kuyruk:");
        bireyselListView = new ListView<>(bireyselKuyrukListesi);
        bireyselListView.setPrefHeight(100);

        Label kurumsalLabel = new Label("Kurumsal Kuyruk:");
        kurumsalListView = new ListView<>(kurumsalKuyrukListesi);
        kurumsalListView.setPrefHeight(100);

        Label oncelikliLabel = new Label("Öncelikli Kuyruk:");
        oncelikliListView = new ListView<>(oncelikliKuyrukListesi);
        oncelikliListView.setPrefHeight(100);

        kuyruklarVBox.getChildren().addAll(bireyselLabel, bireyselListView, kurumsalLabel, kurumsalListView, oncelikliLabel, oncelikliListView);
        return kuyruklarVBox;
    }

    // Müşteri Ekleme Bölümü için VBox
    private VBox createMusteriEkleVBox() {
        VBox musteriEkleVBox = new VBox(10);
        musteriEkleVBox.setPadding(new Insets(10));
        musteriEkleVBox.setPrefWidth(150);

        Label baslikLabel = new Label("Yeni Müşteri Ekle");
        baslikLabel.setStyle("-fx-font-weight: bold;");

        bireyselRadio = new RadioButton("Bireysel");
        kurumsalRadio = new RadioButton("Kurumsal");
        oncelikliRadio = new RadioButton("Öncelikli");

        musteriTuruGroup = new ToggleGroup();
        bireyselRadio.setToggleGroup(musteriTuruGroup);
        kurumsalRadio.setToggleGroup(musteriTuruGroup);
        oncelikliRadio.setToggleGroup(musteriTuruGroup);
        bireyselRadio.setSelected(true); // Varsayılan olarak bireysel seçili

        ekleButton = new Button("Ekle");
        ekleButton.setOnAction(e -> musteriEkle());

        musteriEkleVBox.getChildren().addAll(baslikLabel, bireyselRadio, kurumsalRadio, oncelikliRadio, ekleButton);
        return musteriEkleVBox;
    }

    // Müşteri Çağırma Bölümü için VBox
    private VBox createMusteriCagirVBox() {
        VBox musteriCagirVBox = new VBox(10);
        musteriCagirVBox.setPadding(new Insets(10));
        musteriCagirVBox.setPrefWidth(150);

        Label baslikLabel = new Label("Müşteri Çağır");
        baslikLabel.setStyle("-fx-font-weight: bold;");

        bireyselRadio = new RadioButton("Bireysel");
        kurumsalRadio = new RadioButton("Kurumsal");
        oncelikliRadio = new RadioButton("Öncelikli");

        kuyrukSecimiGroup = new ToggleGroup();
        bireyselRadio.setToggleGroup(kuyrukSecimiGroup);
        kurumsalRadio.setToggleGroup(kuyrukSecimiGroup);
        oncelikliRadio.setToggleGroup(kuyrukSecimiGroup);
        bireyselRadio.setSelected(true); // Varsayılan olarak bireysel seçili

        cagirButton = new Button("Çağır");
        cagirButton.setOnAction(e -> musteriCagir());

        cagrilanMusteriLabel = new Label("");
        Label cagrilanLabel = new Label("Çağrılan Müşteri:");
        cagrilanLabel.setStyle("-fx-font-style: italic;");

        musteriCagirVBox.getChildren().addAll(baslikLabel, bireyselRadio, kurumsalRadio, oncelikliRadio, cagirButton, cagrilanLabel, cagrilanMusteriLabel);
        return musteriCagirVBox;
    }

    // Müşteri Ekleme Metodu
    private void musteriEkle() {
        String musteriTuru = ((RadioButton) musteriTuruGroup.getSelectedToggle()).getText();
        String musteri = musteriNumarasi++ + " - " + musteriTuru + " Müşteri";

        if (musteriTuru.equals("Bireysel")) {
            bireyselKuyruk.offer(musteri);
            bireyselKuyrukListesi.add(musteri);
        } else if (musteriTuru.equals("Kurumsal")) {
            kurumsalKuyruk.offer(musteri);
            kurumsalKuyrukListesi.add(musteri);
        } else {
            oncelikliKuyruk.offer(musteri);
            oncelikliKuyrukListesi.add(musteri);
        }
    }

    // Müşteri Çağırma Metodu
    private void musteriCagir() {
        String cagrilanMusteri = null;
        String kuyrukTuru = ((RadioButton) kuyrukSecimiGroup.getSelectedToggle()).getText();

        if (kuyrukTuru.equals("Bireysel")) {
            cagrilanMusteri = bireyselKuyruk.poll();
            if (cagrilanMusteri != null) {
                bireyselKuyrukListesi.remove(0);
            }
        } else if (kuyrukTuru.equals("Kurumsal")) {
            cagrilanMusteri = kurumsalKuyruk.poll();
            if (cagrilanMusteri != null) {
                kurumsalKuyrukListesi.remove(0);
            }
        } else {
            cagrilanMusteri = oncelikliKuyruk.poll();
            if (cagrilanMusteri != null) {
                oncelikliKuyrukListesi.remove(0);
            }
        }
        if (cagrilanMusteri != null) {
            cagrilanMusteriLabel.setText(cagrilanMusteri);
        }
        else{
            cagrilanMusteriLabel.setText("Kuyruk Boş");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

