import React from 'react';
import { FormControl, TextField } from '@material-ui/core';
import Container from '@material-ui/core/Container';
import Grid from "@material-ui/core/Grid";
import { Link } from "react-router-dom";
import classes from '../design/AppointmentComponent.module.css'
import Button from '../Buttons/Button'


// /**
// *Author: Dhivya
// *Date:06-05-2021
// *Description:This is Order Container
// **/

export default class AddOrderForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
    
                amount: "",
                billingDate:  "",
                customer: {
                    userId:  "",
                }
                         

        }
    }

    onAmountChange = (e) => {
        this.setState({ amount: e.target.value });
    }

    onBillingDateChange = (e) => {
        this.setState({ billingDate: e.target.value });
    }

    onUserIdChange = (e) => {
        this.setState(state =>({customer: {...state.customer,userId:e.target.value}}));
    }

    


    onSubmit = event => {

        console.log("Submitted");
        alert(this.state);
        console.log(this.state);
        event.preventDefault();
        this.props.onSubmitOrder(
            {
                amount: this.state.amount,
                billingDate: this.state.billingDate,
                userId: this.state.customer.userId,
               
               
            }

        );
 
    }


    render() {
        return (

            <Container className={classes.AddAppointment_root}>
                <div className="bg_Ord_image">
          
            
            <form onSubmit={this.onSubmit}  >
                        <div>
                        <h2 style={{ textDecoration: "underline" }}> ORDER DETAILS :</h2> <br/>
                       </div>
                       
                <Grid item spacing={3}>
                <FormControl fullWidth>  
                <TextField
                         required id="standard-number" 
                         label="Amount"
                         type="number"
                         color="secondary" 
                         variant="outlined"
                         placeholder="Enter Amount"
                         value={this.state.amount} onChange={this.onAmountChange}/>
                         
                        </FormControl >
                        </Grid>
                    <br />

                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                        required id="date"
                        label="Billing Date"
                        type="date"
                        color="secondary" 
                        variant="outlined"
                        placeholder="DD/MM/YYYY"
                        value={this.state.billingDate}
                        onChange={this.onBillingDateChange}
                        InputLabelProps={{
                            shrink: true
                        }} />
                        </FormControl >
                        </Grid>
                    <br />


                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                        required id="standard-number"
                        label="User Id" 
                        type="number"
                        color="secondary" 
                        variant="outlined"
                        placeholder="Enter User Id"
                        value={this.state.customer.userId} onChange={this.onUserIdChange}
                         />
                        </FormControl >
                        </Grid>
                    <br />

                        <br />
                        
                        {this.state.error && <b className="m-1 text-danger">{this.state.error}</b>}
                        <div style={{display:"inline-flex"}}>
                        <Button  text="Add Order" onClick={this.onSubmit}></Button>&nbsp;&nbsp;&nbsp;
                      <Link to={`/order`}><Button text= "Cancel"></Button></Link>
                         </div>
                    </form>
               </div>
            </Container>
           
        )
    }

}