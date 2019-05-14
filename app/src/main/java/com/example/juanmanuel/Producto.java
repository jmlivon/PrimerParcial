package com.example.juanmanuel;

/**
 * Created by alumno on 09/05/2019.
 */

public class Producto {
    private String nombre;
    private Integer cantidad;
    private Double precio;

    public Producto(){}

    public Producto(String nombre, Integer cantidad, Double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
