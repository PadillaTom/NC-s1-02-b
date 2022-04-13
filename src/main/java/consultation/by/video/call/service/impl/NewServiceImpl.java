package consultation.by.video.call.service.impl;

import consultation.by.video.call.model.entity.New;
import consultation.by.video.call.model.mapper.NewMapper;
import consultation.by.video.call.model.request.NewRequest;
import consultation.by.video.call.model.response.NewResponse;
import consultation.by.video.call.repository.NewRepository;
import consultation.by.video.call.service.FirebaseService;
import consultation.by.video.call.service.NewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    private NewMapper newMapper;
    @Autowired
    private NewRepository newRepository;
    @Autowired
    private FirebaseService service;

    @Override
    public NewResponse saveNew(NewRequest request, MultipartFile[] file ) {
        New news = newMapper.toEntityMapper(request, file);
        New saved = newRepository.save(news);
        NewResponse response = newMapper.toDtoMapper(saved);
        return response;
    }

    @Override
    public NewResponse getNewBy(Long id) {
        New entity = newRepository.getById(id);
        NewResponse response = newMapper.toDtoMapper(entity);
        return response;
    }

    @Override
    public List<NewResponse> getAll() {
         return newRepository.findAll().stream().map(n -> newMapper.toDtoMapper(n)).
                 collect(Collectors.toList());

    }

    @Override
    public void deleted(Long id){
        New entity = getNew(id);
        entity.setDeleted(true);
        newRepository.save(entity);
    }

    @Override
    public void update(Long id, NewRequest request,MultipartFile[] file) {
        String imageUrl = service.subirImagen(file);
        New entity = newRepository.findById(id).orElseThrow();
        entity.setImageUrl(imageUrl);
        entity.setSource(request.getSource());
        entity.setTitle(request.getTitle());
        newRepository.save(entity);
    }


    private New getNew(Long id) {
        Optional<New> newOptional = newRepository.findById(id);
            if(newOptional.isEmpty() || newOptional.get().isDeleted()){
                throw new RuntimeException("New no encontrada.");
            }
            return newOptional.get();
    }
}
