package com.cy.contractmanagement.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {
    @GetMapping("")
    public void handle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/contract");
    }
}
