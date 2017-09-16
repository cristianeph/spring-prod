package com.pl.model;

public class MaquinaActividadModel {
    private Iterable<MaquinaModel> maquinas;
    private Iterable<ActividadModel> actividades;

    public MaquinaActividadModel() {
    }

    public Iterable<MaquinaModel> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(Iterable<MaquinaModel> maquinas) {
        this.maquinas = maquinas;
    }

    public Iterable<ActividadModel> getActividades() {
        return actividades;
    }

    public void setActividades(Iterable<ActividadModel> actividades) {
        this.actividades = actividades;
    }
}