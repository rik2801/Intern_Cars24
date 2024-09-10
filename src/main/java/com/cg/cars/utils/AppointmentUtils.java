package com.cg.cars.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.cg.cars.entities.Appointment;
import com.cg.cars.model.AppointmentDTO;

public class AppointmentUtils {
    private AppointmentUtils()
    {
        super();
    }
    
    public static AppointmentDTO convertToAppointmentDTO(Appointment appointment)
    {
        AppointmentDTO dto=new AppointmentDTO();
        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setCustomer(CustomerUtils.convertToCustomerDto(appointment.getCustomer()));
        dto.setInspectionType(appointment.getInspectionType());
        dto.setLocation(appointment.getLocation());
        if(appointment.getPayment()!=null)
            dto.setPayment(PaymentUtils.convertToPaymentDto(appointment.getPayment()));
        dto.setPreferredDate(appointment.getPreferredDate().toString());
        dto.setPreferredTime(appointment.getPreferredTime().toString());
        return dto;
    }

    public static Appointment convertToAppointment(AppointmentDTO appointmentDTO)
    {
        Appointment appointment=new Appointment();
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        appointment.setCustomer(CustomerUtils.convertToCustomer(appointmentDTO.getCustomer()));
        appointment.setInspectionType(appointmentDTO.getInspectionType());
        appointment.setLocation(appointmentDTO.getLocation());
        if(appointment.getPayment()!=null)
            appointment.setPayment(PaymentUtils.convertToPayment(appointmentDTO.getPayment()));
        appointment.setPreferredDate(LocalDate.parse(appointmentDTO.getPreferredDate()));
        appointment.setPreferredTime(LocalTime.parse(appointmentDTO.getPreferredTime()));
        return appointment;
    }


    public static List<Appointment> convertToAppointmentList(List<AppointmentDTO> appointmentDTOs)
    {
        List<Appointment> appointments = new ArrayList<Appointment>();
        for(AppointmentDTO appointmentDTO : appointmentDTOs)
        {
            appointments.add(convertToAppointment(appointmentDTO));
        }
        return appointments;
    }

    public static List<AppointmentDTO> convertToAppointmentDTOList(List<Appointment> appointments)
    {
        List<AppointmentDTO> appointmentDTOs = new ArrayList<AppointmentDTO>();
        for(Appointment apppointment : appointments)
        {
            appointmentDTOs.add(convertToAppointmentDTO(apppointment));
        }
        return appointmentDTOs;
    }
}
