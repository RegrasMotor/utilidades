package com.prosegur.guvnor.negocio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping(MappingsController.BASE)
    public String base() {
        return Pagina.INICIO.getPagina();
    }

    @GetMapping(MappingsController.INICIO)
    public String inicio() {
        return Pagina.INICIO.getPagina();
    }
    
    @GetMapping(MappingsController.LOGIN)
    public String login() {
        return Pagina.LOGIN.getPagina();
    }

    @GetMapping(MappingsController.ERROR_403)
    public String error403() {
        return Pagina.ERROR_403.getPagina();
    }

}
