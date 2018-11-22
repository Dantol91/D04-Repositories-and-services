
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Box;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class BoxServiceTest extends AbstractTest {

	// Service under test
	@Autowired
	private BoxService	boxService;


	@Test
	public void testCreateFolder() {
		this.authenticate("manager1");
		Box b = null;
		b = this.boxService.create();
		Assert.notNull(b);
		this.authenticate(null);
	}

	@Test
	public void testSaveFolder() {
		this.authenticate("handyWorker1");
		final Box box;
		Box saved;
		final Collection<Box> boxes;
		box = this.boxService.create();
		box.setName("box-1");
		saved = this.boxService.save(box);
		//		boxes = this.boxService.findAll();
		//		Assert.isTrue(boxes.contains(saved));
		this.authenticate(null);
	}

	@Test
	public void testDeleteBox() {
		this.authenticate("manager1");
		final Box box;
		Box saved;
		final Collection<Box> boxes;
		box = this.boxService.create();
		box.setName("box-1");
		saved = this.boxService.save(box);
		this.boxService.delete(saved);
		//		boxes = boxService.findAll();
		//		Assert.isTrue(!boxes.contains(saved));

	}

	//
	// @Test
	// public void createSaveDelete() {
	// authenticate("manager1");
	// Folder f, fSaved;
	// Collection<Message> messages = new ArrayList<>();
	// f = folderService.create();
	// Assert.notNull(f);
	//
	// // Probamos save
	// f.setName("name");
	// f.setMessages(messages);
	// fSaved = folderService.save(f);
	// Integer fSavedId = fSaved.getId();
	// Assert.isTrue(folderService.findOne(fSavedId).equals(fSaved));
	//
	// // Probamos delete
	// folderService.delete(fSaved);
	// Folder f2 = folderService.findOne(fSavedId);
	// Assert.isTrue(folderService.findOne(fSavedId)==null);
	// }

}
