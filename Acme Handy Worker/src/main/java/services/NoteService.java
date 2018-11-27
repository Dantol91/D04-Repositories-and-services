
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Actor;
import domain.Note;
import domain.Referee;

@Service
@Transactional
public class NoteService {

	// Managed repository

	@Autowired
	private NoteRepository	noteRepository;

	//Supporting services

	@Autowired
	private ActorService	actorService;


	//	@Autowired
	//	private RefereeService			refereeService;

	//	@Autowired
	//	private AdministratorService	administratorService;

	// Constructor

	public NoteService() {
		super();
	}

	// Simple CRUD methods

	public Note create() {

		Note note;
		Date moment;
		final Collection<Note> notes;
		//final Referee referee;

		//referee = (Referee) this.actorService.findByPrincipal();
		note = new Note();
		moment = new Date(System.currentTimeMillis() - 1);

		notes = new ArrayList<Note>();
		notes.add(note);

		note.setMoment(moment);

		return note;
	}

	public Note save(final Note note) {
		Assert.notNull(note);

		Note result;
		Date moment;
		final Collection<Note> notes;

		moment = new Date(System.currentTimeMillis() - 1);

		note.setMoment(moment);
		result = this.noteRepository.save(note);
		notes = new ArrayList<Note>();
		notes.add(result);

		//Comprobamos si es spam
		//		this.administratorService.checkIsSpam(note.getRemark());

		return result;
	}

	public Note findOne(final int NoteId) {
		Note result;

		result = this.noteRepository.findOne(NoteId);

		return result;
	}

	public Collection<Note> findAll() {
		Collection<Note> result;

		result = this.noteRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Other Bussines Methods 

	public void checkPrincipal(final Note note) {
		final Referee referee;
		Actor actor;

		referee = new Referee();
		actor = this.actorService.findByPrincipal();

		Assert.isTrue(referee.equals(actor));
	}

	//The minimum, the maximum, the average, and the standard deviation of the number of notes per referee report.

	public Double[] computeMinMaxAvgStddevNotesPerReport() {

		return this.noteRepository.computeMinMaxAvgStddevNotesPerReport();
	}

	/*
	 * 
	 * //B.13.1 Un Manager puede mostrar las notas que un auditor ha escrito en sus trips
	 * 
	 * public Collection<Note> getNotesToManagerTrips(final int managerId) {
	 * return this.noteRepository.getNotesToManagerTrips(managerId);
	 * }
	 * 
	 * // B.14.1 Un referee puede listar y escribir sus notas en fixUpTasks. Una vez que una nota
	 * // ha sido escrita, esta no puede ser borrada o modificada
	 * 
	 * // B.16.4 Un Admin puede mostrar en el dashboard las estadisticas de notes por fixUpTasks
	 * 
	 * public Double[] getMinMaxAvgStddevNotesPerTrip() {
	 * return this.noteRepository.getMinMaxAvgStddevNotesPerTrip();
	 * }
	 * 
	 * public Collection<Note> getNotesByAuditorId(final int auditorId) {
	 * return this.noteRepository.getNotesByAuditorId(auditorId);
	 * 
	 * }
	 */

}
