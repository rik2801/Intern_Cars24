import axios from './../../axios/axios';
import React from 'react'
import Button from '../Buttons/Button';
import classes from './AppointmentComponent.module.css'

// /**
// *Author: Vivekanandhan
// *Date:06-05-2021
// *Description:This is Appointment Container
// **/

class DeleteAppointment extends React.Component
{

    constructor(props)
    {
        super();
        this.props = props;
    }

    state = {
        id:""
    }
    onIDEnter = (e)=>{

        this.setState({id:e.target.value})
    }

    accept = ()=>
    {
        this.setState({canDelete:true});
    }
    buttonmsg
    onButtonClick = ()=>{

        if(this.state.id === "")
            this.props.showError({errorMsg:"Fill All Details", errorLogo:"❌"})
        else
        {
            axios.delete(`deleteAppointment/${this.state.id}`).then((response)=>{this.props.showError({errorMsg:"Appointment Deleted Successfully", errorLogo:"✅"}); console.log(response);}).catch((err)=>this.props.showError({errorMsg:"Appointment not found. So cannot be deleted", errorLogo:"❌"}));
        }
    }

    render(){
        return(
            <div className={classes.AddAppointment_root}>
                <h2>Delete Appointment</h2>
                <br/>
                <br/>
                <input
          placeholder="Appointment ID"
          className={classes.AddAppointment_text}
          required={true}
          type="text"
          onBlur ={this.onIDEnter}
        />
        <br/>
        <br/>
<Button text="Delete Appointment" onClick ={this.onButtonClick}/>
            </div>
        )
    }
}

export default DeleteAppointment;