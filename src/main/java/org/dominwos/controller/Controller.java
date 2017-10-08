package org.dominwos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Wosin on 08.10.2017.
 */
@RestController
public class Controller {

    @RequestMapping(value = "/liveness")
    public String livenessProbe(){
        return "OK";
    }
}
