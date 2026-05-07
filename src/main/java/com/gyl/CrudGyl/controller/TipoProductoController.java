package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.request.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.response.TipoProductoResponseDto;
import com.gyl.CrudGyl.service.TipoProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo_producto")

public class TipoProductoController {

    private final TipoProductoService tipoProductoService;

    public TipoProductoController (TipoProductoService tipoProductoService) {
        this.tipoProductoService = tipoProductoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProductoResponseDto crear(@Valid @RequestBody TipoProductoRequestDto dto) {
        return tipoProductoService.crear(dto);
    }

    @GetMapping
    public List<TipoProductoResponseDto> listar(){
        return tipoProductoService.listar();
    }

    @GetMapping("/buscar/{nombre}")
    public List<TipoProductoResponseDto> busquedaNombre(@PathVariable String nombre) { return tipoProductoService.busquedaNombre(nombre); }

    @PutMapping("/eliminar/{id}")
    public TipoProductoResponseDto eliminar (@PathVariable Long id) {
        return tipoProductoService.eliminar(id);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDto actualizar (@PathVariable  Long id, @RequestBody TipoProductoRequestDto dto) { return tipoProductoService.actualizar(id, dto);}

    @GetMapping("/{id}")
    public TipoProductoResponseDto buscarPorId (@PathVariable Long id){return tipoProductoService.buscarPorId(id);}

    @GetMapping("/buscarvigente/{vigente}")
    public List<TipoProductoResponseDto> busquedaVigente (@PathVariable Boolean vigente) {return tipoProductoService.busquedaVigente(vigente);}

}
