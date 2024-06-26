package com.exabyting.rms.Entities;

import com.exabyting.rms.Entities.Helper.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Profile extends BaseEntity{

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String contactNumber;
    private Address address;

    @OneToOne(mappedBy = "profile")
    @Cascade(CascadeType.ALL)
    private User user;

}
