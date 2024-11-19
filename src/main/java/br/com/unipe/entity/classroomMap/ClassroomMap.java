package br.com.unipe.entity.classroomMap;

import br.com.unipe.entity.classroomMap.request.RegisterClassroomRequest;
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
public class ClassroomMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String classroom;
    private String discipline;
    private String course;
    private String day;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User teacher;

    public static ClassroomMap fromRegisterClassroomRequest(RegisterClassroomRequest registerClassroomRequest, User user) {
        return ClassroomMap
                .builder()
                .discipline(registerClassroomRequest.getDiscipline())
                .classroom(registerClassroomRequest.getClassroom())
                .day(registerClassroomRequest.getDay())
                .course(user.getCourse())
                .teacher(user)
                .build();
    }
}
