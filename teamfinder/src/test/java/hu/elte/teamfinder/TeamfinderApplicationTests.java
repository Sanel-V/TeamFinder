package hu.elte.teamfinder;

import hu.elte.teamfinder.controllers.AccountController;
import hu.elte.teamfinder.controllers.ProfileController;
import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.repos.ProfileRepository;
import hu.elte.teamfinder.security.PasswordConfig;
import hu.elte.teamfinder.services.AccountModelService;
import hu.elte.teamfinder.services.ProfileModelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class TeamfinderApplicationTests {




	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProfileController profileController;
	@Autowired
	private AccountController accountController;

	@MockBean
	private AccountModelService accountModelService;
	@MockBean
	private ProfileModelService profileModelService;

	@MockBean
	private PasswordEncoder passwordEncoder;
	@MockBean
	private ProfileRepository profileRepository;

	@Test
	public void contextLoads() {
	}

}
