package com.algaworks;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatosControle {
	
	private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<>();
	
	static {
		LISTA_CONTATOS.add(new Contato("1", "Maria", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contato("2", "João", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contato("3", "Normandes", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contato("4", "Thiago", "+55 34 00000 0000"));
		LISTA_CONTATOS.add(new Contato("5", "Alexandre", "+55 34 00000 0000"));
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/contatos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("listar");
		
		mv.addObject("contatos", LISTA_CONTATOS);
		
		return mv;
	}
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("formulario");
		mv.addObject("contato", new Contato());
		return mv;
	}	

	@PostMapping("/contatos")
	public ModelAndView salvar(Contato contato) {
		ModelAndView mv = new ModelAndView("redirect:/contatos");
		LISTA_CONTATOS.add(contato);
		return mv;
	}

	@DeleteMapping("/contatos/{id}")
	public ModelAndView remover(@PathVariable String id) {
		for (int i = 0; i < LISTA_CONTATOS.size(); i++) {
			if (LISTA_CONTATOS.get(i).getId().equals(id)) {
				LISTA_CONTATOS.remove(i);
				break;
			}
		}
		return new ModelAndView("redirect:/contatos");
	}
}