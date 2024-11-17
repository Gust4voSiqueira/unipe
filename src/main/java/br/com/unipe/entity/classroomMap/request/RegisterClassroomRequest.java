package br.com.unipe.entity.classroomMap.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterClassroomRequest {
    private String discipline;
    private String classroom;
    private String day;
}
