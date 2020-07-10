package com.antrl.examples;

import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

//@FunctionalInterface
public interface IComportamiento<Uno,Dos> extends  JpaTest {

//public void algo();
  String array1[]= {"2,3"};
  String [] array2= {"9,6"};
  List<String> lista= new ArrayList<String>();
  String objeto="o";


}