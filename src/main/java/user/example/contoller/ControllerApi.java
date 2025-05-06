
package user.example.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/contact")
public class ControllerApi {

	@GetMapping("/view")
	public String view ()
	{
		return "It is private Page";
	}
	
	
	@GetMapping("/public")
	public String publicview ()
	{
		return "It is public  Page";
	}
	

	
}
