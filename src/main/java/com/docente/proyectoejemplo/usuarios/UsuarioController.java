package com.docente.proyectoejemplo.usuarios;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.docente.proyectoejemplo.lambda.UsuarioLambdaService;


@RestController 
@RequestMapping ("api/usuarios")
public class UsuarioController {
    
    private final UsuarioLambdaService usuarioLambdaService;

    public UsuarioController(UsuarioLambdaService usuarioLambdaService) {
        this.usuarioLambdaService= usuarioLambdaService;
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @GetMapping (value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> recuperarUsuarioDesdeLambda() {
        String respuestaJson = usuarioLambdaService.invokeCrud("GET", null, null);
        return ResponseEntity.ok(respuestaJson);
    }

}
