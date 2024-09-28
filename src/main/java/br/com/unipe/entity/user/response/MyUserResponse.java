package br.com.unipe.entity.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyUserResponse {
    private String name;
    private String dateNow;

    public static MyUserResponse fromUser(String name, int day, String month, int year) {
        return MyUserResponse.builder()
                .name(name)
                .dateNow(day + " " + month + " " + year)
                .build();
    }
}

