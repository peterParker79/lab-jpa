package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;




@RestController
public class PersonController {
    @Autowired // indispensable para que inyecte el repositorio.
    PersonRepository personRepository;

    @GetMapping("/api/hello")

    public String sayHello(){
        StringBuilder sb= new StringBuilder();
        List<Person> personList=personRepository.findAll();
        personList.forEach(person -> sb.append(person.toString()));
        return sb.toString();

        //return "HOLA!!!";



    }
}
