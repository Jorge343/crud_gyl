package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.response.ProductoResponseDto;
import com.gyl.CrudGyl.dto.request.ProductoRequestDto;
import com.gyl.CrudGyl.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto crear(@Valid @RequestBody ProductoRequestDto dto) {
        return productoService.crear(dto);
    }

    @GetMapping
    public List<ProductoResponseDto> listar(){
        return productoService.listar();
    }

    @GetMapping("/buscar/{nombre}")
    public List<ProductoResponseDto> busquedaNombre(@PathVariable String nombre) {
        return productoService.busquedaNombre(nombre);
    }

    @PatchMapping("/{id}")
    public void eliminar (@PathVariable Long id) {
        productoService.eliminar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoResponseDto actualizar (@PathVariable  Long id, @RequestBody ProductoRequestDto dto) {
        return productoService.actualizar(id, dto);
    }

    @GetMapping("/{id}")
    public ProductoResponseDto buscarPorId (@PathVariable Long id){
        return productoService.buscarPorId(id);
    }

    @GetMapping("/buscarvigente/{vigente}")
    public List<ProductoResponseDto> busquedaVigente (@PathVariable Boolean vigente) {
        return productoService.busquedaVigente(vigente);
    }
}
