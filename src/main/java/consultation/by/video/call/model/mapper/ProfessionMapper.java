package consultation.by.video.call.model.mapper;

import consultation.by.video.call.model.entity.Profession;
import consultation.by.video.call.model.request.ProfessionRequest;
import consultation.by.video.call.model.response.ProfessionResponse;
import org.springframework.stereotype.Component;

@Component
public class ProfessionMapper {

    public Profession toEntity(ProfessionRequest dto) {
        return Profession.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public ProfessionResponse toDTO(Profession entity) {
        return ProfessionResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
