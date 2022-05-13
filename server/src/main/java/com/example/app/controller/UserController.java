package com.example.app.controller;

import com.example.app.entity.ContactForm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import com.example.app.entity.User;
import com.example.app.service.UserService;

@Controller
@RequestMapping("/users") // ①
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public String index(Model model) { // ②
    List<User> users = userService.findAll();
    model.addAttribute("users", users); // ③
    return "users/index"; // ④
  }

  @GetMapping("new")
  public String newForm(Model model) {
    model.addAttribute("contactForm", new ContactForm());
    return "users/new";
  }

  // @GetMapping("{id}/edit")
  // public String edit(@PathVariable Long id, Model model) { // ⑤
  // User player = userService.findOne(id);
  // model.addAttribute("player", player);
  // return "users/edit";
  // }

  // @GetMapping("{id}")
  // public String show(@PathVariable Long id, Model model) {
  // Optional<User> user = userService.findOne(id);
  // model.addAttribute("user", user);
  // return "users/show";
  // }

  @PostMapping("confirm")
  public String confirm(
      @RequestParam String name,
      @RequestParam String email,
      @RequestParam String inquiry,

      Model model) {
    model.addAttribute("name", name);
    model.addAttribute("email", email);
    model.addAttribute("inquiry", inquiry);
    return "users/confirm";
  }

  @PostMapping
  public String create(@ModelAttribute User user) {
    userService.save(user);
    return "users/complete";
  }

  // @PutMapping("{id}")
  // public String update(@PathVariable Long id, @ModelAttribute User player) {
  // player.setId(id);
  // ; return }

  // @DeleteMapping("{id}")
  // public String destroy(@PathVariable Long id) {
  // userService.delete(id);
  // return "redirect:/users";
  // }
}