package com.gyl.CrudGyl.service.impl;

import com.gyl.CrudGyl.dto.request.VentaRequestDto;
import com.gyl.CrudGyl.dto.response.VentaResponseDto;
import com.gyl.CrudGyl.entity.Cliente;
import com.gyl.CrudGyl.entity.Venta;
import com.gyl.CrudGyl.exception.RecursosNoEncontradoException;
import com.gyl.CrudGyl.mapper.VentaMapper;
import com.gyl.CrudGyl.repository.ClienteRepository;
import com.gyl.CrudGyl.repository.VentaRepository;
import com.gyl.CrudGyl.service.VentaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;

    public VentaServiceImpl(VentaRepository ventaRepository, ClienteRepository clienteRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public VentaResponseDto crear(VentaRequestDto dto) {
        Cliente cliente = clienteRepository.findById(dto.cliente_id())
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se puede crear la venta: Cliente no encontrado con ID " + dto.cliente_id()
                ));
        Venta venta = VentaMapper.toEntity(dto);
        venta.setCliente(cliente);
        Venta guardada = ventaRepository.save(venta);

        return VentaMapper.toResponseDto(guardada);
    }

    @Override
    public List<VentaResponseDto> listar() {
        return ventaRepository.findAll()
                .stream()
                .map(VentaMapper::toResponseDto)
                .toList();
    }

    @Override
    public VentaResponseDto buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .map(VentaMapper::toResponseDto)
                .orElseThrow(() -> new RecursosNoEncontradoException(
                        "No se encontró la venta con ID " + id
                ));
    }

    @Override
    public void eliminar(Long id){
        Venta venta  = ventaRepository.findById(id)
                .orElseThrow(()-> new RecursosNoEncontradoException(
                        "No se encontro el id " + id
                ));
        venta.setVigente(false);
        ventaRepository.save(venta);
    }

    @Override
    public List<VentaResponseDto> busquedaVigente(Boolean vigente) {
        return ventaRepository.findByVigente(vigente)
                .stream()
                .map(VentaMapper::toResponseDto)
                .toList();
    }
}