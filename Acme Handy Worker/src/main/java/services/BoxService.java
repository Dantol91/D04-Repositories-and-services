
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {

	// Managed repository
	@Autowired
	private BoxRepository	boxRepository;

	// Supporting services
	@Autowired
	private ActorService	actorService;

	@Autowired
	private MessageService	messageService;


	// Constructor
	public BoxService() {
		super();
	}

	// Simple CRUD methods

	public Box create() {

		final Box box = new Box();
		box.setSystemBox(false);
		box.setMessages(new ArrayList<Message>());
		//	folder.setNotifications(new ArrayList<Notification>());
		Actor actor;
		actor = this.actorService.findByPrincipal();
		final Collection<Box> actorBoxes = actor.getBoxes();
		actorBoxes.add(box);
		actor.setBoxes(actorBoxes);
		box.setActor(this.actorService.findByPrincipal());
		return box;
	}

	public Box save(final Box box) {
		Assert.notNull(box);
		// Assert.isTrue(!folder.getSystemFolder());
		if (box.getId() != 0)
			this.checkPrincipal(box);
		Actor actor;
		actor = this.actorService.findByPrincipal();
		box.setActor(actor);
		final Box boxSaved = this.boxRepository.save(box);
		final Collection<Box> actorFolders = actor.getBoxes();
		actorFolders.add(boxSaved);
		actor.setBoxes(actorFolders);
		this.actorService.save(actor);

		return boxSaved;
		// actorService.save(actor);

	}

	public void saveToMove(final Box box, final Box targetBox) {

		Assert.notNull(targetBox);
		Assert.notNull(box);
		box.setParentBox(targetBox);

		//		Message copy = message;
		//		this.messageRepository.delete(message.getId());
		//		Message savedCopy = this.messageRepository.save(copy);
		//		Folder currentFolder = folderService.getFolderFromMessageId(copy
		//				.getId());
		//		Collection<Message> currentFolderMessages = currentFolder.getMessages();
		//		currentFolderMessages.remove(copy);
		//		
		//		
		//		currentFolder.setMessages(currentFolderMessages);
		//		folderService.simpleSave(currentFolder);
		//
		//		// Message saved = this.messageRepository.save(message);
		//		Collection<Message> folderMessages = folder.getMessages();
		//		folderMessages.add(savedCopy);
		//		folder.setMessages(folderMessages);
		//		folderService.simpleSave(folder);

	}

	public Box simpleSave(final Box b) {
		Assert.notNull(b);

		final Box boxSaved = this.boxRepository.save(b);

		return boxSaved;

	}

	public Box saveNotificationBox(final Box box) {
		Assert.notNull(box);
		// Assert.isTrue(!folder.getSystemFolder());
		Actor actor;
		actor = this.actorService.findByPrincipal();
		box.setActor(actor);
		final Box boxSaved = this.boxRepository.save(box);
		final Collection<Box> actorFolders = actor.getBoxes();
		actorFolders.add(boxSaved);
		actor.setBoxes(actorFolders);
		this.actorService.save(actor);

		return boxSaved;
		// actorService.save(actor);
	}

	public List<Box> save(final Iterable<Box> boxes) {
		Assert.notNull(boxes);
		return this.boxRepository.save(boxes);

	}

	public Box findOne(final int boxId) {
		Assert.notNull(boxId);
		Assert.isTrue(boxId != 0);
		final Box box;
		box = this.boxRepository.findOne(boxId);
		return box;
	}

	public void delete(final Box box) {
		Assert.notNull(box);
		Assert.isTrue(!box.getSystemBox());
		//		this.checkPrincipal(box);
		final Collection<Message> messages = box.getMessages();
		final Actor a = this.actorService.findByPrincipal();
		a.getBoxes().remove(box);
		this.actorService.save(a);

		final Collection<Box> childBoxes = this.boxRepository.getChildBoxes(box.getId());

		if (childBoxes.size() > 0)
			for (final Box child : childBoxes)
				this.delete(child);

		this.boxRepository.delete(box);
		this.messageService.delete(messages);

	}

	public void delete(final Iterable<Box> boxes) {
		Assert.notNull(boxes);

		this.boxRepository.delete(boxes);
	}

	public Collection<Box> findAll() {
		return this.boxRepository.findAll();
	}

	// Other business methods
	public void checkPrincipal(final Box box) {
		Actor actor;
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor.getBoxes().contains(box));
	}

	// Other business methods

	public void createSystemFolders(final Actor actor) {

		Box inbox;
		Box outbox;
		Box trashbox;
		Box notificationbox;
		Box spambox;
		final Collection<Box> boxes = new ArrayList<Box>();

		inbox = new Box();
		inbox.setSystemBox(true);
		inbox.setName("in box");
		inbox.setMessages(new ArrayList<Message>());
		inbox.setActor(actor);
		// folders.add(inbox);
		// folderRepository.save(inbox);

		outbox = new Box();
		outbox.setSystemBox(true);
		outbox.setName("out box");
		outbox.setMessages(new ArrayList<Message>());
		outbox.setActor(actor);
		// folders.add(outbox);
		// folderRepository.save(outbox);

		trashbox = new Box();
		trashbox.setSystemBox(true);
		trashbox.setName("trash box");
		trashbox.setMessages(new ArrayList<Message>());
		trashbox.setActor(actor);
		// folders.add(trashbox);
		// folderRepository.save(trashbox);

		notificationbox = new Box();
		notificationbox.setSystemBox(true);
		notificationbox.setName("notification box");
		notificationbox.setMessages(new ArrayList<Message>());
		notificationbox.setActor(actor);
		// folders.add(notificationbox);
		// folderRepository.save(notificationbox);

		spambox = new Box();
		spambox.setSystemBox(true);
		spambox.setName("spam box");
		spambox.setMessages(new ArrayList<Message>());
		spambox.setActor(actor);
		// folders.add(spambox);
		// folderRepository.save(spambox);

		// folderRepository.save(folders);
		final Box savedinbox = this.boxRepository.save(inbox);
		final Box savedoutbox = this.boxRepository.save(outbox);
		final Box savedtrashbox = this.boxRepository.save(trashbox);
		final Box savednotificationbox = this.boxRepository.save(notificationbox);
		final Box savedspambox = this.boxRepository.save(spambox);

		boxes.add(savedinbox);
		boxes.add(savedoutbox);
		boxes.add(savedtrashbox);
		boxes.add(savednotificationbox);
		boxes.add(savedspambox);

		actor.setBoxes(boxes);

		// folderRepository.save(folders);

		// managerService.save((Manager)actor);

	}
	public Box getOutBoxFolderFromActorId(final int id) {
		return this.boxRepository.getOutBoxFolderFromActorId(id);
	}

	public Box getInBoxFolderFromActorId(final int id) {
		return this.boxRepository.getInBoxFolderFromActorId(id);
	}

	public Box getSpamBoxFolderFromActorId(final int id) {
		return this.boxRepository.getSpamBoxFolderFromActorId(id);
	}

	public Box getTrashBoxFolderFromActorId(final int id) {
		return this.boxRepository.getTrashBoxFolderFromActorId(id);
	}

	public Box getNotificationBoxFolderFromActorId(final int id) {
		return this.boxRepository.getNotificationBoxFolderFromActorId(id);
	}

	public Collection<Box> getFirstLevelFoldersFromActorId(final int actorId) {
		return this.boxRepository.getFirstLevelFoldersFromActorId(actorId);
	}

	public Box getFolderFromMessageId(final int messageId) {
		return this.boxRepository.getFolderFromMessageId(messageId);
	}

	public Collection<Box> getChildBoxes(final int folderId) {
		return this.boxRepository.getChildBoxes(folderId);
	}

	// public Folder getFolderFromMessageAndActorId(int messageId,int actorId) {
	// return
	// folderRepository.getFolderFromMessageAndActorId(messageId,actorId);
	// }

	// public Folder getFolderFromMessageAndActorId(int messageId, int actorId){
	// return folderRepository.getFolderFromMessageAndActorId(messageId,
	// actorId);
	// }

}
