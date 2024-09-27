package br.com.unipe.controller;

import br.com.unipe.entity.user.AuthenticationDTO;
import br.com.unipe.entity.user.LoginResponseDTO;
import br.com.unipe.entity.user.RegisterDTO;
import br.com.unipe.entity.user.User;
import br.com.unipe.infra.security.TokenService;
import br.com.unipe.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static br.com.unipe.entity.user.User.fromRegisterDTO;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(Objects.nonNull(userRepository.findByEmail(registerDTO.email()))) return ResponseEntity.badRequest().build();

        var encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var newUser = fromRegisterDTO(registerDTO, encryptedPassword);

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
