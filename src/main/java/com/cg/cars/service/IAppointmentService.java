package com.cg.cars.service;

import java.util.List;

import com.cg.cars.entities.Appointment;
import com.cg.cars.exception.AppointmentExceptions;
import com.cg.cars.exception.AppoitnmentNotFoundException;
import com.cg.cars.model.AppointmentDTO;

public interface IAppointmentService {

    public AppointmentDTO addAppointment(Appointment appointment) throws AppointmentExceptions;

    public AppointmentDTO removeAppointment(int id) throws AppoitnmentNotFoundException;
    
    public AppointmentDTO updateAppointment(int id, Appointment appointment) throws AppointmentExceptions;

    public AppointmentDTO getAppointment(int id) throws AppoitnmentNotFoundException;

    public List<AppointmentDTO> getAllAppointments();
    
    public List<AppointmentDTO> getOpenAppointments();

}
