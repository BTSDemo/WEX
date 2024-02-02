package com.rad.transactionmanager.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {

    @GetMapping("/")
    public String showMainMenu() {
        return "main-menu";
    }
}
