package com.tpe.dto;

import com.tpe.domain.Owner;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    @NotBlank(message = "Please enter valid name")
    private String name;

    @NotBlank(message = "Please enter valid surname")
    private String lastName;

    private String phoneNumber;

    @Email(message = "Please enter valid email")
    private String email;

    public OwnerDTO(Owner owner) {
        this.name = owner.getName();
        this.lastName = owner.getLastName();
        this.phoneNumber = owner.getPhoneNumber();
        this.email = owner.getEmail();
    }
}
