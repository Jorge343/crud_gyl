package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.ClienteRequestDto;
import com.gyl.CrudGyl.dto.ClienteResponseDto;
import com.gyl.CrudGyl.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto crear(@Valid @RequestBody ClienteRequestDto dto) {
        return clienteService.crear(dto);
    }

    @GetMapping
    public List<ClienteResponseDto> listar(){
        return clienteService.listar();
    }

    @GetMapping("/buscarA/{apellido}")
    public List<ClienteResponseDto> busquedaApellido(@PathVariable String apellido) {
        return clienteService.busquedaApellido(apellido);
    }

    @PutMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar (@PathVariable Long id) {
        clienteService.eliminar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto actualizar (@PathVariable Long id, @Valid @RequestBody ClienteRequestDto dto) {
        return clienteService.actualizar(id, dto);
    }

    @GetMapping("/{id}")
    public ClienteResponseDto buscarPorId (@PathVariable Long id){
        return clienteService.buscarPorId(id);
    }

    @GetMapping("/vigencia/{vigente}")
    public List<ClienteResponseDto> busquedaNoVigente (@PathVariable Boolean vigente) {
        return clienteService.busquedaNoVigente(vigente);
    }

    @GetMapping("/buscarN/{nombre}")
    public List<ClienteResponseDto> busquedaNombre (@PathVariable String nombre) {
        return clienteService.busquedaNombre(nombre);
    }
}