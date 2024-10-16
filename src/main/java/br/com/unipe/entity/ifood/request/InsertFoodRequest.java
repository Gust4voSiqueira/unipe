package br.com.unipe.entity.ifood.request;

import java.util.List;

public record InsertFoodRequest(String food, List<String> days) {

}
