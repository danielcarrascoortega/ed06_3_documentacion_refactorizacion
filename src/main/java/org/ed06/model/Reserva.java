package org.ed06.model;

import java.time.LocalDate;

/**
 * Clase que representa una reserva del hotel
 *
 * @author Daniel Martiñan
 */

public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;

    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = calcularPrecioFinal();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Calcula el precio total de la reserva. Si el cliente es VIP tendrá un descuento del 10%. Si la estancia es de más de 7 días, tendrá un descuento del 5%.
     *
     * @return precioFinal Devuelve el precio final con descuentos (si los tiene).
     */
//
    // Devuelve precio total de la reserva
    public double calcularPrecioFinal() {
        //calculamos los días de la reserva
        int diasEstancia = fechaFin.getDayOfYear() - fechaInicio.getDayOfYear();
        /* Declaramos la variable para almacenar el precio final
        Este se calcula multiplicando el precio base de la habitación por el número de noches de la reserva */
        double precioFinal = habitacion.getPrecioBase() * diasEstancia;

        final double descuentoVIP = 0.9;
        final double descuentoLargaEstancia = 0.95;

        // Si el cliente es VIP, aplicamos un descuento del 10%
        if (cliente.esVip) {
            precioFinal *= descuentoVIP;
        }

        // Si el intervalo de fechas es mayor a 7 días, aplicamos un descuento adicional del 5%
        if (diasEstancia > 7) {
            precioFinal *= descuentoLargaEstancia;
        }

        // Devolvemos el precio final
        return precioFinal;
    }
}
