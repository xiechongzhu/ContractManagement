package com.cy.contractmanagement.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @GetMapping("")
    public String GetContractHtml() {
        return "contract";
    }

    @GetMapping("/add")
    @ResponseBody
    public void addContract() {

    }
}
