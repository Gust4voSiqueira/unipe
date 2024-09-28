package br.com.unipe.service;

import br.com.unipe.entity.user.User;
import br.com.unipe.entity.user.response.MyUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static br.com.unipe.entity.user.response.MyUserResponse.fromUser;

@Service
@RequiredArgsConstructor
public class UserService {

    public MyUserResponse myUser(User user) {
        var today = LocalDate.now();
        var formatter = DateTimeFormatter.ofPattern("MMM", new Locale("pt", "BR"));
        var monthAbbreviation = today.format(formatter);
        var day = today.getDayOfMonth();
        var year = today.getYear();

        return fromUser(user.getName(), day, monthAbbreviation, year);
    }
}
