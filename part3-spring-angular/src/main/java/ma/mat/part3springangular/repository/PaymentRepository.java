package ma.mat.part3springangular.repository;

import ma.mat.part3springangular.entities.Payment;
import ma.mat.part3springangular.entities.PaymentStatus;
import ma.mat.part3springangular.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List <Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}

