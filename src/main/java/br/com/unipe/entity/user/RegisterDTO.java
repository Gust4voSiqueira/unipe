package br.com.unipe.entity.user;

public record RegisterDTO(String email, String name, String course, String password, String confirmPassword) {
}
