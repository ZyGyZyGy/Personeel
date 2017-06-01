package be.vdab.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Werknemer;
import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/werknemers")
public class WerknemerController {

    private static final String WERKNEMER_VIEW = "werknemers/werknemershierarchie";
    private static final String OPSLAG_VIEW = "werknemers/opslag";
    private static final String JOBTITELS_VIEW = "werknemers/jobtitels";
    private static final String REDIRECT_URL_NA_WIJZIGEN = 
	    "redirect:/werknemers/werknemershierarchie/{id}";
    private static final String REDIRECT_URL_NA_LOCKING_EXCEPTION = 
	    "redirect:/werknemers/werknemershierarchie/{werknemer}?optimisticlockingexception=true";
    
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
    
    // TODO: eigen annotation
    
    @PostMapping("{werknemer}/opslag")
    String updateSalaris(@Valid Werknemer werknemer, BindingResult bindingResult,
	    RedirectAttributes redirectAttributes) {
	if (bindingResult.hasErrors()) {
	    return OPSLAG_VIEW;
	}
	long id = werknemer.getId();
	try {
	    werknemerService.update(werknemer);
	    redirectAttributes
	    	.addAttribute("id", id)
	    	.addAttribute("update", true);
	    return REDIRECT_URL_NA_WIJZIGEN;
	} catch (ObjectOptimisticLockingFailureException ex) {
	    redirectAttributes
	    	.addAttribute("id", id);
	    return REDIRECT_URL_NA_LOCKING_EXCEPTION;
	}
    }
    
    @GetMapping("jobtitels")
    String toonJobtitels() {
	return JOBTITELS_VIEW;
    }
    
}
