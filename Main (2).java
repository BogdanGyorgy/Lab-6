package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        List<Angajati>angajati = citire();
       /* List<Angajati> angajati = Arrays.asList(
                new Angajati("Ion Popescu", "Manager", LocalDate.of(2020, 5, 12), 7500.5f),
                new Angajati("Ana Ionescu", "Programator", LocalDate.of(2019, 3, 10), 6200.0f),
                new Angajati("Maria Gheorghe", "Designer", LocalDate.of(2021, 8, 5), 4800.75f),
                new Angajati("Paul Marin", "Tester", LocalDate.of(2018, 1, 20), 4500.25f),
                new Angajati("Cristian Vasile", "Analist", LocalDate.of(2022, 2, 17), 5000.0f)

        );*/

        System.out.println("Afisare angajati: ");
        angajati.forEach(System.out::println);
        System.out.println("\n");

            System.out.println("Afisare angajati cu salariu mai mare decat 2500 RON");
        angajati.stream()
                .filter(angajati1 -> angajati1.getSalariul()>2500)
                .forEach(System.out::println);
        System.out.println("\n");

        System.out.println("Afisare angajati din luna Aprilie");
        int anCurent=LocalDate.now().getYear();
        var listaAprilie= angajati.stream()
                .filter(angajati1 -> angajati1.getDataAngajarii().getYear()==anCurent-1)
                .filter(angajati1 -> angajati1.getDataAngajarii().getMonthValue()==4)
                .filter(angajati1 -> angajati1.getPostul().toLowerCase().contains("sef")
                || angajati1.getPostul().toLowerCase().contains("director"))
                .collect(Collectors.toList());
        listaAprilie.forEach(System.out::println);
        System.out.println("\n");

        System.out.println("Afisarea angajatilor care nu au functie de sef");
        angajati.stream()
                .filter(angajati1 -> !angajati1.getPostul().contains("sef"))
                .sorted(Comparator.comparing(Angajati::getSalariul).reversed())
                .forEach(System.out::println);
        System.out.println("\n");

        System.out.print("Numele angajatilor cu majuscule:");
        List<String>numeAngajati = angajati.stream()
                .map(angajati1 -> angajati1.getNume().toUpperCase())
                .collect(Collectors.toList());
        numeAngajati.forEach(System.out::println);
        System.out.println("\n");

        System.out.print("Salariile angajatilor mai mici decat 3000 ron : ");
        angajati.stream()
                .map(Angajati::getSalariul)
                .filter(salariu ->salariu <3000)
                .forEach(System.out::println);
        System.out.print("\n");

        //primul angajat
        var primAngajat = angajati.stream()
                .min((angajat1, angajat2) -> angajat1.getDataAngajarii().compareTo(angajat2.getDataAngajarii()));

        System.out.print("Primul angajat al firmei: ");
        if (primAngajat.isPresent()) {
            System.out.println(primAngajat.get());
        } else {
            System.out.println("Nu există angajați în firmă");
        }



        System.out.print("\n");

        //statistici salariu

       DoubleSummaryStatistics salarii = angajati.stream()
               .collect(Collectors.summarizingDouble(Angajati::getSalariul));
       System.out.print("Salariu minim : "+ salarii.getMin() + " \n");
       System.out.print("Salariu mediu : "+ salarii.getAverage() + "\n");
       System.out.print("Salariu maxim : " + salarii.getMax());

       System.out.print("\n");

       //angajati care se numesc Ion

       angajati.stream()
               .filter(angajati1 -> angajati1.getPostul().contains("Ion"))
               .findAny()
               .ifPresentOrElse(
                       angajati1->System.out.print("Firma are cel putin un angajat care se numeste Ion"),
                       ()->System.out.print("Firma nu are niciun angajat care se numeste Ion")
               );

       System.out.print("\n");
       //personal angajat vara anului precedent
       int anPrecedent=LocalDate.now().getYear()-1;

       var listavara=angajati.stream()
               .filter(angajati1 -> angajati1.getDataAngajarii().getYear()==anPrecedent)

                       .filter(angajati1 -> angajati1.getDataAngajarii().getMonthValue()>=6 &&
                               angajati1.getDataAngajarii().getMonthValue()<=8)
                               .count();
       System.out.print("Numar personal angajat vara anului precedent: "+ listavara);

               

        }


        public static void scriere(List<Angajati>lista)
        {
            try
            {
                ObjectMapper mapper = new ObjectMapper();
                File file = new File("src/main/resources/angajati.json ");
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                mapper.writeValue(file,lista);

            }catch (IOException e)
            {
                e.printStackTrace();
            }

        }

        public static List<Angajati> citire()
    {
        try
        {
            File file = new File("src/main/resources/angajati.json");
            ObjectMapper mapper= new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<Angajati>angajati = mapper.readValue(file, new TypeReference<java.util.List<Angajati>>() {
            });
            return angajati;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
            return null;
    }





    }
