package com.gyl.CrudGyl.controller;

import com.gyl.CrudGyl.dto.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.DetalleVentaResponseDto;
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
    public List<DetalleVentaResponseDto> listarPorVenta(@PathVariable Long idVenta){
        return detalleVentaService.listarPorVenta(idVenta);
    }
}
