
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Endorse;

@Repository
public interface EndorseRepository extends JpaRepository<Endorse, Integer> {

}
