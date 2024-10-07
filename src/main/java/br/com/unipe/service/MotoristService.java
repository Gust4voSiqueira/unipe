package br.com.unipe.service;

import br.com.unipe.entity.motorist.request.CreateMotoristRequest;
import br.com.unipe.entity.motorist.response.GetMotoristResponse;
import br.com.unipe.entity.motorist.response.IsExistsMotoristRegistered;
import br.com.unipe.entity.motorist.response.ListMotoristsResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.exceptions.UserNotFoundException;
import br.com.unipe.repository.MotoristRepository;
import br.com.unipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.unipe.entity.motorist.Motorist.fromCreateMotoristRequest;
import static br.com.unipe.entity.motorist.response.GetMotoristResponse.fromMotorist;
import static br.com.unipe.entity.motorist.response.IsExistsMotoristRegistered.fromExistsMotoristRegistered;
import static br.com.unipe.entity.motorist.response.IsExistsMotoristRegistered.fromNotExistsMotoristRegistered;

@Service
@RequiredArgsConstructor
public class MotoristService {
    private final MotoristRepository motoristRepository;
    private final UserRepository userRepository;

    public void createMotorist(CreateMotoristRequest createMotoristRequest, User user) {
        var motorist = fromCreateMotoristRequest(createMotoristRequest, user);

        motoristRepository.save(motorist);
    }

    public List<ListMotoristsResponse> getMotorists(String city) {
        var motorists = motoristRepository.findAllByCity(city);

        return motorists.get().stream().map(ListMotoristsResponse::fromMotorist).collect(Collectors.toList());
    }

    public GetMotoristResponse getDetailsMotorist(Long idMotorist) {
        var motorist = motoristRepository.findById(idMotorist)
                .orElseThrow(() -> new UserNotFoundException("Motorista não encontrado."));

        return fromMotorist(motorist);
    }

    public IsExistsMotoristRegistered isExistsCarRegistered(Long idMotorist) {
        var motorist = motoristRepository.findById(idMotorist);

        if(motorist.isEmpty()) {
            return fromNotExistsMotoristRegistered();
        }

        return fromExistsMotoristRegistered(motorist.get());
    }
}
