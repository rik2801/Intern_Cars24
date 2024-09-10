import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AddAppointment from './AddAppointmentWindow'
import UpdateAppointment from './UpdateAppointmentWindow'
import DeleteAppointment from './DeleteAppointmentWindow'
import GetAllAppointments from './AllAppointmentList'
import GetOpenAppointments from './AllOpenAppointment'
import Navbar from './Navbar';
import classes from './AppointmentComponent.module.css'



function AppointmentRoot(){

    return (
        <div>
            <Router>
                <div style={{float:"left", width:"20%", backgroundColor:"transparent"}}>
                <Navbar/>
                </div>
                <div className={classes.vl}></div>
                <div style={{float:"right", width:"70%"}}>
                <Switch>
                    <Route path='/Appointment/AddAppointment' component={AddAppointment} />
                    <Route path='/Appointment/UpdateAppointment' component={UpdateAppointment} />
                    <Route path='/Appointment/DeleteAppointment' component={DeleteAppointment} />
                    <Route path='/Appointment/AllAppointments' component={GetAllAppointments} />
                    <Route path='/Appointment/OpenAppointments' component={GetOpenAppointments} />
                </Switch>

                </div>
            </Router>
        
            </div>
    )

}

export default AppointmentRoot;