
import DbController.HibernateCrud;
import horario.Dias;
import horario.HorarioMaster;
import horario.Turnos;
import org.apache.poi.ss.formula.functions.T;
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
    testTiendas();
    testCreateCondeso();
    testPlantillas();

  }

  public  void testPlantillas(){

  }

  public void testTiendas(){
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
  }

  public void testCreateCondeso(){

    TipoEmpleado encargado = TipoEmpleado.Encargado;
    TipoEmpleado GM = TipoEmpleado.GM;
    TipoEmpleado nuevo = TipoEmpleado.Nuevo;
    TipoEmpleado equipo = TipoEmpleado.Equipo;

    Contrato otros = Contrato.otros;
    Contrato minijob = Contrato.MiniJob;

    List<Tiendas> tiendas = HibernateCrud.GetAllTiendas();

    Tiendas mf = tiendas.get(0);
    List<Tiendas> freiheit = new ArrayList<>();
    freiheit.add(mf);
    Tiendas hbf = tiendas.get(1);
    List<Tiendas> haupt = new ArrayList<>();
    haupt.add(hbf);
    Tiendas imp = tiendas.get(2);
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
    condeso8.setDondePuedeTrabajar(haupt);
    HibernateCrud.SaveCondeso(condeso8);

    Condeso condeso9 = new Condeso();
    condeso9.setId(61);
    condeso9.setNombre("Pablo Gonzales");
    condeso9.setAbreviacion("PG");
    condeso9.setMasculino(true);
    condeso9.setFemenino(false);
    condeso9.setContrato(minijob);
    condeso9.setLunch(true);
    condeso9.setTarde(true);
    condeso9.setManana(true);
    condeso9.setColor("#f48642");
    condeso9.setCaja(true);
    condeso9.setLevel(2);
    condeso9.setTipo(equipo);
    condeso9.setFijos(false);
    condeso9.setAntiguedad(LocalDate.now());
    condeso9.setDondePuedeTrabajar(haupt);
    HibernateCrud.SaveCondeso(condeso9);

    Condeso condeso10 = new Condeso();
    condeso10.setId(65);
    condeso10.setNombre("Edurne van Gelderen");
    condeso10.setAbreviacion("EV");
    condeso10.setMasculino(false);
    condeso10.setFemenino(true);
    condeso10.setContrato(otros);
    condeso10.setLunch(true);
    condeso10.setTarde(true);
    condeso10.setManana(true);
    condeso10.setColor("#f48642");
    condeso10.setCaja(true);
    condeso10.setLevel(3);
    condeso10.setTipo(GM);
    condeso10.setFijos(true);
    condeso10.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso10);

    Condeso condeso11 = new Condeso();
    condeso11.setId(67);
    condeso11.setNombre("Ricardo Vera");
    condeso11.setAbreviacion("RV");
    condeso11.setMasculino(true);
    condeso11.setFemenino(false);
    condeso11.setContrato(minijob);
    condeso11.setLunch(true);
    condeso11.setTarde(true);
    condeso11.setManana(true);
    condeso11.setColor("#f48642");
    condeso11.setCaja(true);
    condeso11.setLevel(3);
    condeso11.setTipo(encargado);
    condeso11.setFijos(false);
    condeso11.setAntiguedad(LocalDate.now());
    condeso11.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso11);

    Condeso condeso12 = new Condeso();
    condeso12 .setId(75);
    condeso12.setNombre("Shantal Moreno");
    condeso12.setAbreviacion("SM");
    condeso12.setMasculino(false);
    condeso12.setFemenino(true);
    condeso12.setContrato(otros);
    condeso12.setLunch(true);
    condeso12.setTarde(true);
    condeso12.setManana(true);
    condeso12.setColor("#f48642");
    condeso12.setCaja(true);
    condeso12.setLevel(3);
    condeso12.setTipo(equipo);
    condeso12.setFijos(false);
    condeso12.setAntiguedad(LocalDate.now());
    condeso12.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso12);

    Condeso condeso13 = new Condeso();
    condeso13.setId(84);
    condeso13.setNombre("Rosana Quesada");
    condeso13.setAbreviacion("RQ");
    condeso13.setMasculino(false);
    condeso13.setFemenino(true);
    condeso13.setContrato(otros);
    condeso13.setLunch(true);
    condeso13.setTarde(true);
    condeso13.setManana(true);
    condeso13.setColor("#f48642");
    condeso13.setCaja(true);
    condeso13.setLevel(3);
    condeso13.setTipo(GM);
    condeso13.setFijos(true);
    condeso13.setAntiguedad(LocalDate.now());
    condeso13.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso13);

    Condeso condeso14 = new Condeso();
    condeso14.setId(88);
    condeso14.setNombre("Kristhel McDonald");
    condeso14.setAbreviacion("KM");
    condeso14.setMasculino(false);
    condeso14.setFemenino(true);
    condeso14.setContrato(otros);
    condeso14.setLunch(true);
    condeso14.setTarde(true);
    condeso14.setManana(true);
    condeso14.setColor("#f48642");
    condeso14.setCaja(true);
    condeso14.setLevel(2);
    condeso14.setTipo(equipo);
    condeso14.setFijos(false);
    condeso14.setAntiguedad(LocalDate.now());
    condeso14.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso14);

    Condeso condeso15 = new Condeso();
    condeso15.setId(90);
    condeso15.setNombre("Adrian Rodriguez");
    condeso15.setAbreviacion("AR");
    condeso15.setMasculino(true);
    condeso15.setFemenino(false);
    condeso15.setContrato(otros);
    condeso15.setLunch(true);
    condeso15.setTarde(true);
    condeso15.setManana(true);
    condeso15.setColor("#f48642");
    condeso15.setCaja(true);
    condeso15.setLevel(3);
    condeso15.setTipo(equipo);
    condeso15.setFijos(false);
    condeso15.setAntiguedad(LocalDate.now());
    condeso15.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso15);

    Condeso condeso16 = new Condeso();
    condeso16.setId(94);
    condeso16.setNombre("Esbeidy Flores");
    condeso16.setAbreviacion("EF");
    condeso16.setMasculino(false);
    condeso16.setFemenino(true);
    condeso16.setContrato(minijob);
    condeso16.setLunch(true);
    condeso16.setTarde(true);
    condeso16.setManana(true);
    condeso16.setColor("#f48642");
    condeso16.setCaja(true);
    condeso16.setLevel(2);
    condeso16.setTipo(equipo);
    condeso16.setFijos(false);
    condeso16.setAntiguedad(LocalDate.now());
    condeso16.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso16);

    Condeso condeso17 = new Condeso();
    condeso17.setId(97);
    condeso17.setNombre("Ruth Lopez");
    condeso17.setAbreviacion("RL");
    condeso17.setMasculino(false);
    condeso17.setFemenino(true);
    condeso17.setContrato(otros);
    condeso17.setLunch(true);
    condeso17.setTarde(true);
    condeso17.setManana(true);
    condeso17.setColor("#f48642");
    condeso17.setCaja(true);
    condeso17.setLevel(2);
    condeso17.setTipo(equipo);
    condeso17.setFijos(false);
    condeso17.setAntiguedad(LocalDate.now());
    condeso17.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso17);

    Condeso condeso18 = new Condeso();
    condeso18.setId(98);
    condeso18.setNombre("Carolina Gutierrez");
    condeso18.setAbreviacion("CG");
    condeso18.setMasculino(false);
    condeso18.setFemenino(true);
    condeso18.setContrato(minijob);
    condeso18.setLunch(true);
    condeso18.setTarde(true);
    condeso18.setManana(true);
    condeso18.setColor("#f48642");
    condeso18.setCaja(true);
    condeso18.setLevel(2);
    condeso18.setTipo(equipo);
    condeso18.setFijos(false);
    condeso18.setAntiguedad(LocalDate.now());
    condeso18.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso18);

    Condeso condeso19 = new Condeso();
    condeso19.setId(103);
    condeso19.setNombre("Sebastian Galindez");
    condeso19.setAbreviacion("SG");
    condeso19.setMasculino(true);
    condeso19.setFemenino(false);
    condeso19.setContrato(minijob);
    condeso19.setLunch(true);
    condeso19.setTarde(true);
    condeso19.setManana(true);
    condeso19.setColor("#f48642");
    condeso19.setCaja(true);
    condeso19.setLevel(3);
    condeso19.setTipo(equipo);
    condeso19.setFijos(false);
    condeso19.setAntiguedad(LocalDate.now());
    condeso19.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso19);

    Condeso condeso20 = new Condeso();
    condeso20.setId(106);
    condeso20.setNombre("Bruno Aggierni");
    condeso20.setAbreviacion("BA");
    condeso20.setMasculino(true);
    condeso20.setFemenino(false);
    condeso20.setContrato(otros);
    condeso20.setLunch(true);
    condeso20.setTarde(true);
    condeso20.setManana(true);
    condeso20.setColor("#f48642");
    condeso20.setCaja(true);
    condeso20.setLevel(3);
    condeso20.setTipo(equipo);
    condeso20.setFijos(false);
    condeso20.setAntiguedad(LocalDate.now());
    condeso20.setDondePuedeTrabajar(impler);
    HibernateCrud.SaveCondeso(condeso20);

    Condeso condeso21 = new Condeso();
    condeso21.setId(107);
    condeso21.setNombre("Abigail Sepulveda");
    condeso21.setAbreviacion("AS");
    condeso21.setMasculino(false);
    condeso21.setFemenino(true);
    condeso21.setContrato(otros);
    condeso21.setLunch(true);
    condeso21.setTarde(true);
    condeso21.setManana(true);
    condeso21.setColor("#f48642");
    condeso21.setCaja(true);
    condeso21.setLevel(2);
    condeso21.setTipo(equipo);
    condeso21.setFijos(false);
    condeso21.setAntiguedad(LocalDate.now());
    condeso21.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso21);

    Condeso condeso22 = new Condeso();
    condeso22.setId(108);
    condeso22.setNombre("Angie Betancourt");
    condeso22.setAbreviacion("AB");
    condeso22.setMasculino(false);
    condeso22.setFemenino(true);
    condeso22.setContrato(otros);
    condeso22.setLunch(true);
    condeso22.setTarde(true);
    condeso22.setManana(true);
    condeso22.setColor("#f48642");
    condeso22.setCaja(true);
    condeso22.setLevel(3);
    condeso22.setTipo(GM);
    condeso22.setFijos(false);
    condeso22.setAntiguedad(LocalDate.now());
    condeso22.setDondePuedeTrabajar(haupt);
    HibernateCrud.SaveCondeso(condeso22);

    Condeso condeso23 = new Condeso();
    condeso23.setId(111);
    condeso23.setNombre("Fatima Hernandez");
    condeso23.setAbreviacion("FH");
    condeso23.setMasculino(false);
    condeso23.setFemenino(true);
    condeso23.setContrato(otros);
    condeso23.setLunch(true);
    condeso23.setTarde(true);
    condeso23.setManana(true);
    condeso23.setColor("#f48642");
    condeso23.setCaja(true);
    condeso23.setLevel(2);
    condeso23.setTipo(equipo);
    condeso23.setFijos(false);
    condeso23.setAntiguedad(LocalDate.now());
    condeso23.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso23);

    Condeso condeso24 = new Condeso();
    condeso24.setId(112);
    condeso24.setNombre("Maximiliano Estrada");
    condeso24.setAbreviacion("ME");
    condeso24.setMasculino(true);
    condeso24.setFemenino(false);
    condeso24.setContrato(otros);
    condeso24.setLunch(true);
    condeso24.setTarde(true);
    condeso24.setManana(true);
    condeso24.setColor("#f48642");
    condeso24.setCaja(true);
    condeso24.setLevel(1);
    condeso24.setTipo(nuevo);
    condeso24.setFijos(false);
    condeso24.setAntiguedad(LocalDate.now());
    condeso24.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso24);

    Condeso condeso25 = new Condeso();
    condeso25.setId(113);
    condeso25.setNombre("Constancio Pueyo");
    condeso25.setAbreviacion("CP");
    condeso25.setMasculino(true);
    condeso25.setFemenino(false);
    condeso25.setContrato(otros);
    condeso25.setLunch(true);
    condeso25.setTarde(true);
    condeso25.setManana(true);
    condeso25.setColor("#f48642");
    condeso25.setCaja(true);
    condeso25.setLevel(1);
    condeso25.setTipo(nuevo);
    condeso25.setFijos(false);
    condeso25.setAntiguedad(LocalDate.now());
    condeso25.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso25);

    Condeso condeso26 = new Condeso();
    condeso26.setId(114);
    condeso26.setNombre("Jose Adrian Vega");
    condeso26.setAbreviacion("JV");
    condeso26.setMasculino(true);
    condeso26.setFemenino(false);
    condeso26.setContrato(minijob);
    condeso26.setLunch(true);
    condeso26.setTarde(true);
    condeso26.setManana(true);
    condeso26.setColor("#f48642");
    condeso26.setCaja(true);
    condeso26.setLevel(2);
    condeso26.setTipo(equipo);
    condeso26.setFijos(false);
    condeso26.setAntiguedad(LocalDate.now());
    condeso26.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso26);

    Condeso condeso27 = new Condeso();
    condeso27.setId(115);
    condeso27.setNombre("Juan Pablo Arias");
    condeso27.setAbreviacion("JP");
    condeso27.setMasculino(true);
    condeso27.setFemenino(false);
    condeso27.setContrato(minijob);
    condeso27.setLunch(true);
    condeso27.setTarde(true);
    condeso27.setManana(true);
    condeso27.setColor("#f48642");
    condeso27.setCaja(true);
    condeso27.setLevel(1);
    condeso27.setTipo(nuevo);
    condeso27.setFijos(false);
    condeso27.setAntiguedad(LocalDate.now());
    condeso27.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso27);

    Condeso condeso28 = new Condeso();
    condeso28.setId(116);
    condeso28.setNombre("Marta Marin");
    condeso28.setAbreviacion("MM");
    condeso28.setMasculino(false);
    condeso28.setFemenino(true);
    condeso28.setContrato(otros);
    condeso28.setLunch(true);
    condeso28.setTarde(true);
    condeso28.setManana(true);
    condeso28.setColor("#f48642");
    condeso28.setCaja(true);
    condeso28.setLevel(1);
    condeso28.setTipo(nuevo);
    condeso28.setFijos(false);
    condeso28.setAntiguedad(LocalDate.now());
    condeso28.setDondePuedeTrabajar(tiendas);
    HibernateCrud.SaveCondeso(condeso28);
/*
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