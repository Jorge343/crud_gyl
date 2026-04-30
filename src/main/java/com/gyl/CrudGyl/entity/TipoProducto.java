package com.gyl.CrudGyl.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tipo_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tipo_id;

    @Column(nullable=false, length = 50)
    private String nombre;

    @Column(nullable=false)
    private String descripcion;

}