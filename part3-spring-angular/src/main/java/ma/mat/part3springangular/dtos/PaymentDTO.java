package ma.mat.part3springangular.dtos;


import jakarta.persistence.*;
import lombok.*;
import ma.mat.part3springangular.entities.PaymentStatus;
import ma.mat.part3springangular.entities.PaymentType;
import ma.mat.part3springangular.entities.Student;

import java.time.LocalDate;



@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
}