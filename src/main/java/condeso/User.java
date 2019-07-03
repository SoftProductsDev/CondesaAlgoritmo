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
    public long id;
    public String username;
    public String password;
    public Condeso condeso;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

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

    public long getCondesoId() {
        if(condeso != null){return condeso.getId();}
        return 0;
    }

    public TipoEmpleado getTipo() {
        if(condeso != null){return condeso.getTipo();}
        return null;
    }

    public String getNombre() {
        if(condeso != null){return condeso.getNombre();}
        return null;
    }

    public int getLevel() {
        if(condeso != null){return condeso.getLevel();}
        return 0;
    }

    public LocalDate getAntiguedad() {
        if(condeso != null){ return condeso.getAntiguedad();}
        return null;
    }

    public String getPhonenumber() {
        if(condeso != null){ return condeso.getPhonenumber();}
        return null;
    }

    public String getMail() {
        if(condeso != null){return condeso.getMail();}
        return null;
    }

    public List<Tiendas> getDondePuedeTrabajar() {
        if(condeso != null){return condeso.getDondePuedeTrabajar();}
        return null;
    }

    public String getAbreviacion() {
        if(condeso != null){return condeso.getAbreviacion();}
        return null;
    }

    public boolean isLunch() {
        if(condeso != null){return condeso.isLunch();}
        return false;
    }

    public boolean isMasculino() {
        if(condeso != null){return condeso.isMasculino();}
        return false;
    }

    public boolean isFemenino() {
        if(condeso != null){ return condeso.isFemenino();}
        return false;
    }

    public Contrato getContrato() {
        if(condeso != null){ return condeso.getContrato();}
        return null;
    }

    public String getColor() {
        if(condeso != null){ return condeso.getColor();}
        return null;
    }

    public BooleanProperty Manana() {
        if(condeso != null){return new SimpleBooleanProperty(condeso.isManana());}
        return null;
    }

    public ObservableValue<Boolean> Tarde() {
        if(condeso != null){return  new SimpleBooleanProperty(condeso.isTarde());}
        return null;
    }

    public ObservableValue<Boolean> Nivel() {
        if(condeso != null){return  new SimpleBooleanProperty(condeso.isCaja());}
        return null;
    }

    public ObservableValue<Boolean> Lunch() {
        if(condeso != null){ return  new SimpleBooleanProperty(condeso.isLunch());}
        return null;
    }
}
