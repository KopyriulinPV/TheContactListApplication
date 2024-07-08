package com.example.demo.controller;
import com.example.demo.Contact;
import com.example.demo.service.ContactServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {


    private final ContactServise contactServise;

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactServise.findAll());
        return "contact-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    @PostMapping("/add")
    public String addContact(@ModelAttribute("contact") Contact contact) {
        contactServise.save(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Contact contact = contactServise.findById(id);
        if (contact != null) {
            model.addAttribute("contact", contact);
            return "contact-form";
        }
        return "redirect:/contacts";
    }

    @PostMapping("/edit/{id}")
    public String updateContact(@PathVariable("id") Long id, @ModelAttribute("contact") Contact contact) {
        contactServise.update(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") Long id) {
        contactServise.deleteById(id);
        return "redirect:/contacts";
    }

}
