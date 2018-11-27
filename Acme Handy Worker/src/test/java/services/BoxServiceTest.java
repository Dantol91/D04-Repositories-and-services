
package services;

import java.util.ArrayList;
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
import domain.Message;

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
		this.authenticate("customer1");
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
		boxes = this.boxService.findAll();
		Assert.isTrue(boxes.contains(saved));
		this.authenticate(null);
	}

	@Test
	public void testDeleteBox() {
		this.authenticate("customer1");
		final Box box;
		Box saved;
		final Collection<Box> boxes;
		box = this.boxService.create();
		box.setName("box-1");
		saved = this.boxService.save(box);
		this.boxService.delete(saved);
		boxes = this.boxService.findAll();
		Assert.isTrue(!boxes.contains(saved));

	}

	@Test
	public void createSaveDelete() {
		this.authenticate("customer1");
		Box b;
		final Box boxSaved;
		final Collection<Message> messages = new ArrayList<>();
		b = this.boxService.create();
		Assert.notNull(b);

		// Probamos save
		b.setName("name");
		b.setMessages(messages);
		boxSaved = this.boxService.save(b);
		final Integer boxSavedId = boxSaved.getId();
		Assert.isTrue(this.boxService.findOne(boxSavedId).equals(boxSaved));

		// Probamos delete
		this.boxService.delete(boxSaved);
		//	final Box b2 = this.boxService.findOne(boxSavedId);
		Assert.isTrue(this.boxService.findOne(boxSavedId) == null);
	}

}
