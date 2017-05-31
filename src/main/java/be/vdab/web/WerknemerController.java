package be.vdab.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Werknemer;
import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/werknemers")
public class WerknemerController {

    private static final String WERKNEMER_VIEW = "werknemers/werknemershierarchie";
    private static final String JOBTITELS_VIEW = "werknemers/jobtitels";
    
    private final WerknemerService werknemerService;
    
    WerknemerController(WerknemerService werknemerService) {
	this.werknemerService = werknemerService;
    }
    
    @GetMapping("werknemershierarchie")
    ModelAndView findDirecteur() {
	ModelAndView modelAndView = new ModelAndView(WERKNEMER_VIEW);
	Optional<Werknemer> optionalWerknemer = werknemerService.findWerknemerMetHoogsteTitel();
	if (optionalWerknemer.isPresent()) {
	    modelAndView.addObject(optionalWerknemer.get());
	}
	return modelAndView;
    }
    
    @GetMapping("werknemershierarchie/{werknemer}")
    ModelAndView findWerknemerById(@PathVariable Werknemer werknemer) {
	// TODO afwerken
	return null;
    }
    
    @GetMapping("jobtitels")
    String toonJobtitels() {
	return JOBTITELS_VIEW;
    }
    
}
