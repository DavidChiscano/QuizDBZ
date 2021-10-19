package com.quiz.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.modelo.Puntos;
import com.quiz.persistencia.Usuario;


@Controller
public class ControladorPreguntas {
	
	private Usuario usuario;
	@Autowired
	private Puntos puntosModelo;
	
	//VARIABLES 
	public static int puntos = 0;
	public static String nombreUsuario = "";
	public static String color = "";
	public static String valor = "";
	public static String opc = "";
	public static String select = "";
	public static String fuerte = "";
	public static String meta = "";
	public static String crees = "";
	public static String personaje = "";
	
	
	//GET METHODS	
	@RequestMapping(value = "/DBZQuiz", method = RequestMethod.GET)
	public String getQuiz() {
		return "DBZQuiz";
	}
	@RequestMapping(value = "/Pregunta1", method = RequestMethod.GET)
	public String getPreg1() {
		return "Pregunta1";
	}

	@RequestMapping(value = "/Pregunta2", method = RequestMethod.GET)
	public String getPreg2() {
		return "Pregunta2";
	}
	
	@RequestMapping(value = "/Pregunta3", method = RequestMethod.GET)
	public String getPreg3() {
		return "Pregunta3";
	}
	
	@RequestMapping(value = "/Pregunta4", method = RequestMethod.GET)
	public String getPreg4() {
		return "Pregunta4";
	}
	
	@RequestMapping(value = "/Pregunta5", method = RequestMethod.GET)
	public String getPreg5() {
		return "Pregunta5";
	}
	
	@RequestMapping(value = "/Pregunta6", method = RequestMethod.GET)
	public String getPreg6() {
		return "Pregunta6";
	}
	
	@RequestMapping(value = "/Pregunta7", method = RequestMethod.GET)
	public String getPreg7() {
		return "Pregunta7";
	}
	
	
	@GetMapping("/Resultado")
	public String process(Model model, HttpSession session, HttpServletRequest request) {
	
	usuario = new Usuario(null, 0); //Constructor inicializado
	
	nombreUsuario = (String)session.getAttribute("nombreUsuario");
    color = (String)session.getAttribute("color");
    valor = (String)session.getAttribute("valor");
    opc = (String)session.getAttribute("opc");
    select = (String)session.getAttribute("select");
    fuerte = (String)session.getAttribute("fuerte");
    meta = (String)session.getAttribute("meta");
    crees = (String)session.getAttribute("crees");
    
    puntos = calcularPuntos();	//Calculamos los puntos obtenidoss
    
    usuario.setNombre(nombreUsuario);
    usuario.setPuntos(puntos);
    puntosModelo.setPuntos(usuario);
    
    request.getSession().setAttribute("nombreUsuario", nombreUsuario);
    request.getSession().setAttribute("puntos", puntos);
    
    model.addAttribute("nombreUsuario", nombreUsuario);
    model.addAttribute("puntos", puntos);
    
    List<Usuario> puntuaciones = puntosModelo.getPuntos();
    
    model.addAttribute("puntuaciones", puntuaciones);
    
    
    if(calcularPuntos() == 50) {
    	personaje = "img/goku.jpg";
    }else if(calcularPuntos() == 40) {
    	personaje = "img/vegeta.jpg";
    }else if(calcularPuntos() == 30) {
    	personaje = "img/bulma.jpg";
    }else if(calcularPuntos() == 20) {
    	personaje = "img/freezer.jpg";
    }else if(calcularPuntos() == 10) {
    	personaje = "img/yamcha.jpg";
    }
    
    model.addAttribute("personaje", personaje);
   
    
    request.getSession().invalidate();//Terminamos la sesion una vez obtenido el resultado
	return "Resultado";
	}
	//POST METHODS
	@RequestMapping(value = "/DBZQuiz", method = RequestMethod.POST)
	public String quiz(@RequestParam String nombreUsuario, HttpServletRequest request) {
		request.getSession().setAttribute("nombreUsuario", nombreUsuario);
		return "redirect:/Pregunta1";
	}
	
	@RequestMapping(value = "/Pregunta1", method = RequestMethod.POST)
	public String preg1(@RequestParam String color, HttpServletRequest request) {
		request.getSession().setAttribute("color", color);
		return "redirect:/Pregunta2";
	}

	@RequestMapping(value = "/Pregunta2", method = RequestMethod.POST)
	public String preg2(@RequestParam String valor, HttpServletRequest request) {
		request.getSession().setAttribute("valor", valor);
		return "redirect:/Pregunta3";
	}
	
	@RequestMapping(value = "/Pregunta3", method = RequestMethod.POST)
	public String preg3(@RequestParam String opc, HttpServletRequest request) {
		request.getSession().setAttribute("opc", opc);
		return "redirect:/Pregunta4";
	}
	
	@RequestMapping(value = "/Pregunta4", method = RequestMethod.POST)
	public String preg4(@RequestParam String select, HttpServletRequest request) {
		request.getSession().setAttribute("select", select);
		return "redirect:/Pregunta5";
	}
	
	@RequestMapping(value = "/Pregunta5", method = RequestMethod.POST)
	public String preg5(@RequestParam String fuerte, HttpServletRequest request) {
		request.getSession().setAttribute("fuerte", fuerte);
		return "redirect:/Pregunta6";
	}
	
	@RequestMapping(value = "/Pregunta6", method = RequestMethod.POST)
	public String preg6(@RequestParam String meta, HttpServletRequest request) {
		request.getSession().setAttribute("meta", meta);
		return "redirect:/Pregunta7";
	}
	
	@RequestMapping(value = "/Pregunta7", method = RequestMethod.POST)
	public String preg7(@RequestParam String crees, HttpServletRequest request) {
		request.getSession().setAttribute("crees", crees);
		return "redirect:/Resultado";
	}
    //Funcion para calcular los puntos
	public static int calcularPuntos() {
		if(color.equals("Naranja") && valor.equals("Amistad") && select.equals("Soy Demasiado compasivo con los demas") && fuerte.equals("Fuerza") && meta.contains("Proteger") && crees.equals("goku")) {
	    	puntos = 50; //GOKU
	    }else if(color.equals("Azul") && valor.equals("Disciplina") && select.equals("Pienso en mi por encima del resto") && fuerte.equals("Perseverancia") && meta.contains("Demostrar") && crees.equals("vegeta")) {
	    	puntos = 40;  //VEGETA
	    }else if(color.equals("Rosa") && valor.equals("Familia") && select.equals("Me paso de sobreprotector con mi entorno") && fuerte.equals("Inteligencia") && meta.contains("Formar una familia") && crees.equals("bulma")) {
	    	puntos = 30; //BULMA
	    }else if(color.equals("Morado") && valor.equals("Poder") && select.equals("Deseo el mal a los demás") && fuerte.equals("Ninguna de las anteriores") && meta.contains("Dominar el mundo") && crees.equals("freezer")) {
	    	puntos = 20; //FREEZER
	    }else {
	    	puntos = 10; //YAMCHA
	    }
	    return puntos;
	}
}
