/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Praktikum.project.satu.access;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Musafak
 */
@Controller
public class AccessController {
    
    @GetMapping("/form")
    public String newForm(){
        return "form";
    }
    @GetMapping("/add")  
    public String newData(HttpServletRequest request, Model model){
        String namaBaru = request.getParameter("nama");
        try{
        MydataJpaController ctrl = new MydataJpaController();
        Mydata data = new Mydata();
        data.setNama(namaBaru);
        ctrl.create(data);
        model.addAttribute("result", "Data Tersimpan");
        }catch (Exception e) {
           model.addAttribute("result", "Gagal Simpan");
        }
        model.addAttribute("action", "Update database selesai");
    return "form";
    }
}
