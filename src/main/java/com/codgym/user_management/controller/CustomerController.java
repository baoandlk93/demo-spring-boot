package com.codgym.user_management.controller;

import com.codgym.user_management.entity.Customer;
import com.codgym.user_management.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("customer/create", "customer", new Customer());
    }

    @PostMapping("/save")
    public ModelAndView save(Customer customer) {
        customerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("{id}/edit")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return new ModelAndView("customer/update", "customer", customer);
    }

    @PostMapping("/update")
    public ModelAndView update(Customer customer) {
        customerService.save(customer);
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
         customerService.remove(id);
        return new ModelAndView("redirect:/customers");
    }

}
