
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("select min(r.notes.size), max(r.notes.size), avg(r.notes.size), stddev(r.notes.size) from Report r")
	Double[] computeMinMaxAvgStddevNotesPerReport();

	@Query("select c from Customer c join c.fixUpTasks f join f.notes n where c.id=?1")
	Collection<Note> getNotesToCustomerFixUpTasks(int customerId);

}
