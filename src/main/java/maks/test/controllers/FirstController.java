package maks.test.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request) {// NOTE НЕ падает в ошибку если параметров нет
        String name = request.getParameter("name");
        String surname = request.getParameter("secondName");

        System.out.println(name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(
            @RequestParam("name") String name,
            @RequestParam(value = "secondName", required = false) String surname, // NOTE Падает в ошибку если параметров нет, иногда нужен required = false
            Model model) {

        model.addAttribute("message", "Goodbye " + name + " " + surname);

        System.out.println(name + " " + surname);
        return "first/goodbye";
    }
}
