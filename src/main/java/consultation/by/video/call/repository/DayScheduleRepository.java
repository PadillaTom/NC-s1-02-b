package consultation.by.video.call.repository;

import consultation.by.video.call.model.entity.DaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long>{
    
}
