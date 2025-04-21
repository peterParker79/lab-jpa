package com.example.demo.repositoryTest;


import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest // Contexto para las clases de Test. [Levanta..test...cierra Server.]


public class PersonRepositoryTest {
    @Autowired //Indica a Spring que generemos la interface
    private PersonRepository personRepository;// nos permitira hacer CRUD contra la BBDD

    @Test
    @DisplayName("Testear el método save()")
    public void testSave(){
        Person person = new Person("Dani", 39);
        personRepository.save(person);
        System.out.println(person.toString());
    }

    @Test
    @DisplayName("Testear el método findByName()")
    public void testFindByName() {

   /*
   Busca la persona en el repositorio usando el
   metodo findById() y comprueba que no es nulo.
    */
        assertNotNull(personRepository.findById((long)1));

       // Ojo, devuelve un Opcional y no un Student porque el resultado del findBy
       // podría ser null y no necesariamente un tipo Person.
        Optional<Person> person = personRepository.findById((long)1);
        Person personType= person.get();
        // En personType ya puedo llamar a los métodos propios de la clase Person
        System.out.println(personType.getId());
        System.out.println(personType.getName());
        System.out.println(personType.getAge());
    }

    @Test
    @DisplayName("Comprobar datos de la persona John Doe")
    public void testJohnDoe() {
        personRepository.save(new Person("John Doe",30));
        List<Person> foundPerson = personRepository.findByName("John Doe");
       // assertEquals("John Doe", foundPerson.getName());
        foundPerson.forEach(person -> assertEquals("John Doe", person.getName()));

    }

    @Test
    @DisplayName("Comprobar la edad de la persona que es 30") //
    public void testEdad() {
        personRepository.save(new Person("John Doe", 30));
        List<Person> foundPerson = personRepository.findByName("John Doe");
        // assertEquals("John Doe", foundPerson.getName());
        foundPerson.forEach(person -> assertEquals(30, person.getAge()));

    }

}
