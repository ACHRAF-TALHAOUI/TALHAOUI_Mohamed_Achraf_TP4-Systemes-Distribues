package ma.mat.part3springangular;

import ma.mat.part3springangular.entities.Payment;
import ma.mat.part3springangular.entities.PaymentStatus;
import ma.mat.part3springangular.entities.PaymentType;
import ma.mat.part3springangular.entities.Student;
import ma.mat.part3springangular.repository.PaymentRepository;
import ma.mat.part3springangular.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class Part3SpringAngularApplication {

    public static void main(String[] args) {

        SpringApplication.run(Part3SpringAngularApplication.class, args);
    }




    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Achraf").code("112233").programId("IAAD")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Salah").code("112244").programId("IAAD")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Mohamed").code("112255").programId("GL")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("Ali").code("112266").programId("IABD")
                    .build());
            PaymentType[] paymentTypes=PaymentType.values();
            Random random =new Random();
            studentRepository.findAll().forEach(st->{
                for (int i = 0; i <10 ; i++) {
                    int index =random.nextInt(paymentTypes.length);
                    Payment payment=Payment.builder()
                            .amount(1000+(int)(Math.random()*20000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });
        };
    }

}