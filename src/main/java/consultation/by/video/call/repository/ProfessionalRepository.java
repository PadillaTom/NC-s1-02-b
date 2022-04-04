package consultation.by.video.call.repository;

import consultation.by.video.call.model.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Professional findByEmail(String email);

}
