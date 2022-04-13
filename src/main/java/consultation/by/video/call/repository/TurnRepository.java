package consultation.by.video.call.repository;

import consultation.by.video.call.model.entity.Turn;
import consultation.by.video.call.model.enums.EnumState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {
    List<Turn>findByHigh(EnumState status);
}
