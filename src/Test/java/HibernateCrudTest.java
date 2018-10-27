
import DbController.HibernateCrud;
import horario.Dias;
import horario.HorarioMaster;
import horario.Turnos;
import tiendas.Tiendas;
import condeso.Condeso;
import condeso.Contrato;
import condeso.TipoEmpleado;

import java.util.*;

import junit.framework.TestCase;

import javax.print.CancelablePrintJob;
import java.time.LocalDate;

public class HibernateCrudTest extends TestCase {

  public void testGet() {
    List<Condeso> condesos = DbController.HibernateCrud.GetAllCondesos();
    for (Condeso condeso: condesos
    ) {
      System.out.println(condeso.toString());
    }
  }

  public void testGeneral(){
    List<Tiendas> tiendas = testTiendas();
    testCreateCondeso(tiendas);
    testPlantillas();

  }

  public  void testPlantillas(){

  }

  public List<Tiendas> testTiendas(){
      List<Tiendas> tiendas = new ArrayList<>();
      Tiendas hbf = new Tiendas();
      hbf.setNombre("HBF");
      hbf.setManager("Jorge");
      hbf.setFechaApertura(LocalDate.now());
      hbf.setId(2);
      HibernateCrud.SaveTienda(hbf);


      Tiendas mf = new Tiendas();
      mf.setNombre("MF");
      mf.setManager("Leo");
      mf.setFechaApertura(LocalDate.now());
      mf.setId(1);
      HibernateCrud.SaveTienda(mf);


      Tiendas impler = new Tiendas();
      impler.setNombre("IMP");
      impler.setManager("Jorge");
      impler.setFechaApertura(LocalDate.now());
      impler.setId(3);
      HibernateCrud.SaveTienda(impler);


    tiendas.add(hbf);
    tiendas.add(mf);
    tiendas.add(impler);


      return tiendas;
  }

