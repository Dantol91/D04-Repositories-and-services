
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.FixUpTask;
import domain.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	@Autowired
	private NoteService			noteService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	@Test
	public void createSaveAndDelete() {
		this.authenticate("handyWorker1");

		//	final Note f;
		//		Note nSaved;
		final FixUpTask fixUpTasks;

		//		fixUpTasks = (Trip) this.fixUpTaskService.findAll().toArray()[0];
		//		f = this.noteService.create();
		//		Assert.notNull(n);

		//Probamos save
		//		f.setFiUpTask(fixUpTasks);

		//		nSaved = this.noteService.save(n);
		//		System.out.println(nSaved);
		final Collection<Note> notesBefore = this.noteService.findAll();
		//		Assert.isTrue(notesBefore.contains(nSaved));

		super.authenticate(null);

	}

	//	@Test
	//	public void notesByManagerTrips(){
	//		authenticate("manager2");
	//		Manager m;
	//		
	//		m = (Manager) actorService.findByPrincipal();
	//		
	//	}

}
