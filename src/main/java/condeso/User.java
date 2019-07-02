package condeso;

import horario.Turnos;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import tiendas.Tiendas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User {
    public long Id;
    public String username;
    public String password;
    public Condeso condeso;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Condeso getCondeso() {
        return condeso;
    }

    public void setCondeso(Condeso condeso) {
        this.condeso = condeso;
    }

    public long getId() {
        return condeso.getId();
    }

    public TipoEmpleado getTipo() { return condeso.getTipo(); }

    public String getNombre() {
        return condeso.getNombre();
    }

    public int getLevel() {
        return condeso.getLevel();
    }

    public LocalDate getAntiguedad() {
        return condeso.getAntiguedad();
    }

    public String getPhonenumber() {
        return condeso.getPhonenumber();
    }

    public String getMail() {
        return condeso.getMail();
    }

    public List<Tiendas> getDondePuedeTrabajar() {
        return condeso.getDondePuedeTrabajar();
    }

    public String getAbreviacion() {
        return condeso.getAbreviacion();
    }

    public boolean isLunch() {
        return condeso.isLunch();
    }

    public boolean isMasculino() {
        return condeso.isMasculino();
    }

    public boolean isFemenino() {
        return condeso.isFemenino();
    }

    public Contrato getContrato() {
        return condeso.getContrato();
    }

    public String getColor() {
        return condeso.getColor();
    }

    public BooleanProperty Manana() {
        return new SimpleBooleanProperty(condeso.isManana());
    }

    public ObservableValue<Boolean> Tarde() {
        return  new SimpleBooleanProperty(condeso.isTarde());
    }

    public ObservableValue<Boolean> Nivel() {
        return  new SimpleBooleanProperty(condeso.isCaja());
    }

    public ObservableValue<Boolean> Lunch() {
        return  new SimpleBooleanProperty(condeso.isLunch());
    }
}