  public void testCreateCondeso(List<Tiendas> tiendas){
/*
    TipoEmpleado encargado = TipoEmpleado.Encargado;
    TipoEmpleado GM = TipoEmpleado.GM;
    TipoEmpleado nuevo = TipoEmpleado.Nuevo;
    TipoEmpleado equipo = TipoEmpleado.Equipo;

    Contrato otros = Contrato.otros;
    Contrato minijob = Contrato.MiniJob;

    Tiendas mf = tiendas.get(1);
    List<Tiendas> freiheit = new ArrayList<>();
    freiheit.add(mf);
    Tiendas hbf = tiendas.get(2);
    List<Tiendas> haupt = new ArrayList<>();
    haupt.add(hbf);
    Tiendas imp = tiendas.get(3);
    List<Tiendas> impler = new ArrayList<>();
    impler.add(imp);

    Condeso condeso = new Condeso();
    condeso.setId(2);
    condeso.setNombre("Leonardo Panusia");
    condeso.setAbreviacion("LP");
    condeso.setMasculino(true);
    condeso.setFemenino(false);
    condeso.setContrato(otros);
    condeso.setLunch(true);
    condeso.setTarde(true);
    condeso.setManana(true);
    condeso.setColor("#f48642");
    condeso.setCaja(true);
    condeso.setLevel(3);
    condeso.setTipo(GM);
    condeso.setFijos(false);
    condeso.setAntiguedad(LocalDate.now());
    condeso.setDondePuedeTrabajar(freiheit);
    HibernateCrud.SaveCondeso(condeso);

    Condeso condeso2 = new Condeso();
    condeso2.setId(4);
    condeso2.setNombre("Jorge Figueroa");
    condeso2.setAbreviacion("JF");
    condeso2.setMasculino(true);
    condeso2.setFemenino(false);
    condeso2.setContrato(otros);
    condeso2.setLunch(true);
    condeso2.setTarde(true);
    condeso2.setManana(false);
    condeso2.setCaja(true);
    condeso2.setLevel(3);
    condeso2.setTipo(equipo);
    condeso2.setFijos(true);
    condeso2.setAntiguedad(LocalDate.now());
    condeso2.setColor("#f48642");
    condeso2.setDondePuedeTrabajar(haupt);
    HibernateCrud.SaveCondeso(condeso2);

    Condeso condeso3 = new Condeso();
    condeso3.setId(6);
    condeso3.setNombre("Sergio Pindado");
    condeso3.setAbreviacion("SP");
    condeso3.setMasculino(true);
    condeso3.setFemenino(false);
    condeso3.setContrato(otros);
    condeso3.setLunch(true);
    condeso3.setTarde(false);
    condeso3.setManana(true);
    condeso3.setCaja(true);
    condeso3.setLevel(3);
    condeso3.setTipo(encargado);
    condeso3.setFijos(true);
    condeso3.setAntiguedad(LocalDate.now());
    condeso3.setColor("#f48642");
    condeso3.setDondePuedeTrabajar(freiheit);
    HibernateCrud.SaveCondeso(condeso3);

    Condeso condeso4 = new Condeso();
    condeso4.setId(18);
    condeso4.setNombre("Asuncion Santos");
    condeso4.setAbreviacion("ASA");
    condeso4.setMasculino(false);
    condeso4.setFemenino(true);
    condeso4.setContrato(otros);
    condeso4.setLunch(true);
    condeso4.setTarde(false);
    condeso4.setManana(true);
    condeso4.setColor("#f48642");
    condeso4.setCaja(true);
    condeso4.setLevel(3);
    condeso4.setTipo(equipo);
    condeso4.setFijos(true);
    condeso4.setAntiguedad(LocalDate.now());
    condeso4.setDondePuedeTrabajar(freiheit);
    HibernateCrud.SaveCondeso(condeso4);

    Condeso condeso5 = new Condeso();
    condeso5.setId(41);
    condeso5.setNombre("Eduardo Maurtua");
    condeso5.setAbreviacion("EM");
    condeso5.setMasculino(true);
    condeso5.setFemenino(false);
    condeso5.setContrato(minijob);
    condeso5.setLunch(true);
    condeso5.setTarde(true);
    condeso5.setManana(true);
    condeso5.setColor("#f48642");
    condeso5.setCaja(true);
    condeso5.setLevel(3);
    condeso5.setTipo(encargado);
    condeso5.setFijos(false);
    condeso5.setAntiguedad(LocalDate.now());
    condeso5.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso5);

    Condeso condeso6 = new Condeso();
    condeso6.setId(21);
    condeso6.setNombre("Jorge Ayllon");
    condeso6.setAbreviacion("JA");
    condeso6.setMasculino(true);
    condeso6.setFemenino(false);
    condeso6.setContrato(otros);
    condeso6.setLunch(true);
    condeso6.setTarde(true);
    condeso6.setManana(true);
    condeso6.setColor("#f48642");
    condeso6.setCaja(true);
    condeso6.setLevel(3);
    condeso6.setTipo(GM);
    condeso6.setFijos(true);
    condeso6.setAntiguedad(LocalDate.now());
    condeso6.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso6);

    Condeso condeso7 = new Condeso();
    condeso7.setId(46);
    condeso7.setNombre("Maria Perez");
    condeso7.setAbreviacion("MP");
    condeso7.setMasculino(false);
    condeso7.setFemenino(true);
    condeso7.setContrato(otros);
    condeso7.setLunch(true);
    condeso7.setTarde(true);
    condeso7.setManana(true);
    condeso7.setColor("#f48642");
    condeso7.setCaja(true);
    condeso7.setLevel(3);
    condeso7.setTipo(GM);
    condeso7.setFijos(true);
    condeso7.setAntiguedad(LocalDate.now());
    condeso7.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso7);

    Condeso condeso8 = new Condeso();
    condeso8.setId(57);
    condeso8.setNombre("Borja Royo");
    condeso8.setAbreviacion("BR");
    condeso8.setMasculino(true);
    condeso8.setFemenino(false);
    condeso8.setContrato(otros);
    condeso8.setLunch(true);
    condeso8.setTarde(true);
    condeso8.setManana(true);
    condeso8.setColor("#f48642");
    condeso8.setCaja(true);
    condeso8.setLevel(3);
    condeso8.setTipo(encargado);
    condeso8.setFijos(false);
    condeso8.setAntiguedad(LocalDate.now());
    condeso8.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso8);

    Condeso condeso9 = new Condeso();
    condeso9.setId(1);
    condeso9.setNombre("Pepe");
    condeso9.setAbreviacion("");
    condeso9.setMasculino();
    condeso9.setFemenino();
    condeso9.setContrato();
    condeso9.setLunch();
    condeso9.setTarde();
    condeso9.setManana();
    condeso9.setColor();
    condeso9.setCaja(true);
    condeso9.setLevel();
    condeso9.setTipo();
    condeso9.setFijos();
    condeso9.setAntiguedad(LocalDate.now());
    condeso9.setColor("#f48642");
    condeso9.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso9);

    Condeso condeso10 = new Condeso();
    condeso10.setId(1);
    condeso10.setNombre("Pepe");
    condeso10.setAbreviacion("");
    condeso10.setMasculino();
    condeso10.setFemenino();
    condeso10.setContrato();
    condeso10.setLunch();
    condeso10.setTarde();
    condeso10.setManana();
    condeso10.setColor();
    condeso10.setCaja(true);
    condeso10.setLevel();
    condeso10.setTipo();
    condeso10.setFijos();
    condeso10.setAntiguedad(LocalDate.now());
    condeso10.setColor("#f48642");
    condeso10.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso10);

    Condeso condeso11 = new Condeso();
    condeso11.setId(1);
    condeso11.setNombre("Pepe");
    condeso11.setAbreviacion("");
    condeso11.setMasculino();
    condeso11.setFemenino();
    condeso11.setContrato();
    condeso11.setLunch();
    condeso11.setTarde();
    condeso11.setManana();
    condeso11.setColor();
    condeso11.setCaja(true);
    condeso11.setLevel();
    condeso11.setTipo();
    condeso11.setFijos();
    condeso11.setAntiguedad(LocalDate.now());
    condeso11.setColor("#f48642");
    condeso11.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso11);

    Condeso condeso12 = new Condeso();
    condeso12 .setId(1);
    condeso12.setNombre("Pepe");
    condeso12.setAbreviacion("");
    condeso12.setMasculino();
    condeso12.setFemenino();
    condeso12.setContrato();
    condeso12.setLunch();
    condeso12.setTarde();
    condeso12.setManana();
    condeso12.setColor();
    condeso12.setCaja(true);
    condeso12.setLevel();
    condeso12.setTipo();
    condeso12.setFijos();
    condeso12.setAntiguedad(LocalDate.now());
    condeso12.setColor("#f48642");
    condeso12.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso12);

    Condeso condeso13 = new Condeso();
    condeso13.setId(1);
    condeso13.setNombre("Pepe");
    condeso13.setAbreviacion("");
    condeso13.setMasculino();
    condeso13.setFemenino();
    condeso13.setContrato();
    condeso13.setLunch();
    condeso13.setTarde();
    condeso13.setManana();
    condeso13.setColor();
    condeso13.setCaja(true);
    condeso13.setLevel();
    condeso13.setTipo();
    condeso13.setFijos();
    condeso13.setAntiguedad(LocalDate.now());
    condeso13.setColor("#f48642");
    condeso13.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso13);

    Condeso condeso14 = new Condeso();
    condeso14.setId(1);
    condeso14.setNombre("Pepe");
    condeso14.setAbreviacion("");
    condeso14.setMasculino();
    condeso14.setFemenino();
    condeso14.setContrato();
    condeso14.setLunch();
    condeso14.setTarde();
    condeso14.setManana();
    condeso14.setColor();
    condeso14.setCaja(true);
    condeso14.setLevel();
    condeso14.setTipo();
    condeso14.setFijos();
    condeso14.setAntiguedad(LocalDate.now());
    condeso14.setColor("#f48642");
    condeso14.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso14);

    Condeso condeso15 = new Condeso();
    condeso15.setId(1);
    condeso15.setNombre("Pepe");
    condeso15.setAbreviacion("");
    condeso15.setMasculino();
    condeso15.setFemenino();
    condeso15.setContrato();
    condeso15.setLunch();
    condeso15.setTarde();
    condeso15.setManana();
    condeso15.setColor();
    condeso15.setCaja(true);
    condeso15.setLevel();
    condeso15.setTipo();
    condeso15.setFijos();
    condeso15.setAntiguedad(LocalDate.now());
    condeso15.setColor("#f48642");
    condeso15.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso15);

    Condeso condeso16 = new Condeso();
    condeso16.setId(1);
    condeso16.setNombre("Pepe");
    condeso16.setAbreviacion("");
    condeso16.setMasculino();
    condeso16.setFemenino();
    condeso16.setContrato();
    condeso16.setLunch();
    condeso16.setTarde();
    condeso16.setManana();
    condeso16.setColor();
    condeso16.setCaja(true);
    condeso16.setLevel();
    condeso16.setTipo();
    condeso16.setFijos();
    condeso16.setAntiguedad(LocalDate.now());
    condeso16.setColor("#f48642");
    condeso16.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso16);

    Condeso condeso17 = new Condeso();
    condeso17.setId(1);
    condeso17.setNombre("Pepe");
    condeso17.setAbreviacion("");
    condeso17.setMasculino();
    condeso17.setFemenino();
    condeso17.setContrato();
    condeso17.setLunch();
    condeso17.setTarde();
    condeso17.setManana();
    condeso17.setColor();
    condeso17.setCaja(true);
    condeso17.setLevel();
    condeso17.setTipo();
    condeso17.setFijos();
    condeso17.setAntiguedad(LocalDate.now());
    condeso17.setColor("#f48642");
    condeso17.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso17);

    Condeso condeso18 = new Condeso();
    condeso18.setId(1);
    condeso18.setNombre("Pepe");
    condeso18.setAbreviacion("");
    condeso18.setMasculino();
    condeso18.setFemenino();
    condeso18.setContrato();
    condeso18.setLunch();
    condeso18.setTarde();
    condeso18.setManana();
    condeso18.setColor();
    condeso18.setCaja(true);
    condeso18.setLevel();
    condeso18.setTipo();
    condeso18.setFijos();
    condeso18.setAntiguedad(LocalDate.now());
    condeso18.setColor("#f48642");
    condeso18.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso18);

    Condeso condeso19 = new Condeso();
    condeso19.setId(1);
    condeso19.setNombre("Pepe");
    condeso19.setAbreviacion("");
    condeso19.setMasculino();
    condeso19.setFemenino();
    condeso19.setContrato();
    condeso19.setLunch();
    condeso19.setTarde();
    condeso19.setManana();
    condeso19.setColor();
    condeso19.setCaja(true);
    condeso19.setLevel();
    condeso19.setTipo();
    condeso19.setFijos();
    condeso19.setAntiguedad(LocalDate.now());
    condeso19.setColor("#f48642");
    condeso19.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso19);

    Condeso condeso20 = new Condeso();
    condeso20.setId(1);
    condeso20.setNombre("Pepe");
    condeso20.setAbreviacion("");
    condeso20.setMasculino();
    condeso20.setFemenino();
    condeso20.setContrato();
    condeso20.setLunch();
    condeso20.setTarde();
    condeso20.setManana();
    condeso20.setColor();
    condeso20.setCaja(true);
    condeso20.setLevel();
    condeso20.setTipo();
    condeso20.setFijos();
    condeso20.setAntiguedad(LocalDate.now());
    condeso20.setColor("#f48642");
    condeso20.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso20);

    Condeso condeso21 = new Condeso();
    condeso21.setId(1);
    condeso21.setNombre("Pepe");
    condeso21.setAbreviacion("");
    condeso21.setMasculino();
    condeso21.setFemenino();
    condeso21.setContrato();
    condeso21.setLunch();
    condeso21.setTarde();
    condeso21.setManana();
    condeso21.setColor();
    condeso21.setCaja(true);
    condeso21.setLevel();
    condeso21.setTipo();
    condeso21.setFijos();
    condeso21.setAntiguedad(LocalDate.now());
    condeso21.setColor("#f48642");
    condeso21.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso21);

    Condeso condeso22 = new Condeso();
    condeso22.setId(1);
    condeso22.setNombre("Pepe");
    condeso22.setAbreviacion("");
    condeso22.setMasculino();
    condeso22.setFemenino();
    condeso22.setContrato();
    condeso22.setLunch();
    condeso22.setTarde();
    condeso22.setManana();
    condeso22.setColor();
    condeso22.setCaja(true);
    condeso22.setLevel();
    condeso22.setTipo();
    condeso22.setFijos();
    condeso22.setAntiguedad(LocalDate.now());
    condeso22.setColor("#f48642");
    condeso22.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso22);

    Condeso condeso23 = new Condeso();
    condeso23.setId(1);
    condeso23.setNombre("Pepe");
    condeso23.setAbreviacion("");
    condeso23.setMasculino();
    condeso23.setFemenino();
    condeso23.setContrato();
    condeso23.setLunch();
    condeso23.setTarde();
    condeso23.setManana();
    condeso23.setColor();
    condeso23.setCaja(true);
    condeso23.setLevel();
    condeso23.setTipo();
    condeso23.setFijos();
    condeso23.setAntiguedad(LocalDate.now());
    condeso23.setColor("#f48642");
    condeso23.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso23);

    Condeso condeso24 = new Condeso();
    condeso24.setId(1);
    condeso24.setNombre("Pepe");
    condeso24.setAbreviacion("");
    condeso24.setMasculino();
    condeso24.setFemenino();
    condeso24.setContrato();
    condeso24.setLunch();
    condeso24.setTarde();
    condeso24.setManana();
    condeso24.setColor();
    condeso24.setCaja(true);
    condeso24.setLevel();
    condeso24.setTipo();
    condeso24.setFijos();
    condeso24.setAntiguedad(LocalDate.now());
    condeso24.setColor("#f48642");
    condeso24.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso24);

    Condeso condeso25 = new Condeso();
    condeso25.setId(1);
    condeso25.setNombre("Pepe");
    condeso25.setAbreviacion("");
    condeso25.setMasculino();
    condeso25.setFemenino();
    condeso25.setContrato();
    condeso25.setLunch();
    condeso25.setTarde();
    condeso25.setManana();
    condeso25.setColor();
    condeso25.setCaja(true);
    condeso25.setLevel();
    condeso25.setTipo();
    condeso25.setFijos();
    condeso25.setAntiguedad(LocalDate.now());
    condeso25.setColor("#f48642");
    condeso25.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso25);

    Condeso condeso26 = new Condeso();
    condeso26.setId(1);
    condeso26.setNombre("Pepe");
    condeso26.setAbreviacion("");
    condeso26.setMasculino();
    condeso26.setFemenino();
    condeso26.setContrato();
    condeso26.setLunch();
    condeso26.setTarde();
    condeso26.setManana();
    condeso26.setColor();
    condeso26.setCaja(true);
    condeso26.setLevel();
    condeso26.setTipo();
    condeso26.setFijos();
    condeso26.setAntiguedad(LocalDate.now());
    condeso26.setColor("#f48642");
    condeso26.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso26);

    Condeso condeso27 = new Condeso();
    condeso27.setId(1);
    condeso27.setNombre("Pepe");
    condeso27.setAbreviacion("");
    condeso27.setMasculino();
    condeso27.setFemenino();
    condeso27.setContrato();
    condeso27.setLunch();
    condeso27.setTarde();
    condeso27.setManana();
    condeso27.setColor();
    condeso27.setCaja(true);
    condeso27.setLevel();
    condeso27.setTipo();
    condeso27.setFijos();
    condeso27.setAntiguedad(LocalDate.now());
    condeso27.setColor("#f48642");
    condeso27.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso27);

    Condeso condeso28 = new Condeso();
    condeso28.setId(1);
    condeso28.setNombre("Pepe");
    condeso28.setAbreviacion("");
    condeso28.setMasculino();
    condeso28.setFemenino();
    condeso28.setContrato();
    condeso28.setLunch();
    condeso28.setTarde();
    condeso28.setManana();
    condeso28.setColor();
    condeso28.setCaja(true);
    condeso28.setLevel();
    condeso28.setTipo();
    condeso28.setFijos();
    condeso28.setAntiguedad(LocalDate.now());
    condeso28.setColor("#f48642");
    condeso28.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso28);

    Condeso condeso29 = new Condeso();
    condeso29.setId(1);
    condeso29.setNombre("Pepe");
    condeso29.setAbreviacion("");
    condeso29.setMasculino();
    condeso29.setFemenino();
    condeso29.setContrato();
    condeso29.setLunch();
    condeso29.setTarde();
    condeso29.setManana();
    condeso29.setColor();
    condeso29.setCaja(true);
    condeso29.setLevel();
    condeso29.setTipo();
    condeso29.setFijos();
    condeso29.setAntiguedad(LocalDate.now());
    condeso29.setColor("#f48642");
    condeso29.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso29);

    Condeso condeso30 = new Condeso();
    condeso30.setId(1);
    condeso30.setNombre("Pepe");
    condeso30.setAbreviacion("");
    condeso30.setMasculino();
    condeso30.setFemenino();
    condeso30.setContrato();
    condeso30.setLunch();
    condeso30.setTarde();
    condeso30.setManana();
    condeso30.setColor();
    condeso30.setCaja(true);
    condeso30.setLevel();
    condeso30.setTipo();
    condeso30.setFijos();
    condeso30.setAntiguedad(LocalDate.now());
    condeso30.setColor("#f48642");
    condeso30.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso30);

    Condeso condeso31 = new Condeso();
    condeso31.setId(1);
    condeso31.setNombre("Pepe");
    condeso31.setAbreviacion("");
    condeso31.setMasculino();
    condeso31.setFemenino();
    condeso31.setContrato();
    condeso31.setLunch();
    condeso31.setTarde();
    condeso31.setManana();
    condeso31.setColor();
    condeso31.setCaja(true);
    condeso31.setLevel();
    condeso31.setTipo();
    condeso31.setFijos();
    condeso31.setAntiguedad(LocalDate.now());
    condeso31.setColor("#f48642");
    condeso31.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso31);

    Condeso condeso32 = new Condeso();
    condeso32.setId(1);
    condeso32.setNombre("Pepe");
    condeso32.setAbreviacion("");
    condeso32.setMasculino();
    condeso32.setFemenino();
    condeso32.setContrato();
    condeso32.setLunch();
    condeso32.setTarde();
    condeso32.setManana();
    condeso32.setColor();
    condeso32.setCaja(true);
    condeso32.setLevel();
    condeso32.setTipo();
    condeso32.setFijos();
    condeso32.setAntiguedad(LocalDate.now());
    condeso32.setColor("#f48642");
    condeso32.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso32);


*/
  }

