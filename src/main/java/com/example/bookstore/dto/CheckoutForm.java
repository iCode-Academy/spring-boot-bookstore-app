package com.example.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CheckoutForm {

    @NotBlank(message = "Name is required")
    private String customerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String customerEmail;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}