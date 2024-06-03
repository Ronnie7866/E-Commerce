package com.backend.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserBankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String accountHolderName;
    private String accountNumber;
    private String bankName;
    private String branchName;
    private String ifscCode;
    private String accountType;
    private boolean isPrimary;
    private String status;
    private LocalDateTime dateAdded;
}