  public void testCreateHorario() {
    HorarioMaster horarioMaster = new HorarioMaster();
    horarioMaster.setMes(createMes());
    Tiendas tienda = HibernateCrud.GetAllTiendas().get(0);
    tienda.setMaster(horarioMaster);
    HibernateCrud.UpdateTienda(tienda);
  }

  private HashMap<LocalDate, Dias> createMes() {
    HashMap<LocalDate, Dias> result = new HashMap<>();
    Dias dia1 = createDia(LocalDate.now().withDayOfMonth(1));
    Dias dia2 = createDia2(LocalDate.now().withDayOfMonth(2));
    Dias dia3 = createDia3(LocalDate.now().withDayOfMonth(3));
    Dias dia4 = createDia4(LocalDate.now().withDayOfMonth(4));
    Dias dia5 = createDia5(LocalDate.now().withDayOfMonth(5));
    Dias dia6 = createDia6(LocalDate.now().withDayOfMonth(6));
    Dias dia7 = createDia7(LocalDate.now().withDayOfMonth(7));
    result.put(dia1.getDate(), dia1);
    result.put(dia2.getDate(), dia2);
    result.put(dia3.getDate(), dia3);
    result.put(dia4.getDate(), dia4);
    result.put(dia5.getDate(), dia5);
    result.put(dia6.getDate(), dia6);
    result.put(dia7.getDate(), dia7);
    return result;
  }

