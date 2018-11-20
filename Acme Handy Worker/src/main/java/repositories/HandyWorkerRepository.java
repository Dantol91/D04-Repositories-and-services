
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.HandyWorker;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	@Query("select h from HandyWorkr h where h.userAccount.id = ?1")
	HandyWorker findByUserAccountId(int userAccountId);

	@Query("select h from HandyWorker h join h.applications a where a.id = ?1")
	HandyWorker findByApplicationId(int applicatioId);

}
