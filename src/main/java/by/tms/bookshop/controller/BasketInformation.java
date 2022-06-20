package by.tms.bookshop.controller;

import by.tms.bookshop.entity.User;
import by.tms.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketInformation {

    @Autowired
    private BookService bookService;


    @GetMapping("/basket")
    public String bookInBasket(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Double result = bookService.buyBooks(user);
        model.addAttribute("result", result);
        return "basket";
    }
}
