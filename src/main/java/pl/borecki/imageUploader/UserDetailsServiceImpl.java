package pl.borecki.imageUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.borecki.imageUploader.repo.AppUserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private AppUserRepo appUserRepo;
	
	@Autowired
	public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
		this.appUserRepo = appUserRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return appUserRepo.findByUsername(username);
	}
}
