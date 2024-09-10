import React from 'react'
import classes from './AppointmentComponent.module.css'
import {  CalendarDateFilled, ClockFilled, LocationFilled } from "fluent-icons-react";
import IdCard from './IdCard';

// /**
// *Author: Vivekanandhan
// *Date:06-05-2021
// *Description:This is Appointment Container
// **/

class AppointmentCard extends React.Component
{
    constructor(props)
    {
        super();
        this.props = props;
        console.log(this.props);
    }

    
    render()
    {
        return(
            <div className={classes.AppointmentCard} style={{display:"inline-block"}}>
                <div style={{width:"70%", float:"left"}}>
                <br/>
                <p className={classes.AppointmentCard_Text}><LocationFilled color="#0f6686" size={20}/> <b> Appointment At</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {this.props.location}</p>
                <p className={classes.AppointmentCard_Text}>For the customer&nbsp;&nbsp;&nbsp;{this.props.customer}</p>
                <p className={classes.AppointmentCard_Text}><CalendarDateFilled color="#0f6686" size={20}/>&nbsp;&nbsp;{this.props.date}&nbsp;&nbsp;&nbsp;&nbsp;<ClockFilled color="#0f6686" size={20}/>&nbsp;&nbsp;{this.props.time}</p>
                </div>
                <div style={{width:"30%", float:"right", textAlign:"center", verticalAlign:"center"}}>
                    <IdCard id={this.props.id}/>
                </div>
            </div>
        )
    }

}

export default AppointmentCard;