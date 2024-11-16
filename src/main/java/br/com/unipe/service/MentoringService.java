package br.com.unipe.service;

import br.com.unipe.entity.mentoring.request.CreateMentoringRequest;
import br.com.unipe.entity.mentoring.response.ListMentoringsResponse;
import br.com.unipe.exceptions.MentoringNotFoundException;
import br.com.unipe.exceptions.UnauthorizedItemDeletionException;
import br.com.unipe.exceptions.UserNotFoundException;
import br.com.unipe.repository.MentoringRepository;
import br.com.unipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.unipe.entity.mentoring.Mentoring.fromCreateMentoringRequest;
import static br.com.unipe.entity.mentoring.response.ListMentoringsResponse.fromMentoring;

@Service
@RequiredArgsConstructor
public class MentoringService {
    private final UserRepository userRepository;
    private final MentoringRepository mentoringRepository;

    public void createMentoring(CreateMentoringRequest createMentoringRequest, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        var mentoring = fromCreateMentoringRequest(createMentoringRequest, user);

        mentoringRepository.save(mentoring);
    }

    public List<ListMentoringsResponse> listMentorings(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        return mentoringRepository
                .findByCourse(user.getCourse())
                .stream()
                .map(mentoring -> {
                    var isAddedByCurrentUser = mentoring.getUser().getId().equals(userId);
                    return fromMentoring(mentoring, isAddedByCurrentUser, user);
                })
                .toList();
    }

    public void deleteMentoring(Long mentoringId, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        var mentoring = mentoringRepository.findById(mentoringId)
                .orElseThrow(() -> new MentoringNotFoundException("Mentoria não encontrado."));

        if(!mentoring.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedItemDeletionException("Você não pode deletar uma mentoria que não foi cadastrada por você.");
        }

        mentoringRepository.delete(mentoring);
    }
}
