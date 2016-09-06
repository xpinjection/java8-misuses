package com.xpinjection.java8.misused.optional;

import com.xpinjection.java8.misused.Annotations;
import com.xpinjection.java8.misused.Annotations.Good;
import com.xpinjection.java8.misused.Annotations.Ugly;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

public class HundredAndOneApproach {

    @Ugly
    class SameOldImparativeStyle {

        String getPersonCarInsuranceName(Person person) {
            String name = "Unknown";
            if (ofNullable(person).isPresent()) {
                if (person.getCar().isPresent()) {
                    if (person.getCar().get().getInsurance().isPresent()) {
                        name = person.getCar().get().getInsurance().get().getName();
                    }
                }
            }
            return name;
        }
    }

    @Ugly
    class UsingIfPresentInSameImparativeWayWithDirtyHack{

        String getPersonCarInsuranceName(Person person) {
            final StringBuilder builder = new StringBuilder();
            ofNullable(person).ifPresent(
                    p -> p.getCar().ifPresent(
                            c -> c.getInsurance().ifPresent(
                                    i -> builder.append(i.getName())
                            )
                    )
            );
            return builder.toString();
        }
    }

    @Annotations.Bad
    class UsingMapWithUncheckedGet {

        String getPersonCarInsuranceName(Person person) {
            return ofNullable(person)
                    .map(Person::getCar)
                    .map(car -> car.get().getInsurance())
                    .map(insurance -> insurance.get().getName())
                    .orElse("Unknown");
        }
    }

    @Ugly
    class KeepItSimple {

        String getPersonCarInsuranceName(Person person) {
            return ofNullable(person)
                    .map(Person::getCar)
                    .map(car -> car.map(c -> c.getInsurance()))
                    .map(insurance -> insurance.map(i -> i.map(ii -> ii.getName())))
                    .orElse(empty())
                    .orElse(empty())
                    .orElse("Unknown");
        }
    }

    @Ugly
    class UsingMapWithHackToOvercomeAccidentalComplexity {

        String getPersonCarInsuranceName(Person person) {
            return ofNullable(person)
                    .map(Person::getCar)
                    .map(car -> car.orElse(new Car()).getInsurance())
                    .map(insurance -> insurance.orElse(new Insurance()).getName())
                    .orElse("Unknown");
        }
    }

    @Good
    class UsingFlatMap {

        String getCarInsuranceNameFromPersonUsingFlatMap(Person person) {
            return ofNullable(person)
                    .flatMap(Person::getCar)
                    .flatMap(Car::getInsurance)
                    .map(Insurance::getName)
                    .orElse("Unknown");
        }
    }

    class Person{
        public Optional<Car> getCar(){
            return empty(); //stub
        }
        //some other code...
    }

    class Car{
        public Optional<Insurance> getInsurance(){
            return empty(); //stub
        }
        //some other code...
    }

    class Insurance{
        public String getName(){
            return ""; //stub
        }
        //some other code...
    }
}
