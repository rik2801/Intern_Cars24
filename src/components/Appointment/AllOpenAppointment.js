import React from 'react'
import axios from './../../axios/axios'
import AppointmentCard from './AppointmentCard';

// /**
// *Author: Vivekanandhan
// *Date:06-05-2021
// *Description:This is Appointment Container
// **/

class AllOpenAppointment extends React.Component
{
    constructor(props)
    {
        super();
        this.props = props;
        this.onLoad();
    }

    state = {

    }

    resultValue = []
    onLoad = async ()=> 
    {
        await axios.get("/getOpenAppointments").then((data)=>{
            this.resultValue = data.data;
            this.setState({render: true})
            console.log(data.data)}).catch((err)=>console.log(err));
            
    }

    shouldComponentUpdate()
    {
        return true;
    }

     render()
    {
        
        return(
            <div>
                <h1>All Open Appointments</h1>
                <ul style={{listStyle:"none", display:"inline-block", width:"100%"}}>
                <hr style={{borderWidth:"3px", borderBlockColor:"#000000", textAlign:"center"}}/>
                    {
                        this.resultValue.map((result) =>
                        {
                            return (
                                <div>
                                <li key={result.appointmentId}>
                                    <AppointmentCard id={result.appointmentId} customer={result.customer.name} location={result.location} date = {result.preferredDate} time = {result.preferredTime}/>
                                </li>
                                <hr style={{borderWidth:"3px", borderBlockColor:"#000000"}}/>
                                </div>   
                            )
                        })
                    }        
                </ul>
            </div>
        )
}
}


export default AllOpenAppointment;