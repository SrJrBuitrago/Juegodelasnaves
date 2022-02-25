package com.example.juegodelasnaves;
//Clase jugador contiene los atributos de la clase, sus constructores y su toString
public class Jugador {
    int idJugador;
    String nombreJugador;
    int puntuacionJugador;

    public Jugador() {

    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getPuntuacionJugador() {
        return puntuacionJugador;
    }

    public void setPuntuacionJugador(int puntuacionJugador) {
        this.puntuacionJugador = puntuacionJugador;
    }

    public Jugador(String nombreJugador, int puntuacionJugador) {
        this.nombreJugador = nombreJugador;
        this.puntuacionJugador = puntuacionJugador;
    }

    public Jugador(int idJugador, String nombreJugador, int puntuacionJugador) {
        this.idJugador = idJugador;
        this.nombreJugador = nombreJugador;
        this.puntuacionJugador = puntuacionJugador;
    }

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador=" + idJugador +
                ", nombreJugador='" + nombreJugador + '\'' +
                ", puntuacionJugador=" + puntuacionJugador +
                '}';
    }
}
