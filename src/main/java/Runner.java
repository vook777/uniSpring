import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.models.Auditorium;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;
import com.foxminded.univer.spring.dao.GroupDaoSpring;

public class Runner {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		GroupDaoSpring groupDao = context.getBean(GroupDaoSpring.class);
		
		System.out.println(groupDao.getFacultyId(11));

		context.close();
	}
}
