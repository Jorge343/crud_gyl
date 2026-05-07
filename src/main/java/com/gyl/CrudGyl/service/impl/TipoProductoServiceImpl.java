package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.request.TipoProductoRequestDto;
import com.gyl.CrudGyl.dto.response.TipoProductoResponseDto;
import com.gyl.CrudGyl.entity.TipoProducto;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.TipoProductoMapper;
import com.gyl.CrudGyl.repository.TipoProductoRepository;
import com.gyl.CrudGyl.service.TipoProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    private TipoProductoRepository tipoProductoRepository;

    public TipoProductoServiceImpl(TipoProductoRepository tipoProductoRepository) {
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Override
    public TipoProductoResponseDto crear(TipoProductoRequestDto dto) {
        TipoProducto tipoProducto = TipoProductoMapper.toEntity(dto);
        TipoProducto guardado = tipoProductoRepository.save(tipoProducto);
        return TipoProductoMapper.toResponseDto(guardado);
    }

    @Override
    public List<TipoProductoResponseDto> listar() {
        return tipoProductoRepository.findAll()
                .stream()
                .map(TipoProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public TipoProductoResponseDto buscarPorId(Long id) {
        return tipoProductoRepository.findById(id)
                .map(TipoProductoMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontro el id" + id
                ));
    }

    @Override
    public TipoProductoResponseDto actualizar(Long id, TipoProductoRequestDto dto) {
        TipoProducto tipoProducto  = tipoProductoRepository.findById(id)
                .orElseThrow(()-> new RecursosNoEncontradoException(
                        "No se encontro el id " + id
                ));

        TipoProductoMapper.updateEntity(tipoProducto, dto);
        TipoProducto guardado = tipoProductoRepository.save(tipoProducto);
        return TipoProductoMapper.toResponseDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        TipoProducto tipoProducto  = tipoProductoRepository.findById(id)
                .orElseThrow(()-> new RecursosNoEncontradoException(
                        "No se encontro el id " + id
                ));
        tipoProducto.setVigente(false);
        tipoProductoRepository.save(tipoProducto);
    }

    @Override
    public List<TipoProductoResponseDto> busquedaNombre(String nombre) {
        return tipoProductoRepository.findByNombre(nombre)
                .stream().map(TipoProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<TipoProductoResponseDto> busquedaVigente(Boolean vigente) {
            return tipoProductoRepository.findByVigente(vigente).stream()
                    .map(TipoProductoMapper::toResponseDto).toList();
    }
}
