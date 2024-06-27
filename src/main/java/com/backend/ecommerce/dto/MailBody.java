package com.backend.ecommerce.dto;

public record MailBody(String recipient,String messageBody, String subject, String attachment) {
}
