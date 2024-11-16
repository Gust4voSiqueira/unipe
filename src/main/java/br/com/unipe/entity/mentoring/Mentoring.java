package br.com.unipe.entity.mentoring;

import br.com.unipe.entity.mentoring.request.CreateMentoringRequest;
import br.com.unipe.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String description;

    private String course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Mentoring fromCreateMentoringRequest(CreateMentoringRequest request, User user) {
        return Mentoring
                .builder()
                .subject(request.subject())
                .description(request.description())
                .course(user.getCourse())
                .user(user)
                .build();
    }
}
