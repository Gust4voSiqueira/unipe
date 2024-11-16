package br.com.unipe.entity.mentoring.response;

import br.com.unipe.entity.mentoring.Mentoring;
import br.com.unipe.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ListMentoringsResponse {
    private Long id;
    private String subject;
    private String description;
    private String phone;
    private Boolean isAddedByCurrentUser;

    public static ListMentoringsResponse fromMentoring(Mentoring mentoring, boolean isAddedByCurrentUser, User user) {
        return ListMentoringsResponse.builder()
                .id(mentoring.getId())
                .subject(mentoring.getSubject())
                .description(mentoring.getDescription())
                .phone(user.getPhone())
                .isAddedByCurrentUser(isAddedByCurrentUser)
                .build();
    }
}
