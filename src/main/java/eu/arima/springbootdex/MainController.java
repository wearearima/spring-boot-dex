package eu.arima.springbootdex;

import java.security.Principal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	private OAuth2AuthorizedClientService authorizedClientService;
	
	public MainController(OAuth2AuthorizedClientService authorizedClientService) {
		this.authorizedClientService = authorizedClientService;
	}

   @GetMapping("/")
   String home(Principal user) {
       return "Hello " + user.getName();
   }
   
   @GetMapping("/authorizedClient")
   OAuth2AuthorizedClient authorizedClient(OAuth2AuthenticationToken authentication) {
	   LOGGER.info("Authorized Client Registration Id: {}", authentication.getAuthorizedClientRegistrationId());
	   LOGGER.info("Authentication name: {}", authentication.getName());
	   return this.authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
   }
   
   @GetMapping("/oidcinfo")
   Map<String, Object> moreInfo(Principal user) {
	   return ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes();
   }
   
}