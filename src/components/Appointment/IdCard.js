import React from 'react'
import classes from './AppointmentComponent.module.css'

class IdCard extends React.Component
{
    constructor(props)
    {
        super();
        this.props = props;
    }

    render()
    {
        return(
            <div className={classes.IdCardBox}>
                <h3>Appointment Id</h3>
                <h1>{this.props.id}</h1>
                <br/>
            </div>
        )
    }
}

export default IdCard;