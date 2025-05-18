package com.example.bankakuyrugusirasi;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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
    private TextField adTextField, soyadTextField; // Ad-soyad alanları

    private int musteriNumarasi = 1;

    @Override
    public void start(Stage primaryStage) {
        // Ana düzen
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #e0f2f7; -fx-font-family: 'Inter';");

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
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Banka Sıra Uygulaması");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Kuyruk Gösterimleri için VBox
    private VBox createKuyruklarVBox() {
        VBox kuyruklarVBox = new VBox(20);
        kuyruklarVBox.setPadding(new Insets(20));
        kuyruklarVBox.setStyle("-fx-alignment: center;");

        Label bireyselLabel = new Label("Bireysel Kuyruk:");
        bireyselLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.2em; -fx-text-fill: #1976d2;");
        bireyselListView = new ListView<>(bireyselKuyrukListesi);
        bireyselListView.setPrefHeight(120);
        bireyselListView.setStyle("-fx-border-color: #bbdefb; -fx-border-radius: 5;");

        Label kurumsalLabel = new Label("Kurumsal Kuyruk:");
        kurumsalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.2em; -fx-text-fill: #1976d2;");
        kurumsalListView = new ListView<>(kurumsalKuyrukListesi);
        kurumsalListView.setPrefHeight(120);
        kurumsalListView.setStyle("-fx-border-color: #bbdefb; -fx-border-radius: 5;");

        Label oncelikliLabel = new Label("Öncelikli Kuyruk:");
        oncelikliLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.2em; -fx-text-fill: #1976d2;");
        oncelikliListView = new ListView<>(oncelikliKuyrukListesi);
        oncelikliListView.setPrefHeight(120);
        oncelikliListView.setStyle("-fx-border-color: #bbdefb; -fx-border-radius: 5;");

        kuyruklarVBox.getChildren().addAll(bireyselLabel, bireyselListView, kurumsalLabel, kurumsalListView, oncelikliLabel, oncelikliListView);
        return kuyruklarVBox;
    }

    // Müşteri Ekleme Bölümü için VBox
    private VBox createMusteriEkleVBox() {
        VBox musteriEkleVBox = new VBox(15);
        musteriEkleVBox.setPadding(new Insets(20));
        musteriEkleVBox.setPrefWidth(200);
        musteriEkleVBox.setStyle("-fx-background-color: #f5f5f5; -fx-border-radius: 10; -fx-padding: 20;");

        Label baslikLabel = new Label("Yeni Müşteri Ekle");
        baslikLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.4em; -fx-text-fill: #212121; -fx-margin-bottom: 10;");

        Label adLabel = new Label("Ad:");
        adLabel.setStyle("-fx-text-fill: #424242;");
        adTextField = new TextField();

        Label soyadLabel = new Label("Soyad:");
        soyadLabel.setStyle("-fx-text-fill: #424242;");
        soyadTextField = new TextField();

        bireyselRadio = new RadioButton("Bireysel");
        bireyselRadio.setStyle("-fx-text-fill: #424242;");
        kurumsalRadio = new RadioButton("Kurumsal");
        kurumsalRadio.setStyle("-fx-text-fill: #424242;");
        oncelikliRadio = new RadioButton("Öncelikli");
        oncelikliRadio.setStyle("-fx-text-fill: #424242;");

        musteriTuruGroup = new ToggleGroup();
        bireyselRadio.setToggleGroup(musteriTuruGroup);
        kurumsalRadio.setToggleGroup(musteriTuruGroup);
        oncelikliRadio.setToggleGroup(musteriTuruGroup);
        bireyselRadio.setSelected(true); // Varsayılan olarak bireysel seçili

        ekleButton = new Button("Ekle");
        ekleButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-cursor: hand;");
        ekleButton.setOnAction(e -> musteriEkle());

        musteriEkleVBox.getChildren().addAll(baslikLabel, adLabel, adTextField, soyadLabel, soyadTextField, bireyselRadio, kurumsalRadio, oncelikliRadio, ekleButton);
        return musteriEkleVBox;
    }

    // Müşteri Çağırma Bölümü için VBox
    private VBox createMusteriCagirVBox() {
        VBox musteriCagirVBox = new VBox(15);
        musteriCagirVBox.setPadding(new Insets(20));
        musteriCagirVBox.setPrefWidth(200);
        musteriCagirVBox.setStyle("-fx-background-color: #f5f5f5; -fx-border-radius: 10; -fx-padding: 20;");
        musteriCagirVBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        Label baslikLabel = new Label("Müşteri Çağır");
        baslikLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.4em; -fx-text-fill: #212121; -fx-margin-bottom: 15;");

        cagirButton = new Button("Çağır");
        cagirButton.setStyle("-fx-background-color: #26a69a; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 15 30; -fx-border-radius: 5; -fx-cursor: hand; -fx-font-size: 1.1em;");
        cagirButton.setOnAction(e -> musteriCagir());

        Label cagrilanLabel = new Label("Çağrılan Müşteri:");
        cagrilanLabel.setStyle("-fx-font-style: italic; -fx-text-fill: #757575; -fx-margin-top: 20;");

        cagrilanMusteriLabel = new Label("");
        cagrilanMusteriLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.6em; -fx-text-fill: #212121; -fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-radius: 5;");
        cagrilanMusteriLabel.setPrefWidth(180);
        cagrilanMusteriLabel.setAlignment(javafx.geometry.Pos.CENTER);

        // Radyo butonları kaldırıldı
        musteriCagirVBox.getChildren().addAll(baslikLabel, cagirButton, cagrilanLabel, cagrilanMusteriLabel);
        return musteriCagirVBox;
    }

    // Müşteri Ekleme Metodu (Ad-Soyad ile)
    private void musteriEkle() {
        String musteriTuru = ((RadioButton) musteriTuruGroup.getSelectedToggle()).getText();
        String ad = adTextField.getText();
        String soyad = soyadTextField.getText();
        String musteriBilgisi = musteriNumarasi++ + " - " + ad + " " + soyad + " (" + musteriTuru + ")";

        if (musteriTuru.equals("Bireysel")) {
            bireyselKuyruk.offer(musteriBilgisi);
            bireyselKuyrukListesi.add(musteriBilgisi);
        } else if (musteriTuru.equals("Kurumsal")) {
            kurumsalKuyruk.offer(musteriBilgisi);
            kurumsalKuyrukListesi.add(musteriBilgisi);
        } else {
            oncelikliKuyruk.offer(musteriBilgisi);
            oncelikliKuyrukListesi.add(musteriBilgisi);
        }
        // Eklendikten sonra metin alanlarını temizle
        adTextField.clear();
        soyadTextField.clear();
    }

    // Müşteri Çağırma Metodu (Öncelik Sırasına Göre)
    private void musteriCagir() {
        String cagrilanMusteri = null;

        // 1. Öncelikli Kuyruğu Kontrol Et
        if (!oncelikliKuyruk.isEmpty()) {
            cagrilanMusteri = oncelikliKuyruk.poll();
            oncelikliKuyrukListesi.remove(0);
        }
        // 2. Kurumsal Kuyruğu Kontrol Et (Öncelikli boşsa)
        else if (!kurumsalKuyruk.isEmpty()) {
            cagrilanMusteri = kurumsalKuyruk.poll();
            kurumsalKuyrukListesi.remove(0);
        }
        // 3. Bireysel Kuyruğu Kontrol Et (Öncelikli ve Kurumsal boşsa)
        else if (!bireyselKuyruk.isEmpty()) {
            cagrilanMusteri = bireyselKuyruk.poll();
            bireyselKuyrukListesi.remove(0);
        }

        if (cagrilanMusteri != null) {
            if(cagrilanMusteri.contains(" - ")) {
                String[] parts = cagrilanMusteri.split(" - ")[1].split(" ");
                if(parts.length > 1)
                    cagrilanMusteriLabel.setText(parts[0] + " " + parts[1]);
                else
                    cagrilanMusteriLabel.setText(parts[0]);
            }
            else{
                cagrilanMusteriLabel.setText(cagrilanMusteri);
            }
        } else {
            cagrilanMusteriLabel.setText("Kuyruk Boş");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
