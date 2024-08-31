package maks.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calc")
public class CalcController {
    @GetMapping
    public String calc(@RequestParam("a") int a,
                       @RequestParam("b") int b,
                       @RequestParam("action") CalcActions action,
                       Model model) {
        double result = 0;
        switch (action) {
            case ADDITION:
                result = a + b;
                break;
            case SUBTRACTION:
                result = a - b;
                break;
            case MULTIPLICATION:
                result = a * b;
                break;
            case DIVISION:
                result = a / (double)b;
                break;
        }

        model.addAttribute("expression", a + " " + this.convertActionToMathSymbol(action) + " " + b);
        model.addAttribute("result", result);

        return "calc";

    }

    String convertActionToMathSymbol(CalcActions action) {
        switch (action) {
            case ADDITION:
                return "+";
            case SUBTRACTION:
                return "-";
            case MULTIPLICATION:
                return "*";
            case DIVISION:
                return "/";
        }
        return "";
    }
}
