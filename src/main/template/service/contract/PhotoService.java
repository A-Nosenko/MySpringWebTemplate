package template.service.contract;

import org.springframework.stereotype.Service;
import template.model.Photo;

@Service
public interface PhotoService {
    void addPhoto(Photo photo);

    void removePhoto(int id);

    Photo findPhoto(int productId);
}
