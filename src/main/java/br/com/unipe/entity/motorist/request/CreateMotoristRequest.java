package br.com.unipe.entity.motorist.request;

import java.util.List;

public record CreateMotoristRequest(String phone, String neighborhood, String city, String plate, String car, Integer quantityVacancies,List<String> days, String observation) {
}