  private Dias createDia(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos());
    return result;
  }

  private Dias createDia2(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos2());
    return result;
  }

  private Dias createDia3(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos3());
    return result;
  }

  private Dias createDia4(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos4());
    return result;
  }

  private Dias createDia5(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos5());
    return result;
  }
  private Dias createDia6(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos6());
    return result;
  }

  private Dias createDia7(LocalDate date) {
    Dias result = new Dias();
    result.setDate(date);
    result.setTurnos(createTurnos7());
    return result;
  }

  private Set<Turnos> createTurnos3() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(2));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(6));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(7));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(13));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos4() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(17);
    turno1.setCondeso(condesos.get(1));

    Turnos turno2 = new Turnos();
    turno2.setInicio(15);
    turno2.setFin(20);
    turno2.setCondeso(condesos.get(2));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(16);
    turno3.setCondeso(condesos.get(3));

    Turnos turno4 = new Turnos();
    turno4.setInicio(16);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(4));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(5));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(15);
    turno6.setCondeso(condesos.get(6));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(7));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(8));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos5() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(3));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(2));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(12));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(7));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos6() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(9));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(4));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(10));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(7));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(8));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(6));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(5));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos7() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(2));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(7));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(3));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(5));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos2() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(9);
    turno1.setFin(16);
    turno1.setCondeso(condesos.get(9));

    Turnos turno2 = new Turnos();
    turno2.setInicio(16);
    turno2.setFin(21);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(9);
    turno3.setFin(15);
    turno3.setCondeso(condesos.get(10));

    Turnos turno4 = new Turnos();
    turno4.setInicio(17);
    turno4.setFin(23);
    turno4.setCondeso(condesos.get(7));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(23);
    turno5.setCondeso(condesos.get(11));

    Turnos turno6 = new Turnos();
    turno6.setInicio(9);
    turno6.setFin(14);
    turno6.setCondeso(condesos.get(13));

    Turnos turno7 = new Turnos();
    turno7.setInicio(15);
    turno7.setFin(21);
    turno7.setCondeso(condesos.get(14));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(4));

    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  private Set<Turnos> createTurnos() {
    Set<Turnos> result = new TreeSet<>();
    List<Condeso> condesos = HibernateCrud.GetAllCondesos();

    Turnos turno1 = new Turnos();
    turno1.setInicio(12);
    turno1.setFin(20);
    turno1.setCondeso(condesos.get(0));

    Turnos turno2 = new Turnos();
    turno2.setInicio(8);
    turno2.setFin(14);
    turno2.setCondeso(condesos.get(1));

    Turnos turno3 = new Turnos();
    turno3.setInicio(8);
    turno3.setFin(14);
    turno3.setCondeso(condesos.get(2));

    Turnos turno4 = new Turnos();
    turno4.setInicio(14);
    turno4.setFin(22);
    turno4.setCondeso(condesos.get(3));

    Turnos turno5 = new Turnos();
    turno5.setInicio(17);
    turno5.setFin(22);
    turno5.setCondeso(condesos.get(4));

    Turnos turno6 = new Turnos();
    turno6.setInicio(12);
    turno6.setFin(18);
    turno6.setCondeso(condesos.get(5));

    Turnos turno7 = new Turnos();
    turno7.setInicio(16);
    turno7.setFin(22);
    turno7.setCondeso(condesos.get(6));

    Turnos turno8 = new Turnos();
    turno8.setInicio(16);
    turno8.setFin(22);
    turno8.setCondeso(condesos.get(7));



    result.add(turno1);
    result.add(turno2);
    result.add(turno3);
    result.add(turno4);
    result.add(turno5);
    result.add(turno6);
    result.add(turno7);
    result.add(turno8);

    return result;
  }

  public void testCreateTienda()
  {
    Tiendas tienda = new Tiendas();
    tienda.setNombre("HBF");
    HibernateCrud.SaveTienda(tienda);
  }

  public void testGetTiendas()
  {
    List<tiendas.Tiendas> tiendas = HibernateCrud.GetAllDTOTiendas();
    for (tiendas.Tiendas tienda:tiendas
    ) {
      System.out.println(tienda);
    }
  }

}