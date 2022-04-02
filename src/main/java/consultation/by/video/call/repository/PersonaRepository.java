package consultation.by.video.call.repository;

import consultation.by.video.call.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Person, Long> {
}
