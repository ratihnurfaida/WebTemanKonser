package konserid.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import konserid.model.GuestStar;
import konserid.model.Konser;

public interface GuestStarRepository extends JpaRepository<GuestStar, Integer> {
    List<GuestStar> findByKonser(Konser konser);
}
