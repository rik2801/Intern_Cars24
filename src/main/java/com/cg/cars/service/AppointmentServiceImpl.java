package com.cg.cars.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cars.dao.IAppointmentRepository;
import com.cg.cars.entities.Appointment;
import com.cg.cars.exception.AppointmentExceptions;
import com.cg.cars.exception.AppoitnmentNotFoundException;
import com.cg.cars.exception.InvalidAppointmentDateException;
import com.cg.cars.exception.InvalidAppointmentInspectionTypeException;
import com.cg.cars.exception.InvalidAppointmentLocationException;
import com.cg.cars.exception.InvalidAppointmentTimeException;
import com.cg.cars.model.AppointmentDTO;
import com.cg.cars.utils.AppointmentUtils;

/**
*Author: Vivekanandhan
*Date:08-04-2021
*Description:This is Appointment Service Class that provides the
 *   		   services to add an appointments, remove an appointment, update an appointment
 *             and view an appointment
**/


@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    IAppointmentRepository repo;

    private final String message= "Appointment with id %d not found";


    /**
     *Description	:To add Appointment to the database
     *Input Params	:Appointment object to be added to the database
     *Return Value	:Object
     *Exception	:AppointmentException-It is raised when appointment already exist   
     **/
    
    @Transactional
    @Override
    public AppointmentDTO addAppointment(Appointment appointment) throws AppointmentExceptions {
        if (AppointmentServiceImpl.isValidAppointment(appointment)) {
            Appointment app = repo.save(appointment);
            return AppointmentUtils.convertToAppointmentDTO(app);
        }
        throw new AppointmentExceptions("Invalid Appointment");

    }

    
    /**
     *Description	:To delete Appointment from the database
     *Input Params	:Appointment id to be deleted from the database
     *Return Value	:Object of the Appointment been deleted
     *Exception	:AppointmentException-It is raised when appointment ID doesn't exist   
     **/
    
    @Transactional
    @Override
    public AppointmentDTO removeAppointment(int id) throws AppoitnmentNotFoundException{
        try {
            if (this.getAppointment(id) != null) {
                Appointment appointment = repo.getOne(id);
                repo.deleteById(id);
                return AppointmentUtils.convertToAppointmentDTO(appointment);
            }
            throw new AppoitnmentNotFoundException(String.format(message, id));
        }
        catch (AppoitnmentNotFoundException ex) {
            throw ex;
        }
        catch(EntityNotFoundException ex)
        {
            throw new AppoitnmentNotFoundException(String.format(message, id));
        }
    }
    
    /**
     *Description	:To fetch Appointment details from the database
     *Input Params	:Appointment ID object to be fetched from the database
     *Return Value	:Object of the Appointment been fetched
     *Exception	:AppointmentException-It is raised when Appointment Id doesn't exist   
     **/

    @Transactional
    @Override
    public AppointmentDTO getAppointment(int id) throws AppoitnmentNotFoundException {
        try {
            return AppointmentUtils.convertToAppointmentDTO(repo.getOne(id));
        } catch (EntityNotFoundException ex) {
            throw new AppoitnmentNotFoundException(String.format(message,id));
        }
    }
    
    /**
     *Description	:To fetch Appointment details from the database
     *Return Value	:List<AppointmentDTO> object of the Appointment been fetched
     *Exception	:AppointmentException-It is raised when Appointment not found  
     **/

    @Transactional
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return AppointmentUtils.convertToAppointmentDTOList(repo.findAll());
    }

    /**
     *Description	: To fetch all available appointment details from the database
     *Return Value	: List<AppointmentDTO> object of the Appointment been fetched
     *Exception	    :  AppointmentException-It is raised when appointment not found  
     **/
    
    @Transactional
    @Override
    public List<AppointmentDTO> getOpenAppointments() {
        return AppointmentUtils.convertToAppointmentDTOList(repo.getOpenAppointments(LocalDate.now()));
    }

    public static boolean isValidAppointment(Appointment appointment) throws AppointmentExceptions {
        return AppointmentServiceImpl.isValidAppointmentDate(appointment.getPreferredDate())
                && AppointmentServiceImpl.isValidAppointmentTime(appointment.getPreferredTime(),appointment.getPreferredDate())
                && AppointmentServiceImpl.isValidInspectionType(appointment.getInspectionType())
                && AppointmentServiceImpl.isValidLocation(appointment.getLocation());
    }

    public static boolean isValidAppointmentDate(LocalDate date) throws InvalidAppointmentDateException {
        if (LocalDate.now().compareTo(date) > 0) {
            throw new InvalidAppointmentDateException(date.toString() + " is less than today's date");
        }
        return true;
    }

    public static boolean isValidAppointmentTime(LocalTime time, LocalDate date) throws InvalidAppointmentTimeException {
        if(LocalDate.now().compareTo(date)==0)
        {
            return (LocalTime.now().compareTo(time)<0);
        }
        else if(LocalDate.now().compareTo(date)<0)
            return true;
        else
            throw new InvalidAppointmentTimeException("Appointment date is past. So appointment time is not valid");
    }

    public static boolean isValidLocation(String location) throws InvalidAppointmentLocationException {
        if (!location.matches("^[a-zA-Z ]{4,}$"))
            throw new InvalidAppointmentLocationException(location + " is not of length greater than 3");
        return true;
    }

    public static boolean isValidInspectionType(String inspectionType)
            throws InvalidAppointmentInspectionTypeException {
        if (!inspectionType.matches("^[a-zA-z0-9]{1,4}$"))
            throw new InvalidAppointmentInspectionTypeException(inspectionType + " is not of length 2 or 3 ");
        return true;
    }

    /**
     *Description	:To update Appointment details to the database
     *Input Params	:Appointment to be updated in the database
     *Return Value	:Object of the Appointment been updated
     *Exception	:AppointmentException-It is raised when appointment doesn't exist   
     **/
    
    @Transactional
    @Override
    public AppointmentDTO updateAppointment(int id, Appointment appointment) throws AppointmentExceptions {
        if(AppointmentServiceImpl.isValidAppointment(appointment))
        {
            Optional<Appointment> oldAppointment=repo.findById(id);
            if(oldAppointment.isPresent())
            {
                oldAppointment.get().setCustomer(appointment.getCustomer());
                oldAppointment.get().setInspectionType(appointment.getInspectionType());
                oldAppointment.get().setLocation(appointment.getLocation());
                oldAppointment.get().setPayment(appointment.getPayment());
                oldAppointment.get().setPreferredDate(appointment.getPreferredDate());
                oldAppointment.get().setPreferredTime(appointment.getPreferredTime());
                repo.save(oldAppointment.get());
                repo.flush();
                return AppointmentUtils.convertToAppointmentDTO(oldAppointment.get());
            }
            throw new AppoitnmentNotFoundException("Updation failed. Appointment with id "+id+" is not found");
        }
        throw new AppointmentExceptions("Appointment data is invalid");
    }

}
