package com.prosegur.guvnor.negocio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prosegur.guvnor.negocio.front.Opcion;
import com.prosegur.guvnor.negocio.service.AppService;
import com.prosegur.guvnor.negocio.service.ComboService;
import com.prosegur.guvnor.negocio.service.Entorno;
import com.prosegur.guvnor.negocio.service.Motor;

@Controller
public class AppController {
	
	private static final String TIPO_LISTA_OPCION = "opcion";
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private ComboService comboService;

    @GetMapping(MappingsController.AJAX_COMBO)
	public String ajaxCombo(Model model, @PathVariable String tipo, 
							@RequestParam String entorno, 
							@RequestParam String id, 
			                @RequestParam(required = false) String idAux,
			                @RequestParam(required = false, defaultValue=TIPO_LISTA_OPCION) String tipoLista) throws InstantiationException, IllegalAccessException {
    	List<Opcion> opciones = comboService.getCombo(entorno, tipo, id, idAux);
    	model.addAttribute("opciones", opciones);
    	
    	if (TIPO_LISTA_OPCION.equals(tipoLista)) {
    		return Pagina.AJAX_COMBO.getPagina();
    	} else {
    		return Pagina.AJAX_PAQUETES_HASTA.getPagina();
    	}
	}    
    
    @PostMapping(MappingsController.AJAX_GUARDAR_GUVNOR)
	public String ajaxGuardarGuvnor(Model model, @PathVariable String entorno, 
									@RequestParam("paquetes[]") List<String> paquetes,
									@RequestParam() String asset,
			                        @RequestParam() String codigo) {
    	Map<String, Boolean> resultados = appService.guardarGuvnor(entorno, paquetes, asset, codigo);
    	
    	List<Mensaje> mensajes = new ArrayList<>();
    	for (Map.Entry<String, Boolean> entry : resultados.entrySet()) {
    		String textoMensaje = asset + " " + (entry.getValue() ? "se ha guardado correctamente" : "no se ha podido guardar") + " en " + entry.getKey();
    		Mensaje mensaje = entry.getValue() ? Mensaje.nuevoMensajeOK(textoMensaje) : Mensaje.nuevoMensajeERROR(textoMensaje);
    		mensajes.add(mensaje);
    	}
    	
    	model.addAttribute("mensajes", mensajes);      	
		return Pagina.AJAX_GUARDAR_GUVNOR.getPagina();
	}       
    
    @GetMapping(MappingsController.MODIFICACION_VARIABLE)
    public String modificacionVariable(Model model) {
    	model.addAttribute("motores", Motor.values());
    	model.addAttribute("entornos", Entorno.values());
    	model.addAttribute("tiempoRecargarCache", comboService.getTiempoRecargarCache());
    	model.addAttribute("fechaUltimoCacheo", comboService.getFechaUltimoCacheo());
    	
        return Pagina.MODIFICACION_VARIABLE.getPagina();
    } 
    
}
