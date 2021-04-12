package Praktikum.project.satu;

import Praktikum.project.satu.access.Mydata;
import Praktikum.project.satu.access.MydataJpaController;
import Praktikum.project.satu.access.Mydetail;
import Praktikum.project.satu.access.MydetailJpaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        MydetailJpaController ctrl = new MydetailJpaController();
        Mydetail detail = new Mydetail();
        MydataJpaController ctrn = new MydataJpaController();
        Mydata data = new Mydata();
        
        detail.setNik("3481080303830002");
        data.setNama("Musafak");
        data.setMydetail(detail);
        
        ctrl.create(detail);
        ctrn.create(data);
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