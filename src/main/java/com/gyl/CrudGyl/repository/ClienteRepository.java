package com.gyl.CrudGyl.repository;

import com.gyl.CrudGyl.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByApellido(String apellido);

    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByVigente(Boolean vigente);

    List<Cliente> findByCorreo(String correo);

    List<Cliente> findByTelefono(String telefono);

}