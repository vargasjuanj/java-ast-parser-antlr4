package com.antrl.examples;

import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

public interface IComportamiento<Uno,Dos> extends  JpaTest {

//public void algo();
}