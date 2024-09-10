import React from 'react'
import classes from './AppointmentComponent.module.css'

class NavBarItem extends React.Component
{  
    
    constructor(props)
    {
        super();
        this.props = props;
    }

    render()
    {
        return (
            <div className={classes.NavBarItem} onClick={this.props.onClick}>
                <p style={{fontSize:"20px"}}>{this.props.text}</p>
            </div>
        )
    }

}

export default NavBarItem;