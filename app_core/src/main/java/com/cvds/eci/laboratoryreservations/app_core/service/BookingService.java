package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.model.User;
import com.cvds.eci.laboratoryreservations.app_core.repository.BookingRepository;
import com.cvds.eci.laboratoryreservations.app_core.repository.LaboratoryRepository;
import com.cvds.eci.laboratoryreservations.app_core.repository.UserRepository;

/**
 * Servicio encargado de gestionar las reservas de los laboratorios.
 * Proporciona métodos para crear, consultar y eliminar reservas.
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Obtiene todas las reservas registradas en el sistema.
     * 
     * @return Lista de todas las reservas.
     * @throws RuntimeException si no hay reservas registradas.
     */
    public List<Booking> getAllBookings() {
        List<Booking> list = bookingRepository.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("The booking's list is empty");
        }
        return list;
    }

    /**
     * Agrega una nueva reserva para un laboratorio, validando previamente que no haya 
     * conflictos de horario con reservas existentes.
     * 
     * @param booking Objeto {@link Booking} con la información de la reserva.
     * @return La reserva guardada en la base de datos.
     * @throws RuntimeException si el laboratorio no existe o si ya hay una reserva en el horario especificado.
     */
    public Booking addBooking(Booking booking) {
        Laboratory lab = laboratoryRepository.findByName(booking.getLabName());

        if (lab == null) {
            throw new RuntimeException("Laboratory not found.");
        }

        // Verifica si hay conflictos de horario con reservas existentes
        List<Booking> conflictBookings = bookingRepository.findByLabNameAndDateAndInitHourLessThanAndFinalHourGreaterThan(
            lab.getName(),
            booking.getDate(),
            booking.getFinalHour(),
            booking.getInitHour()
        );

        if (!conflictBookings.isEmpty()) {
            throw new RuntimeException("There's a laboratory booked between that schedule already.");
        }

        return bookingRepository.save(booking);
    }

    /**
     * Elimina una reserva existente por su ID.
     * 
     * @param id Identificador único de la reserva.
     * @return ID de la reserva eliminada.
     * @throws RuntimeException si la reserva no existe.
     */
    public String deleteBooking(String id) {
        Booking bookingSearch = bookingRepository.findById(id).orElse(null);
        if (bookingSearch == null) {
            throw new RuntimeException("The booking " + id + " does not exist");
        }
        bookingRepository.deleteById(id);
        return id;
    }

    /**
     * Busca una reserva por su ID.
     * 
     * @param id Identificador único de la reserva.
     * @return Objeto {@link Booking} correspondiente al ID proporcionado.
     * @throws RuntimeException si la reserva no existe.
     */
    public Booking findById(String id) {
        Booking bookingSearch = bookingRepository.findById(id).orElse(null);
        if (bookingSearch == null) {
            throw new RuntimeException("The booking " + id + " does not exist");
        }
        return bookingSearch;
    }

    public Booking findByUserId(String userId){
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new RuntimeException("The booking with user id " + userId + " does not exist");
        }

        Booking booking = bookingRepository.findByUserId(user.getId()).orElse(null);
        
        

        return booking;
    }

}
