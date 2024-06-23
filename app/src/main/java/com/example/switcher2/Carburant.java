package com.example.switcher2;
public class Carburant {
    private int id;
    private String station;
    private String tel;
    private double prixgasoil;
    private double prixessence;
    private boolean AvecAdditif;

    public Carburant(int id, String station, String tel, double prixgasoil, double prixessence, boolean avecAdditif) {
        this.id = id;
        this.station = station;
        this.tel = tel;
        this.prixgasoil = prixgasoil;
        this.prixessence = prixessence;
        AvecAdditif = avecAdditif;
    }

    // Getters
    public int getId() {
        return id;
    }
    public boolean isAvecAdditif() {
        return AvecAdditif;
    }
    public double getPrixgasoil() {
        return prixgasoil;
    }

    public double getPrixessence() {
        return prixessence;
    }
    public String getStation() {
        return station;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public String toString() {
        return "Station{" +
                "station='" + station + '\'' +
                ", tel='" + tel + '\'' +
                ", avecAdditif=" + AvecAdditif +
                ", prixGasoil=" + prixgasoil +
                ", prixEssence=" + prixessence +
                '}';
    }
}