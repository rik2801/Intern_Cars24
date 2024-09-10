import React from "react";
import classes from "./AppointmentComponent.module.css";
import LabelDate from "../Inputs/LabelDate";
import Button from "../Buttons/Button"
import axios from "../../axios/axios";

class UpdateAppointment extends React.Component {
  constructor(props) {
    super();
    this.props = props;
  }

  state={
      customer :{
          userId:""
      },
      preferredDate : "",
      preferredTime : "",
      location : "",
      inspectionType : "", 
      minTime: "06:00:00",
  }

  value = {
      location:"",
      inspectionType:"",

  }

  getTodayDate = () => {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; 
    var yyyy = today.getFullYear();
    if (dd < 10) {
      dd = "0" + dd;
    }
    if (mm < 10) {
      mm = "0" + mm;
    }

    today = yyyy + "-" + mm + "-" + dd;
    return today;
  };

  getMinTime = () =>
  {
    let dt = new Date();
    let newdt = dt.getTime()+1800000;
    dt = new Date(newdt);
    this.setState({preferredDate:this.parseDate(dt)})
    return `${dt.getHours()}:${dt.getMinutes()}:00`;
  }

  parseDate = (today)=>
  {
    var dd = today.getDate();
    var mm = today.getMonth() + 1; 
    var yyyy = today.getFullYear();
    if (dd < 10) {
      dd = "0" + dd;
    }
    if (mm < 10) {
      mm = "0" + mm;
    }

    today = yyyy + "-" + mm + "-" + dd;
    return today;
  }

  onLocationBlur = (e) =>
  {
    this.setState({location: e.target.value})
  };

  onCategorySelected = (e) =>
  {
    if(e.target.value.length === 3)
    {
        this.setState({inspectionType: e.target.value})
    }
    else
    this.setState({categoty: null})
  };

  onDateSelected = (e) =>
  {
      this.setState({preferredDate : e});
      if(e===this.parseDate(new Date()))
      {
          this.setState({minTime : this.getMinTime()})
      }
  }

  validTime =(time)=>
  {
      var parts = time.split(":");
      var minparts = this.state.minTime.split(":");
      if(parts[0]>minparts[0])
      {
        return true;
      }
      else if(parts[0]==minparts[0])
      {
        if(parts[1]<minparts[1])
            return false;
        return true;
      }
      return false;

  }
  onTimeSelected =(e) =>
  {
      this.onDateSelected(this.state.preferredDate);
      if(this.validTime(e.target.value))
        this.setState({preferredTime:e.target.value});
        else
        this.setState({preferredTime:""});
    
  }

  onSubmit = (e)=>
  {
      console.log(this.state);
      if(this.state.customer.userId ==="" || this.state.inspectionType==="" ||  this.state.preferredDate ==="" || this.state.preferredTime==="" || this.state.location ==="")
        this.props.showError({errorMsg:"Fill All Details", errorLogo:"❌"});
      else
        axios.put(`/updateAppointment/${this.state.customer.userId}`, this.state).then((response)=>this.props.showError({errorMsg:"Updation Successful", errorLogo:"✅"})).catch((err)=>this.props.showError({errorMsg:"Updation failed", errorLogo:"❌"}));
      
  }

  onCustomerBlur = (e)=>{
      this.setState({customer:{userId:e.target.value}});
      if(this.state.customer.userId ==="")
    {
        e.target.style = {borderColor : "#ff0000"}
    }
  }

  onAppointmentBlur = (e)=>
  {
    this.setState({appointmentId:e.target.value});
  }

  render() {
    return (
      <div className={classes.AddAppointment_root}>
        <h2 style={{ textDecoration: "underline" }}>Update Appointment</h2>
        <br />
        <input
          placeholder="Appointment ID"
          className={classes.AddAppointment_text}
          required={true}
          type="text"
          onBlur ={this.onAppointmentBlur}
        />
        <br />
        <br/>
        <input
          placeholder="Appointment Location"
          className={classes.AddAppointment_text}
          required={true}
          type="text"
          onBlur ={this.onLocationBlur}
        />
        <br />
        <br />
        <input
          placeholder="Customer ID"
          className={classes.AddAppointment_text}
          required={true}
          type="text"
          onBlur ={this.onCustomerBlur}
        />
        <br/><br/>
        <select className={classes.AddAppointment_select} required={true} onBlur = {this.onCategorySelected}>
          <option disabled selected value={null}>
            Choose reason for appointment
          </option>
          <optgroup label="Payment">
            <option
              className={classes.AddAppointment_item_selection}
              value="PY1"
            >
              Payment verification
            </option>
            <option value="PY2">Pay remaining amount</option>
          </optgroup>
          <optgroup label="Buy">
            <option value="CR1">View Car</option>
            <option value="CR2">Make a car deal</option>
          </optgroup>
        </select>

        <br />
        <div>
          <LabelDate
            label="Preferred Appointment Date"
            minDate={this.getTodayDate()}
            fontSize="15px"
            onChange={this.onDateSelected}
          />
        </div>

        <br/>
        <p style={{fontWeight:"bold", marginBottom:"0.1rem"}}>Preferred Appointment Time</p>
        <input type = "time" min={this.state.minTime} onBlur={this.onTimeSelected}/>
        <br/>
        <br/>
        <Button text="Update Appointment" onClick = {this.onSubmit}/>
      </div>
      
    );
  }
}

export default UpdateAppointment;
