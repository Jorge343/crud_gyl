package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.ProductoResponseDto;
import com.gyl.CrudGyl.dto.ProductoRequestDto;
import com.gyl.CrudGyl.dto.TipoProductoResponseDto;
import com.gyl.CrudGyl.entity.Producto;
import com.gyl.CrudGyl.entity.TipoProducto;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.ProductoMapper;
import com.gyl.CrudGyl.mapper.TipoProductoMapper;
import com.gyl.CrudGyl.repository.ProductoRepository;
import com.gyl.CrudGyl.repository.TipoProductoRepository;
import com.gyl.CrudGyl.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {


    private ProductoRepository productoRepository;
    private TipoProductoRepository tipoProductoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, TipoProductoRepository tipoProductoRepository) {
        this.productoRepository = productoRepository;
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Override
    public List<ProductoResponseDto> busquedaNombre(String nombre){
        return productoRepository.findByNombre(nombre)
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public ProductoResponseDto crear(ProductoRequestDto dto) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(dto.idTipoProducto()).
                orElseThrow(() -> new RecursosNoEncontradoException("No se encontro la categoria"));
        Producto producto = ProductoMapper.toEntity(dto);
        producto.setTipoProducto(tipoProducto);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(guardado);
    }

    @Override
    public List<ProductoResponseDto> listar() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }



    @Override
    public ProductoResponseDto buscarPorId(Long id) {

        return productoRepository.findById(id)
                .map(ProductoMapper::toResponseDto)
                .orElseThrow(()-> new RecursosNoEncontradoException(
                        "No se encontro el Id " + id
                ));
    }

    @Override
    public ProductoResponseDto actualizar(Long id, ProductoRequestDto dto) {
        Producto producto  = productoRepository.findById(id)
                        .orElseThrow(()-> new RecursosNoEncontradoException(
                                "No se encontro el id " + id
                        ));

        ProductoMapper.updateEntity(producto, dto);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto  = productoRepository.findById(id)
                .orElseThrow(()-> new RecursosNoEncontradoException(
                        "No se encontro el id " + id
                ));
        productoRepository.delete(producto);
    }

    @Override
    public List<ProductoResponseDto> busquedaNoVigente(Boolean vigente) {
        return productoRepository.findByVigente(vigente).stream()
                .map(ProductoMapper::toResponseDto).toList();

    }
}
