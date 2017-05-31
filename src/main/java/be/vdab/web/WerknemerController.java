package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/werknemers")
public class WerknemerController {

    private static final String WERKNEMERS_VIEW = "werknemers/werknemershierarchie";
    private static final String JOBTITELS_VIEW = "werknemers/jobtitels";
    
    @GetMapping("werknemershierarchie")
    String toonWerknemers() {
	return WERKNEMERS_VIEW;
    }
    
    @GetMapping("jobtitels")
    String toonJobtitels() {
	return JOBTITELS_VIEW;
    }
    
}
