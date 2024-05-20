package com.backend.ecommerce.Controller;

import com.backend.ecommerce.entity2.Car;
import com.backend.ecommerce.entity2.CarRepo;
import com.backend.ecommerce.entity2.Person;
import com.backend.ecommerce.entity2.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cont")
@AllArgsConstructor
public class Cont {

    CarRepo carRepo;
    PersonRepo personRepo;

    @GetMapping
    public List<Car> get(){
        return carRepo.findAll();
    }

//    @PostMapping
//    public List<Person> create(){
//        Person person = new Person()
//        personRepo.saveAll();
//    }
}
