package by.tms.bookshop.controller;

import by.tms.bookshop.entity.Role;
import by.tms.bookshop.entity.Shop;
import by.tms.bookshop.entity.User;
import by.tms.bookshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class StoreInformation {

    @Autowired
    ShopService shopService;

    @GetMapping("/aboutSh")
    public String aboutShop(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<Shop> shops = shopService.findAllInfo();
        model.addAttribute("shops", shops);
        if (user.getRoles().equals(Collections.singleton(Role.ADMIN_ROLE))) {
            return "aboutForAdmin";
        } else {
            return "about";
        }
    }

    @GetMapping("/about/add")
    public String aboutShops(Model model) {
        return "aboutShop";
    }


    @PostMapping("/about/add")
    public String aboutShopAdd(@RequestParam String shopName, @RequestParam String location, @RequestParam String information, Model model) {
        Shop shop = new Shop(shopName, location, information);
        shopService.saveShop(shop);
        return "redirect:/aboutSh";
    }
}
