package br.com.unipe.entity.classroomMap.response;

import br.com.unipe.entity.classroomMap.ClassroomMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ListClassRoomResponse {
   private Boolean isTeacher;
   private List<ListClassroom> classroomList;

    @Getter
    @Setter
    @Builder
    private static class ListClassroom {
        private Long id;
        private String discipline;
        private String day;
        private String classroom;
    }

    public static ListClassRoomResponse fromClassroomMap(Boolean isTeacher, List<ClassroomMap> classroomMap) {
        var classroomList = classroomMap
                .stream()
                .map(classroom -> ListClassroom
                        .builder()
                        .id(classroom.getId())
                        .discipline(classroom.getDiscipline())
                        .day(classroom.getDay())
                        .classroom(classroom.getClassroom())
                        .build())
                .toList();

        return ListClassRoomResponse
                .builder()
                .isTeacher(isTeacher)
                .classroomList(classroomList)
                .build();
    }
}
