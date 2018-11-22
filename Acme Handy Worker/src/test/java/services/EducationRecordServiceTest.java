
package services;

import java.sql.Date;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.EducationRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EducationRecordServiceTest extends AbstractTest {

	@Autowired
	private EducationRecordService	educationRecordService;


	@Test
	public void createSaveDelete() {
		this.authenticate("ranger2");

		EducationRecord edRecord, edSaved;
		Collection<EducationRecord> eBefore, eAfter;

		edRecord = this.educationRecordService.create();
		Assert.notNull(edRecord);

		//Probamos save
		edRecord.setDiplomaTitle("diploma");
		edRecord.setStartDate(Date.valueOf("2014-08-08"));
		edSaved = this.educationRecordService.save(edRecord);

		eBefore = this.educationRecordService.findAll();
		Assert.isTrue(eBefore.contains(edSaved));

		//Probamos delete
		this.educationRecordService.delete(edSaved);

		eAfter = this.educationRecordService.findAll();

		Assert.isTrue(!eAfter.contains(edSaved));
	}

}
