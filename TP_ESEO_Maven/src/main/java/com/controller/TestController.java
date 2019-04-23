package com.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.dao.*;
import com.dto.*;

@RestController
public class TestController {
	@RequestMapping(value="/villeget", method=RequestMethod.GET)
	@ResponseBody
	public Ville[] get(@RequestParam(required = false, value="filtre") String filtre, @RequestParam(required = false, value="value"
			) String value) {
		VilleFranceDAO villeFranceDAO= new VilleFranceDAO();
		
		if(filtre!=null) {
			return villeFranceDAO.gestionFiltre(filtre,value);
		}else {
			return villeFranceDAO.listeVilles();
		}
		
	}
	
	@RequestMapping(value="/villepost", method=RequestMethod.POST)
	@ResponseBody
	public String post(@RequestBody Ville ville){
		
		VilleFranceDAO villeFranceDAO= new VilleFranceDAO();
		
		villeFranceDAO.creationVille(ville);
		return "La ville a été ajoutée";
		
	}
	
	@DeleteMapping(value="/villedelete/{codeCommune}")
	@ResponseBody
	public String delete(@PathVariable String codeCommune){
		
		VilleFranceDAO villeFranceDAO= new VilleFranceDAO();
		
		villeFranceDAO.supprimerVille(codeCommune);
		return "La ville a été supprimée";
		
	}
	
	@PutMapping(value="/villeput/{codeCommune}")
	@ResponseBody
	public String put(@RequestBody Ville ville, @PathVariable String codeCommune){
		
		VilleFranceDAO villeFranceDAO= new VilleFranceDAO();
		
		villeFranceDAO.update(ville, codeCommune);
		return "La ville a été modifiée";
		
	}
	
	
}
