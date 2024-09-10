package com.cg.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cars.entities.Appointment;
import com.cg.cars.exception.AppointmentExceptions;
import com.cg.cars.exception.AppoitnmentNotFoundException;
import com.cg.cars.service.AppointmentServiceImpl;
import com.cg.cars.service.IAppointmentService;
import com.cg.cars.utils.AppointmentUtils;

/**
*Author: Vivekanandhan
*Date:08-04-2021
*Description:This is Appointment Controller Layer
**/

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService service;
    
    /**
     *Description	:To fetch Appointment details from the database
     *Return Value	:List<AppointmentDTO> object of the Appointment been fetched
     *Exception	:AppointmentException-It is raised when Appointment not found  
     **/

    @GetMapping(path = "/allAppointments", produces = "application/json")
    public ResponseEntity< List< Appointment > > getAllAppointment() {
        return new ResponseEntity< List<Appointment > >(
                AppointmentUtils.convertToAppointmentList(service.getAllAppointments()), HttpStatus.OK);
    }

    /**
     *Description	:To fetch Appointment details from the database
     *Input Params	:Appointment ID object to be fetched from the database
     *Return Value	:Object of the Appointment been fetched
     *Exception	:AppointmentException-It is raised when Appointment Id doesn't exist   
     **/
    
    @GetMapping(path = "/getAppointment/{id}", produces = "application/json")
    public ResponseEntity<Object> getAppointentById(@PathVariable("id") int id) {
        try {
                return new ResponseEntity < Object > (AppointmentUtils.convertToAppointment(service.getAppointment(id)),
                        HttpStatus.OK);
        } catch (AppoitnmentNotFoundException e) {
                return new ResponseEntity < Object > (e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
        }
    }
    
    /**
     *Description	:To add Appointment to the database
     *Input Params	:Appointment object to be added to the database
     *Return Value	:Object
     *Exception	:AppointmentException-It is raised when appointment already exist   
     **/

    @PostMapping(path = "/addAppointment", consumes = "application/json", produces = "application/json")
    public ResponseEntity < Object > addAppointment(@RequestBody Appointment appointment){
        try
        {
                return new ResponseEntity < Object > (AppointmentUtils.convertToAppointment(service.addAppointment(appointment)), HttpStatus.OK);
        }
        catch(AppointmentExceptions ex)
        {
                return new ResponseEntity < Object > (ex.getMessage(), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    /**
     *Description	: To fetch all available appointment details from the database
     *Return Value	: List<AppointmentDTO> object of the Appointment been fetched
     *Exception	    :  AppointmentException-It is raised when appointment not found  
     **/
    
    @GetMapping(path = "getOpenAppointments", produces = "application/json")
    public ResponseEntity < List < Appointment > > getOpenAppointments() {
        return new ResponseEntity < List < Appointment > >(
                AppointmentUtils.convertToAppointmentList(service.getOpenAppointments()), HttpStatus.OK);
    }

    /**
     *Description	:To delete Appointment from the database
     *Input Params	:Appointment id to be deleted from the database
     *Return Value	:Object of the Appointment been deleted
     *Exception	:AppointmentException-It is raised when appointment ID doesn't exist   
     **/
    
    @DeleteMapping(path = "deleteAppointment/{id}", produces = "application/json")
    public ResponseEntity < Object > deleteAppointment(@PathVariable("id") int id){
        try {
                return new ResponseEntity<Object>(AppointmentUtils.convertToAppointment(service.removeAppointment(id)),
                        HttpStatus.OK);
        } catch (AppoitnmentNotFoundException e) {
                return new ResponseEntity < Object > (e.getMessage(), HttpStatus.BAD_REQUEST);       
        }
    }

    /**
     *Description	:To update Appointment details to the database
     *Input Params	:Appointment to be updated in the database
     *Return Value	:Object of the Appointment been updated
     *Exception	:AppointmentException-It is raised when appointment doesn't exist   
     **/
    
    @PutMapping(path = "updataAppointment/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity < Object > updateAppointment(@RequestBody Appointment appointment){
        try {
                if (AppointmentServiceImpl.isValidAppointment(appointment))
                    return new ResponseEntity < Object > (AppointmentUtils.convertToAppointment(
                            service.updateAppointment(appointment.getAppointmentId(), appointment)), HttpStatus.OK);

                throw new AppointmentExceptions("Given appointment is not in valid format");
        } catch (AppointmentExceptions e) {
                return new ResponseEntity < Object > (e.getMessage(), HttpStatus.BAD_REQUEST);       
        }
    }
}
