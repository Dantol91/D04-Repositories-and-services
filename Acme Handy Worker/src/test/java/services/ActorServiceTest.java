
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;
import domain.Actor;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private UserAccountService	userAccountService;


	@Test
	public void testgetSuspiciousActors() {
		final Collection<Actor> suspiciusActors = this.actorService.getSuspiciousActors(false);
		Assert.notEmpty(suspiciusActors);
	}
	@Test
	public void testFindByPrincipal() {
		this.authenticate("auditor1");
		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
	}
	@Test
	public void testFindByUserAccount() {
		final List<UserAccount> userAccounts = (List<UserAccount>) this.userAccountService.findAll();
		final UserAccount userAccount = userAccounts.get(0);
		final Actor user = this.actorService.findByUserAccount(userAccount);
		Assert.notNull(user);
	}
	@Test
	public void testFindByUserAccountId() {
		final List<UserAccount> userAccounts = (List<UserAccount>) this.userAccountService.findAll();
		final UserAccount userAccount = userAccounts.get(0);
		final int id = userAccount.getId();
		final Actor user = this.actorService.findByUserAccountId(id);
		Assert.notNull(user);
	}
	@Test
	public void testGetType() {
		final List<UserAccount> userAccounts = (List<UserAccount>) this.userAccountService.findAll();
		final UserAccount userAccount = userAccounts.get(0);

		final String type = this.actorService.getType(userAccount);
		Assert.notNull(type);

	}

	/*
	 * @Test
	 * public void testBan() {
	 * final Actor pepon = (Actor) this.actorService.findAll().toArray()[0];
	 * pepon.setSuspicious(true); //debe ser sospechoso para poder ser baneado
	 * this.actorService.banActor(pepon);
	 * }
	 * 
	 * 
	 * @Test
	 * public void testUnBan() {
	 * final Actor pepon = (Actor) this.actorService.findAll().toArray()[0];
	 * pepon.getUserAccount().setEnabled(false);
	 * this.actorService.unbanActor(pepon);
	 * }
	 */

	@Test
	public void updatePhone() {

		final Actor pepon = (Actor) this.actorService.findAll().toArray()[0];
		System.out.println(pepon.getPhone());
		pepon.setPhone("+100 (666) 10");
		System.out.println(pepon.getPhone());

	}

	@Test
	public void testCreateActor() {

		final Actor a = this.handyWorkerService.create();
		Assert.notNull(a);

	}

	@Test
	public void testSaveActor() {
		final UserAccount u = new UserAccount();
		final Authority a = new Authority();
		a.setAuthority("ADMIN");
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(a);
		u.setAuthorities(authorities);
		Actor saved;
		Collection<Actor> actors;
		final HandyWorker m = this.handyWorkerService.create();
		m.setName("Name1");
		m.setSurname("Surname1");
		saved = this.actorService.save(m);
		actors = this.actorService.findAll();
		Assert.isTrue(actors.contains(saved));

	}

	@Test
	public void testFindOneActor() {
		Actor saved;
		final HandyWorker m = this.handyWorkerService.create();
		m.setName("Name1");
		m.setSurname("Surname1");
		saved = this.actorService.save(m);
		final int actorId = saved.getId();
		final Actor a = this.actorService.findOne(actorId);
		Assert.isTrue(a.equals(saved));
	}
}
