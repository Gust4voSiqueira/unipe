package br.com.unipe.entity.lostAndFoundItem.request;

public record CreateItemRequest(String item, String local, String phone, String observation) {
}
