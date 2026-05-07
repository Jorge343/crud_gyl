package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.request.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.response.DetalleVentaResponseDto;
import com.gyl.CrudGyl.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleventa")

public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetalleVentaResponseDto agregarProducto(@Valid @RequestBody DetalleVentaRequestDto dto){
        return detalleVentaService.agregarProducto(dto);
    }

    @GetMapping("/{id}")
    public List<DetalleVentaResponseDto> listarPorVenta(@PathVariable Long id){
        return detalleVentaService.listarPorVenta(id);
    }

    @GetMapping("/buscarvigente/{vigente}")
    public List<DetalleVentaResponseDto> busquedaVigente(@PathVariable Boolean vigente){
        return detalleVentaService.busquedaVigente(vigente);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminar (@PathVariable Long id){
         detalleVentaService.eliminar(id);
    }
}
