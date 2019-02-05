package template.service;

import template.model.Photo;
import template.repository.PhotoRepository;
import template.service.contract.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;

public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public void addPhoto(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public void removePhoto(int id) {
        photoRepository.deleteById(id);
    }

    @Override
    public Photo findPhoto(int productId) {
        return photoRepository.findPhotoByProductId(productId);
    }
}
