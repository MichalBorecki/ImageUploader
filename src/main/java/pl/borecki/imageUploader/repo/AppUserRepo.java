package pl.borecki.imageUploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.borecki.imageUploader.model.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
	
	AppUser findByUsername(String username);

}
