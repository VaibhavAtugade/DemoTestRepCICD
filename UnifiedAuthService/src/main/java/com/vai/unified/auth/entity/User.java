package com.vai.unified.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "UNI_USERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "IS_EMAIL_VERIFIED")
    private Integer isEmailVerified;

    @Column(name = "IS_MOBILE_VERIFIED")
    private Integer isMobileVerified;

    @Column(name = "IS_SUPER_ADMIN")
    private Integer isSuperAdmin;

    @Column(name = "LAST_LOGIN_AT")
    private LocalDateTime lastLoginAt;

    @Column(name = "IS_2FA_ENABLED")
    private Integer is2faEnabled;

    @Column(name = "LAST_2FA_VERIFIED_AT")
    private LocalDateTime last2faVerifiedAt;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "MFA_TYPE")
    private String mfaType;

    @Column(name = "MFA_SECRET_KEY")
    private String mfaSecretKey;
}
