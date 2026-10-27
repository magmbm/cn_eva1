package com.docente.proyectoejemplo.usuarios;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docente.proyectoejemplo.aws.UsuarioLambdaService;
import com.docente.proyectoejemplo.usuarios.entity.Usuario;


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

    @PreAuthorize ("hasAnyRole('Admin', 'Owner')")
    @GetMapping (value = "/registrado")
    public ResponseEntity<UsuarioRegistradoResponse> usuarioRegistrado(@RequestHeader("email_usuario") String email) {
        Map<String, String> emailMap= new HashMap<String, String>();
        emailMap.put("email_usuario", email);
        String usuarioRegistrado= usuarioLambdaService.invokeCrud("GET", emailMap, null);
        System.out.println("Respuesta del invokeCrud REGISTRADO");
        System.out.println(usuarioRegistrado);
        System.out.println(email);
        UsuarioRegistradoResponse respuesta= new UsuarioRegistradoResponse(Boolean.valueOf(usuarioRegistrado));
        return ResponseEntity.status(200).body(respuesta);
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet', 'Owner')")
    @PostMapping ("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo= new Usuario(usuario.getNumrun_usuario(), usuario.getNombre_usuario(), usuario.getPapellido_usuario(), usuario.getSapellido_usuario(), usuario.getEmail_usuario(), usuario.getDvrun_usuario());
        System.out.println("Viendo la peticion");
        System.out.println(nuevo);
        String respuestaJson= usuarioLambdaService.invokeCrud("POST", null, nuevo);
        System.out.println(respuestaJson);
        return ResponseEntity.status(201).body(respuestaJson);
    }


}
