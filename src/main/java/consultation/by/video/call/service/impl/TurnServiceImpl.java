package consultation.by.video.call.service.impl;

import consultation.by.video.call.repository.IUserRepository;
import consultation.by.video.call.exception.ParamNotFound;
import consultation.by.video.call.model.entity.Turn;
import consultation.by.video.call.model.enums.EnumState;
import consultation.by.video.call.model.mapper.TurnMapper;
import consultation.by.video.call.model.response.TurnsPatientResponse;
import consultation.by.video.call.repository.TurnRepository;
import consultation.by.video.call.service.TurnService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnServiceImpl implements TurnService {

    @Autowired
    private IUserRepository userRepository; 
    @Autowired
    private TurnMapper turnMapper;
    @Autowired
    private TurnRepository turnRepository;
 

    @Override
    public TurnsPatientResponse getTurnById(Long Id) {
        Turn t= turnRepository.findById(Id).orElseThrow();
        return turnMapper.toDtResponse(t);               
    }

    @Override
    public List<TurnsPatientResponse> getAllTurns() {
         return turnRepository.findAll().stream()
                .map( i -> turnMapper.toDtResponse(i) )
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TurnsPatientResponse> getAllTurnsActived(EnumState high) {
       List<Turn> turnsList= turnRepository.findByHigh(high);
        if (turnsList.isEmpty()) {
           throw new ParamNotFound("Parameter does not work or shift list empty");
        }
       List <TurnsPatientResponse> listResponse=new ArrayList<>();
        for (Turn turn : turnsList) {
            listResponse.add(turnMapper.toDtResponse(turn));
        }        
        return listResponse;
    }
}
