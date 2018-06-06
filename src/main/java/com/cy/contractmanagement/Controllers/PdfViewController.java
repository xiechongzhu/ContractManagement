package com.cy.contractmanagement.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PdfViewController {
    @GetMapping("/view-pdf/{name}")
    public String viewPdf(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "pdfView";
    }
}
