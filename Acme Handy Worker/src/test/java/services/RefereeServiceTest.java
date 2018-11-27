
package services;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RefereeServiceTest extends AbstractTest {

	@Autowired
	private RefereeService		refereeService;

	@Autowired
	private SponsorshipService	sponsorshipService;

	//	@Autowired
	//	private FixUpTaskService	fixUpTaskService;

	@Autowired
	private CreditCardService	creditcardService;

	/*
	 * @Test
	 * public void createAndSaveSponsorship() {
	 * this.authenticate("sponsor1");
	 * 
	 * final Sponsorship sp = this.sponsorshipService.create();
	 * sp.setBannerURL("http://marca.com");
	 * 
	 * // sp.setFixUpTask(this.fixUpTaskService.findOne(2534));
	 * 
	 * final Collection<CreditCard> cred = this.creditcardService.findAll();
	 * final List<CreditCard> spp = new ArrayList<CreditCard>(cred);
	 * final CreditCard sps = spp.get(0);
	 * sp.setCreditCard(this.creditcardService.findOne(sps.getId()));
	 * final List<Sponsor> sponsors = new ArrayList<Sponsor>(this.refereeService.findAll());
	 * sp.setSponsor(sponsors.get(0));
	 * 
	 * this.sponsorshipService.save(sp);
	 * 
	 * }
	 * 
	 * @Test
	 * public void searchSponsorship() {
	 * 
	 * final Collection<Sponsorship> sp = this.sponsorshipService.findAll();
	 * final List<Sponsorship> spp = new ArrayList<Sponsorship>(sp);
	 * final Sponsorship sps = spp.get(0);
	 * this.sponsorshipService.findOne(sps.getId());
	 * 
	 * }
	 * 
	 * @Test
	 * public void deleteSponsorship() {
	 * final Collection<Sponsorship> sp = this.sponsorshipService.findAll();
	 * final List<Sponsorship> spp = new ArrayList<Sponsorship>(sp);
	 * final Sponsorship sps = spp.get(0);
	 * this.sponsorshipService.delete(sps);
	 * 
	 * final Collection<Sponsorship> collafter = this.sponsorshipService.findAll();
	 * Assert.isTrue(!collafter.contains(sps));
	 * 
	 * }
	 */
}