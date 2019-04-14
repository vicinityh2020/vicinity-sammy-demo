package gr.optionsnet.vicinity.demo.controller;

import gr.optionsnet.vicinity.demo.component.RestClient;
import gr.optionsnet.vicinity.demo.config.ContextURL;
import gr.optionsnet.vicinity.demo.dto.sammy.Sammy;
import gr.optionsnet.vicinity.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MvcController implements ContextURL {

    private Logger logger = LoggerFactory.getLogger(MvcController.class);

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("${app.refresh}")
    private String appRefresh;
    @Value("${log.refreshInterval}")
    private Integer logRefresh;


    @Autowired
    private RestClient restClient;
    @Autowired
    private DemoService demoService;


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        model.addAttribute("BASE_PATH", contextPath);

        model.addAttribute("REST_PATH", REST_ROOT);
        model.addAttribute("REST_SAMMY", REST_SAMMY);
        model.addAttribute("REST_ITEMS", REST_ITEMS);
        model.addAttribute("REST_LOG", REST_LOG);
        model.addAttribute("REST_ITEM_LOGIN", REST_ITEM_LOGIN);
        model.addAttribute("REST_ITEM_QUERY", REST_ITEM_QUERY);

        model.addAttribute("NAV_HOME", contextPath + "/" + MVC_HOME);
        model.addAttribute("NAV_SAMMY", contextPath + "/" + MVC_SAMMY);
        model.addAttribute("NAV_SYSTEM", contextPath + "/" + MVC_SYSTEM);

        model.addAttribute("APP_REFRESH", appRefresh);
        model.addAttribute("LOG_REFRESH", logRefresh);

    }


    @GetMapping({"/", MVC_HOME})
    public ModelAndView home(Model model) {

        demoService.initEnv();

        return new ModelAndView("public/index", "", model);
    }

    @GetMapping({MVC_SAMMY})
    public ModelAndView sammy(Model model) {
        Sammy sammy = restClient.sammyData().getBody();

        model.addAttribute("sammy", sammy);

        return new ModelAndView("public/sammy", "", model);
    }

    @GetMapping({MVC_SYSTEM})
    public ModelAndView system(Model model) {
        return new ModelAndView("public/system", "", model);
    }

}
