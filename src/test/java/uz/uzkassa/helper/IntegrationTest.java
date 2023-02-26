package uz.uzkassa.helper;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import uz.uzkassa.TaskUzKassaApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dilshodbek Akhmedov, Thu 10:23 AM. 2/26/23
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = TaskUzKassaApplication.class)
@AutoConfigureMockMvc
public @interface IntegrationTest {
}
