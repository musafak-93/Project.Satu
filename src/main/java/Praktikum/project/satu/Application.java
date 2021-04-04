package Praktikum.project.satu;

import Praktikum.project.satu.access.Mydata;
import Praktikum.project.satu.access.MydataJpaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        try {
//            MydataJpaController ctr = new MydataJpaController();
//            Mydata dta = new Mydata();
//
//            dta.setId(1);
//            dta.setNama("Musafak");
//            
//            ctr.create(dta);
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    }

}