package br.com.unipe.entity.motorist.request;

import java.util.List;

public record CreateMotoristRequest(String neighborhood, String city, String plate, String car, List<String> days) {
}
