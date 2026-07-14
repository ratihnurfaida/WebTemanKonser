package konserid.repository;

import konserid.model.Konser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KonserRepository extends JpaRepository<Konser, Integer> {
	List<Konser> findByNamaEventContainingIgnoreCase(String keyword);
	
	@Query("SELECT k FROM Konser k JOIN k.wishlist w GROUP BY k.id ORDER BY COUNT(w.id) DESC")
    List<Konser> findTop5ByOrderByWishlist();
}