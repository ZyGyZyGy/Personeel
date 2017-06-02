package be.vdab.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Jobtitel;
import be.vdab.entities.Werknemer;
import be.vdab.services.JobtitelService;
import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/werknemers")
@SessionAttributes("werknemer")
public class WerknemerController {

    private static final String WERKNEMER_VIEW = "werknemers/werknemershierarchie";
    private static final String OPSLAG_VIEW = "werknemers/opslag";
    private static final String JOBTITELS_VIEW = "werknemers/jobtitels";
    private static final String REDIRECT_URL_NA_WIJZIGEN = 
	    "redirect:/werknemers/werknemershierarchie/{id}";
    private static final String REDIRECT_URL_NA_LOCKING_EXCEPTION = 
	    "redirect:/werknemers/werknemershierarchie/{id}";
    
    private final WerknemerService werknemerService;
    private final JobtitelService jobtitelService;
    
    WerknemerController(WerknemerService werknemerService, JobtitelService jobtitelService) {
	this.werknemerService = werknemerService;
	this.jobtitelService = jobtitelService;
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
	ModelAndView modelAndView = new ModelAndView(WERKNEMER_VIEW);
	if (werknemer != null) {
	    modelAndView.addObject(werknemer);
	}
	return modelAndView;
    }
    
    @GetMapping("{werknemer}/opslag")
    ModelAndView toonfOpslagForm(@PathVariable Werknemer werknemer) {
	ModelAndView modelAndView = new ModelAndView(OPSLAG_VIEW);
	if (werknemer != null) {
	    modelAndView.addObject(werknemer);
	}
	return modelAndView;
    }   
    
    @PostMapping("{werknemer}/opslag")
    String updateSalaris(@Valid Werknemer werknemer, BindingResult bindingResult,
	    RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
	if (bindingResult.hasErrors()) {
	    return OPSLAG_VIEW;
	}
	long id = werknemer.getId();
	BigDecimal opslag = werknemer.getOpslag();
	try {
	    werknemerService.geefOpslag(werknemer, opslag);
	    redirectAttributes
	    	.addAttribute("id", id)
	    	.addAttribute("update", true);
	    sessionStatus.setComplete();
	    return REDIRECT_URL_NA_WIJZIGEN;
	} catch (ObjectOptimisticLockingFailureException ex) {
	    redirectAttributes
	    	.addAttribute("id", id)
	    	.addAttribute("optimisticlockingexception", true);
	    sessionStatus.setComplete();
	    return REDIRECT_URL_NA_LOCKING_EXCEPTION;
	}
    }
    
    @GetMapping("jobtitels")
    ModelAndView toonJobtitels() {
	return new ModelAndView(JOBTITELS_VIEW, "jobtitels", jobtitelService.findAll());
    }
    
    @GetMapping("jobtitels/{jobtitel}")
    ModelAndView findAlleWerknemersMetJobtitel(@PathVariable Jobtitel jobtitel) {
	ModelAndView modelAndView = new ModelAndView(JOBTITELS_VIEW);
	modelAndView
		.addObject("jobtitels", jobtitelService.findAll())
		.addObject("aangeklikteJobtitel", jobtitel);
	List<Werknemer> werknemers = werknemerService.findByJobtitel(jobtitel.getId());
	if (!werknemers.isEmpty()) {
	    modelAndView.addObject("werknemers", werknemers);
	} else {
	    modelAndView.addObject("fout", "Geen werknemers gevonden");
	}
	return modelAndView;
    }
    
}
