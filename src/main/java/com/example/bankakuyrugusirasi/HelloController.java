package com.example.bankakuyrugusirasi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.LinkedList;
import java.util.Queue;

public class HelloController {
    @FXML
    private ListView<String> bireyselKuyrukListesi;
    @FXML
    private ListView<String> kurumsalKuyrukListesi;
    @FXML
    private ListView<String> oncelikliKuyrukListesi;
    @FXML
    private RadioButton bireyselRadioEkle;
    @FXML
    private RadioButton kurumsalRadioEkle;
    @FXML
    private RadioButton oncelikliRadioEkle;
    @FXML
    private ToggleGroup musteriTuru;
    @FXML
    private Button ekleButton;
    @FXML
    private RadioButton bireyselRadioCagir;
    @FXML
    private RadioButton kurumsalRadioCagir;
    @FXML
    private RadioButton oncelikliRadioCagir;
    @FXML
    private ToggleGroup kuyrukSecimi;
    @FXML
    private Button cagirButton;
    @FXML
    private Label cagrilanMusteriLabel;

    // Uygulama verileri (kuyruklar)
    private Queue<String> bireyselKuyruk = new LinkedList<>();
    private Queue<String> kurumsalKuyruk = new LinkedList<>();
    private Queue<String> oncelikliKuyruk = new LinkedList<>();
    private ObservableList<String> bireyselKuyrukData = FXCollections.observableArrayList();
    private ObservableList<String> kurumsalKuyrukData = FXCollections.observableArrayList();
    private ObservableList<String> oncelikliKuyrukData = FXCollections.observableArrayList();

    private int musteriNumarasi = 1;

    @FXML
    public void initialize() {
        bireyselKuyrukListesi.setItems(bireyselKuyrukData);
        kurumsalKuyrukListesi.setItems(kurumsalKuyrukData);
        oncelikliKuyrukListesi.setItems(oncelikliKuyrukData);
    }

    @FXML
    protected void yeniMusteriEkle() {
        String musteriTuruSecim = ((RadioButton) musteriTuru.getSelectedToggle()).getText();
        String musteri = musteriNumarasi++ + " - " + musteriTuruSecim + " Müşteri";

        if (musteriTuruSecim.equals("Bireysel")) {
            bireyselKuyruk.offer(musteri);
            bireyselKuyrukData.add(musteri);
        } else if (musteriTuruSecim.equals("Kurumsal")) {
            kurumsalKuyruk.offer(musteri);
            kurumsalKuyrukData.add(musteri);
        } else {
            oncelikliKuyruk.offer(musteri);
            oncelikliKuyrukData.add(musteri);
        }
    }

    @FXML
    protected void musteriCagir() {
        String cagrilanMusteri = null;
        String kuyrukSecimiTuru = ((RadioButton) kuyrukSecimi.getSelectedToggle()).getText();

        if (kuyrukSecimiTuru.equals("Bireysel")) {
            cagrilanMusteri = bireyselKuyruk.poll();
            if (cagrilanMusteri != null) {
                bireyselKuyrukData.remove(0);
            }
        } else if (kuyrukSecimiTuru.equals("Kurumsal")) {
            cagrilanMusteri = kurumsalKuyruk.poll();
            if (cagrilanMusteri != null) {
                kurumsalKuyrukData.remove(0);
            }
        } else {
            cagrilanMusteri = oncelikliKuyruk.poll();
            if (cagrilanMusteri != null) {
                oncelikliKuyrukData.remove(0);
            }
        }

        if (cagrilanMusteri != null) {
            cagrilanMusteriLabel.setText(cagrilanMusteri);
        } else {
            cagrilanMusteriLabel.setText("Kuyruk Boş");
        }
    }
}