package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.ProductResponseDto;
import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.service.ProductoService;
import jakarta.persistence.SqlResultSetMapping;
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
    public ProductResponseDto crear(@Valid @RequestBody ProductoRequestDto dto) {
        return productoService.crear(dto);
    }

    @GetMapping
    public List<ProductResponseDto> listar(){
        return productoService.listar();
    }

    @GetMapping("/buscar/{nombre}")
    public List<ProductResponseDto> busquedaNombre(@PathVariable String nombre) { return productoService.busquedaNombre(nombre); }

    @DeleteMapping("/{id}")
    public void eliminar (@PathVariable Long id) { productoService.eliminar(id);}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto actualizar (@PathVariable  Long id, @RequestBody ProductoRequestDto dto) { return productoService.actualizar(id, dto);}

    @GetMapping("/{id}")
    public ProductResponseDto buscarPorId (@PathVariable Long id){return productoService.buscarPorId(id);}

}
