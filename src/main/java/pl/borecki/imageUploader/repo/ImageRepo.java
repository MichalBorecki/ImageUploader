package pl.borecki.imageUploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.borecki.imageUploader.model.AppUser;
import pl.borecki.imageUploader.model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
