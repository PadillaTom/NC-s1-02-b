package consultation.by.video.call.service.impl;

import consultation.by.video.call.model.response.PatientResponseById;
import consultation.by.video.call.model.response.PatientsReponse;
import consultation.by.video.call.repository.IUserRepository;
import consultation.by.video.call.repository.PatientRepository;
import consultation.by.video.call.service.IUserService;
import consultation.by.video.call.exception.ParamNotFound;
import consultation.by.video.call.model.entity.Patient;
import consultation.by.video.call.model.entity.Professional;
import consultation.by.video.call.model.entity.Turn;
import consultation.by.video.call.model.entity.User;
import consultation.by.video.call.model.mapper.PatientMapper;
import consultation.by.video.call.model.mapper.TurnMapper;
import consultation.by.video.call.model.request.PatientTurnRequest;
import consultation.by.video.call.model.response.PatientTurnResponse;
import consultation.by.video.call.repository.TurnRepository;
import consultation.by.video.call.service.PatientService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javassist.NotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private static final String ERROR_CONECTION = "Error trying to connect to BD: ";
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private TurnRepository turnRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private PatientMapper mapperPatient;
    @Autowired
    private TurnMapper mapperTurn;
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    @Override
    public PatientTurnResponse savePatientTurn(PatientTurnRequest request) {

        Patient patient;
        try {
            patient = (Patient) userService.getInfoUser();
        } catch (NotFoundException e) {
            throw new ParamNotFound("error: Username is not valid");
        }

        Professional professional = (Professional) userRepository.findById(request.getProfessional().getId()).orElseThrow();
        request.getProfessional().setLastName(professional.getLastName());
        request.getProfessional().setFirstName(professional.getFirstName());
        PatientTurnResponse patientTurn = mapperPatient.toEntity(request, dayDate(request.getDayMonthYear().toString()));
        try {
            Turn turn = turnRepository.save(mapperTurn.toDTO(patientTurn, professional, patient));
            patientTurns(patient, turn, professional);
        } catch (Exception e) {
            throw new ParamNotFound(ERROR_CONECTION + e.getMessage());
        }
        return patientTurn;
    }

    @Transactional
    private void patientTurns(Patient patient, Turn t, Professional professional) {
        List<Turn> newTurns = patient.getTurnList();
        newTurns.add(t);
        patient.setTurnList(newTurns);
        List<Professional> newProfessional = patient.getProfessionals();
        newProfessional.add(professional);
        patient.setProfessionals(newProfessional);
        try {
            userRepository.save(patient);
        } catch (Exception e) {
            throw new ParamNotFound(ERROR_CONECTION + e.getMessage());
        }

    }

    private String dayDate(String date) {
        String day = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCurrent = null;
        try {
            dateCurrent = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar dateCalendar = new GregorianCalendar();
        dateCalendar.setTime(dateCurrent);
        int dayWeek = dateCalendar.get(Calendar.DAY_OF_WEEK);

        switch (dayWeek) {
            case 1:
                day = "Domingo";
                break;
            case 2:
                day = "Lunes";
                break;
            case 3:
                day = "Martes";
                break;
            case 4:
                day = "Miércoles";
                break;
            case 5:
                day = "Juevess";
                break;
            case 6:
                day = "Viernes";
                break;
            case 7:
                day = "Sábado";
                break;
            default:
                throw new AssertionError();
        }
        return day;
    }

    @Override
    public List<PatientsReponse> getPatients() {
        List<User> users = userRepository.findAll();
        List<PatientsReponse> patientsList = new ArrayList();
        for (User patient : users) {
            if (patient.getRoles().get(0).getName().equals("ROLE_PATIENT")) {
                patientsList.add(mapperPatient.toDTO((Patient) patient));
            }
        }
        if (patientsList.isEmpty()) {
            throw new ParamNotFound("Empty patient list ");
        }

        return patientsList;
    }

    @Override
    public PatientResponseById getPatientById(Long id) {
        Patient patient = patientRepository.getById(id);
        if (patient.isDeleted()) {
            throw new ParamNotFound("Eliminated patient");
        }
        PatientResponseById response = mapperPatient.toDTOPatient(patient, true);
        return response;
    }
}
