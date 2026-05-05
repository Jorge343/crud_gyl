package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.DetalleVentaResponseDto;
import com.gyl.CrudGyl.entity.DetalleVenta;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.entity.Venta;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.DetalleVentaMapper;
import com.gyl.CrudGyl.repository.DetalleVentaRepository;
import com.gyl.CrudGyl.repository.ProductoRepository;
import com.gyl.CrudGyl.repository.VentaRepository;
import com.gyl.CrudGyl.service.DetalleVentaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private DetalleVentaRepository detalleVentaRepository;
    private ProductoRepository productoRepository;
    private VentaRepository ventaRepository;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository,
                                   ProductoRepository productoRepository,
                                   VentaRepository ventaRepository){

        this.detalleVentaRepository = detalleVentaRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public DetalleVentaResponseDto agregarProducto(DetalleVentaRequestDto dto) {
        Venta venta = ventaRepository.findById(dto.venta_id()).
                orElseThrow(() -> new RecursosNoEncontradoException("Venta no encontrada"));
        Producto producto = productoRepository.findById(dto.producto_id()).
                orElseThrow(() -> new RecursosNoEncontradoException("Producto no encontrado"));

        if(producto.getStock() < dto.cantidad())
            throw new RuntimeException("No hay suficiente stock");

        double subtotal = producto.getPrecio() * dto.cantidad();
        DetalleVenta detalleVenta = DetalleVentaMapper.toEntity(dto);

        detalleVenta.setVenta(venta);
        detalleVenta.setProducto(producto);
        detalleVenta.setCantidad(dto.cantidad());
        detalleVenta.setSubtotal(subtotal);
        detalleVenta.setPrecio_unitario(producto.getPrecio());

        producto.setStock((producto.getStock()-dto.cantidad()));
        productoRepository.save(producto);

        venta.setTotal((venta.getTotal()+subtotal));
        ventaRepository.save(venta);

        detalleVentaRepository.save(detalleVenta);

        return DetalleVentaMapper.toResponseDto(detalleVenta);
    }

    @Override
    public List<DetalleVentaResponseDto> listarPorVenta(Long idVenta) {
        return detalleVentaRepository.findByVentaIdVenta(idVenta).
                stream().
                map(DetalleVentaMapper::toResponseDto).
                toList();
    }
}
