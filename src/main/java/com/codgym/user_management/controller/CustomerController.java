package com.codgym.user_management.controller;

import com.codgym.user_management.dto.CustomerDto;
import com.codgym.user_management.entity.Customer;
import com.codgym.user_management.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView showList(@PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customerService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() throws Exception {
        Exception exception = new Exception();
        return new ModelAndView("customer/create", "customer", new CustomerDto());
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("customer") @Validated CustomerDto customer,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("customer/create");
        } else {
            Customer customer1 = new Customer();
            customer1.setName(customer.getName());
            customer1.setEmail(customer.getEmail());
            customer1.setAddress(customer.getAddress());
            customerService.save(customer1);
            return new ModelAndView("redirect:/customers");
        }
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
