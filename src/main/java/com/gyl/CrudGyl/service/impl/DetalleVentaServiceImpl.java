package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.request.DetalleVentaRequestDto;
import com.gyl.CrudGyl.dto.response.ClienteResponseDto;
import com.gyl.CrudGyl.dto.response.DetalleVentaResponseDto;
import com.gyl.CrudGyl.entity.DetalleVenta;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.entity.Venta;
import com.gyl.CrudGyl.exception.RecursoNoVigenteException;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.exception.StockInsuficienteException;
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

        if (!(producto.getVigente()) || !(venta.getVigente()))
            throw new RecursoNoVigenteException("Recurso se encuentra deshabilitado");

        if(producto.getStock() < dto.cantidad())
            throw new StockInsuficienteException("No hay stock suficiente para el pedido");

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

    @Override
    public List<DetalleVentaResponseDto> busquedaVigente(Boolean vigente) {
            return detalleVentaRepository.findByVigente(vigente).stream()
                    .map(DetalleVentaMapper::toResponseDto)
                    .toList();
    }

    @Override
    public DetalleVentaResponseDto eliminar(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RecursosNoEncontradoException("No se encontro la ID"));
        detalleVenta.setVigente(false);
        detalleVentaRepository.save(detalleVenta);
        return DetalleVentaMapper.toResponseDto(detalleVenta);
    }
}
