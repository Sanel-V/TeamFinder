package hu.elte.teamfinder;

import hu.elte.teamfinder.controllers.AccountController;
import hu.elte.teamfinder.controllers.ProfileController;
import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.ProfileModel;
import hu.elte.teamfinder.repos.AccountRepository;
import hu.elte.teamfinder.repos.ProfileRepository;
import hu.elte.teamfinder.security.PasswordConfig;
import hu.elte.teamfinder.services.AccountModelService;
import hu.elte.teamfinder.services.ProfileModelService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
@AutoConfigureMockMvc
public class TeamfinderMvcTests
{

	/*
		We're missing unit tests and basically only have integration tests here
		//TODO: write unit tests to test Repos, Services and controllers separately

	 */
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
	@MockBean
	private AccountRepository accountRepository;

	/*
	@BeforeEach
	public void beforeAll()
	{

		profileModelService.createProfile(new ProfileModel(1, "anna", "smith", 25));
		profileModelService.createProfile(new ProfileModel(2, "bob", "smith", 25));
		profileModelService.createProfile(new ProfileModel(3, "charlie", "smith", 25));
		profileModelService.createProfile(new ProfileModel(4, "dan", "smith", 25));

		accountController.addAccount(new AccountModel(1,"email","pw"));
		accountController.addAccount(new AccountModel(2,"email1","pw"));
		accountController.addAccount(new AccountModel(3,"email2","pw"));

	}
*/
	@Test
	public void contextLoads() {

	}


	/*
	@Test
	public void profileRepoSave()
	{
		//when
		ProfileModel prof = profileModelService.createProfile(new ProfileModel(5,"ethan","klein",30));

		System.out.println(prof.getAccountId());

		//then
		Assert.assertEquals(5, profileRepository.findAll().size());
	}*/

	@Test
	public void return404FromRoot() throws Exception
	{
		MvcResult result = this.mockMvc.perform(get("/")).andReturn();//.andDo(print()).andExpect(status().isNotFound()).andExpect(response().string(containsString("\"error\": \"Not Found\"")));
		int status = result.getResponse().getStatus();
		Assert.assertEquals(404, status);
	}

	@Test
	public void getAllAccountsFromEmptyRepo() throws Exception
	{
		MvcResult result = this.mockMvc.perform(get("/account/all")).andReturn();

		Assert.assertEquals(0, result.getResponse().getContentLength());
	}


	@Test
	public void postOneAccount() throws Exception
	{
		MvcResult result = this.mockMvc.perform(post("/account/add").content( "    {\n" +
				"    \"accountId\": 7,\n" +
				"    \"email\": \"asdsa\",\n" +
				"    \"password\": \"25\",\n" +
				"    \"role\": \"STANDARD\",\n" +
				"    \"enabled\": true,\n" +
				"    \"accountNonExpired\": true,\n" +
				"    \"credentialsNonExpired\": true,\n" +
				"    \"accountNonLocked\": true\n" +
				"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());

	}
/*
	@Test
	public void getAccountById() throws Exception
	{
		this.mockMvc.perform(post("/account/add").content( "    {\n" +
				"    \"accountId\": 7,\n" +
				"    \"email\": \"bobby\",\n" +
				"    \"password\": \"25\",\n" +
				"    \"role\": \"STANDARD\",\n" +
				"    \"enabled\": true,\n" +
				"    \"accountNonExpired\": true,\n" +
				"    \"credentialsNonExpired\": true,\n" +
				"    \"accountNonLocked\": true\n" +
				"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		/*MvcResult result = this.mockMvc.perform(get("/account/get/7")).;
		Assert.assertEquals(200, result.getResponse().getStatus());
		String resp = result.getResponse().getContentAsString();
		Assert.assertEquals(true, result.getResponse().getContentAsString().contains("bobby"));
		this.mockMvc.perform(get("/account/get/7")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("bobby")));

	}
*/
}
