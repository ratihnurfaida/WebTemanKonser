package konserid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import konserid.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	 
		User findByUsernameAndPassword(String username, String password);
}

