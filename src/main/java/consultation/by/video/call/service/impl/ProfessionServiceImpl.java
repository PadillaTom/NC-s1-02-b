package consultation.by.video.call.service.impl;

import consultation.by.video.call.model.entity.Profession;
import consultation.by.video.call.model.mapper.ProfessionMapper;
import consultation.by.video.call.model.request.ProfessionRequest;
import consultation.by.video.call.model.response.ProfessionResponse;
import consultation.by.video.call.repository.ProfessionRepository;
import consultation.by.video.call.service.ProfessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionMapper professionMapper;
    private final ProfessionRepository professionRepository;


    @Override
    public ProfessionResponse saveProfession(ProfessionRequest request) {
        Profession newProfession = professionMapper.toEntity(request);
        Profession savedProfession = professionRepository.save(newProfession);
        return professionMapper.toDTO(savedProfession);
    }

    @Override
    public List<ProfessionResponse> getProfessions() {
        return professionRepository.findAll().stream()
                .map( i -> professionMapper.toDTO(i) )
                .collect(Collectors.toList());
    }

    @Override
    public void updateProfession(Long id, ProfessionRequest request) {
        Profession foundProfession = professionRepository.findById(id).orElseThrow();
        foundProfession.setTitle(request.getTitle());
        foundProfession.setDescription(request.getDescription());
        foundProfession.setImageUrl(request.getImageUrl());
        professionRepository.save(foundProfession);
    }

    @Override
    public void deleteProfession(Long id) {
        var foundProfession = professionRepository.findById(id).orElseThrow();
        professionRepository.delete(foundProfession);
    }

    @Override
    public Profession getProfession(Long professionId) {
        Optional<Profession> profession = Optional.of(professionRepository.findById(professionId).orElseThrow());
        return profession.get();
    }


}
