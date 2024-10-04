package br.com.unipe.service;

import br.com.unipe.entity.motorist.request.CreateMotoristRequest;
import br.com.unipe.entity.motorist.response.GetMotoristResponse;
import br.com.unipe.entity.motorist.response.ListMotoristsResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.exceptions.UserNotFoundException;
import br.com.unipe.repository.MotoristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.unipe.entity.motorist.Motorist.fromCreateMotoristRequest;
import static br.com.unipe.entity.motorist.response.GetMotoristResponse.fromMotorist;

@Service
@RequiredArgsConstructor
public class MotoristService {
    private final MotoristRepository motoristRepository;

    public void createMotorist(CreateMotoristRequest createMotoristRequest, User user) {
        var motorist = fromCreateMotoristRequest(createMotoristRequest, user);

        motoristRepository.save(motorist);
    }

    public List<ListMotoristsResponse> getMotorists(String city) {
        var motorists = motoristRepository.findAllByCity(city);

        return motorists.get().stream().map(ListMotoristsResponse::fromMotorist).collect(Collectors.toList());
    }

    public GetMotoristResponse getMotorist(Long idMotorist) {
        var motorist = motoristRepository.findById(idMotorist)
                .orElseThrow(() -> new UserNotFoundException("Motorista não encontrado."));

        return fromMotorist(motorist);
    }
}
