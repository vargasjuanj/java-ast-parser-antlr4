package com.antrl.examples;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

//@FunctionalInterface
@Repository
public interface IComportamiento<Uno,Dos> extends  JpaTest {

  //public void algo();
  String array1[]= {"2,3"};
  String [] array2= {"9,6"};
  List<String> lista= new ArrayList<String>();
  String objeto="o";

  public static void moverPosicion(int x, int y) {

  }
  public static int moverPosicion2(int x, int y) {
    return 0;
  }
  int algo3();

  public default void algo() {

  }



  public default int algo2() {
    return 0;
  }

  public void disparar(int z);
  public String caminar(String a);
  int nose();
  @Query("select a FROM Articulo a WHERE a.categoriaArticulo.denominacion=?1")
  List<Domicilio> findByCategoria(String categoria);
  @Query(value="select a FROM Articulo a WHERE a.categoriaArticulo.denominacion=?1")
  List<Domicilio> findByCategoria2(String categoria);

  @Query(nativeQuery = true, value="select a FROM Articulo a WHERE a.categoriaArticulo.denominacion=?1")
  List<Domicilio> findByCategoria3(String categoria);

  @Query(value="select a FROM Articulo a WHERE a.categoriaArticulo.denominacion=?1",nativeQuery = true )
  List<Domicilio> findByCategoria4(String categoria);



}