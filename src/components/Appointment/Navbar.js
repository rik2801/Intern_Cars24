import React from 'react'
import { Link } from 'react-router-dom';
import NavBarItem from './NavBarItem'
import classes from './AppointmentComponent.module.css'

class Navbar extends React.Component
{

    render()
    {
        return(

            <>
            <div className={classes.NavBar} >
                <ul>
                    <Link style={{textDecoration:"none"}} to="/Appointment/AddAppointment"><NavBarItem text = "Add Appointment"/></Link>
                </ul>
                <ul>
                <Link style={{textDecoration:"none"}} to="/Appointment/UpdateAppointment"><NavBarItem text = "Update Appointment"/></Link>
                </ul>
                <ul>
                <Link style={{textDecoration:"none"}} to="/Appointment/DeleteAppointment"><NavBarItem text = "Delete Appointment"/></Link>
                </ul>
                <ul>
                <Link style={{textDecoration:"none"}} to="/Appointment/AllAppointments"><NavBarItem text = "All Appointments"/></Link>
                </ul>
                <ul>
                <Link style={{textDecoration:"none"}} to="/Appointment/OpenAppointments"><NavBarItem text = "Open Appointments"/></Link>
                </ul>
            </div>
            </>
        )
    }
}

export default Navbar;