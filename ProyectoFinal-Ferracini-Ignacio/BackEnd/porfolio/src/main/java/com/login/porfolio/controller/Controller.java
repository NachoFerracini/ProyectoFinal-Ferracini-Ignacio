package com.login.porfolio.controller;

import com.login.porfolio.entity.Persona;
import com.login.porfolio.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController 
@CrossOrigin (origins = "http://localhost:4200/")
public class Controller {
    
    @Autowired IPersonaService persoServ;
    
     @GetMapping ("/personas/traer/")
    @ResponseBody
    public List<Persona> getPersona(){
       return persoServ.getPersona();
    }    
    
    
    @PostMapping ("/personas/crear")
    public String createPersona (@RequestBody Persona persona){
        persoServ.savePersona(persona);
        return "La persona fue creada correctamente.";
    }
    
    
    
 
    @DeleteMapping ("/personas/borrar/{id}")
    public String deletePersona (@PathVariable Long id){
        persoServ.deletePersona(id);
        return "La persona fue eliminada correctamente.";
    }
    
   
   
    @PutMapping ("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("img") String nuevoImg){
        Persona persona = persoServ.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        
        persoServ.savePersona(persona);
        return persona;
        
    
}
  
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return persoServ.findPersona((long)1);
    }
    
}
