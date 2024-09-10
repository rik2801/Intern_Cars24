import React from 'react';
import Button from '../Buttons/Button'
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { FormControl, TextField } from '@material-ui/core';
import CustomerValidation from './CustomerValidations';
import { withRouter } from "react-router-dom";
import classes from '../design/AppointmentComponent.module.css';
import Grid from "@material-ui/core/Grid";



// /**
//  * Author : Monisha V
//  * Date   : 06-05-2021 
//  * Description : This is Customer Container
//  **/



class CustomerUpdateForm extends React.Component {

    constructor(props) 
    {
        super(props);
        this.state = 
        {
            userId: "",
            name : "",
            email: "",
            contactNo: "",
            dob: "",
            address:
            {
                doorNo: "",
                street: "",
                area: "",
                city: "",
                state: "",
                pinCode: "",
            }      
        };
        this.validators = CustomerValidation;
        this.resetValidators();
    }

    updateValidators = (fieldName, value) => {
        this.validators[fieldName].errors = [];
        this.validators[fieldName].state = value;
        this.validators[fieldName].valid = true;
        this.validators[fieldName].rules.forEach((rule) => {
            if (rule.test instanceof RegExp) {
                if (!rule.test.test(value)) {
                    this.validators[fieldName].errors.push(rule.message);
                    this.validators[fieldName].valid = false;
                }
            } else if (typeof rule.test === 'function') {
                if (!rule.test(value)) {
                    this.validators[fieldName].errors.push(rule.message);
                    this.validators[fieldName].valid = false;
                }
            }
        });
    }

    resetValidators = () => {
        Object.keys(this.validators).forEach((fieldName) => {
            this.validators[fieldName].errors = [];
            this.validators[fieldName].state = '';
            this.validators[fieldName].valid = false;
        });
    }

    displayValidationErrors = (fieldName) => {
        const validator = this.validators[fieldName];
        const result = '';
        if (validator && !validator.valid) {
            const errors = validator.errors.map((info, index) => {
                return <span style={errorStyle} key={index}>* {info}</span>;
            }); 

            return (
                <div style={errorStyle} className="col s12 row">
                    {errors}
                </div>
            ); 
        }
        return result;
    }

    

    handleInputChange(event, inputPropName) {
        const newState = Object.assign({}, this.state);
        newState.address[inputPropName] = event.target.value;
        this.setState(newState);
        this.updateValidators(inputPropName, event.target.value);
    }

    handleInputChangeCustomer(event, inputPropName) {
        const newState = Object.assign({}, this.state);
        newState[inputPropName] = event.target.value;
        this.setState(newState);
        this.updateValidators(inputPropName, event.target.value);
    }

    onCustomerIdChange = (e) => {
        this.setState({ userId: e.target.value });
    }

    

    onEmailChange = (e) => {
        this.setState({ email: e.target.value });
    }

   

   

    onDoorNoChange = (e) => {
        this.setState(state => ({ address: { ...state.address, doorNo: e.target.value }, }));
    }

    onStreetChange = (e) => {
        this.setState(state => ({ address: { ...state.address, street: e.target.value }, }));
    }

    onAreaChange = (e) => {
        this.setState(state => ({ address: { ...state.address, area: e.target.value }, }));
    }

    onCityChange = (e) => {
        this.setState(state => ({ address: { ...state.address, city: e.target.value }, }));
    }

    onStateChange = (e) => {
        this.setState(state => ({ address: { ...state.address, state: e.target.value }, }));
    }

    

    onCancel = () => {
        //this.props.handleCancel(); 
        this.props.history.push('/customer');
    }

    onSubmit = (e) => 
    {
        e.preventDefault();
        console.log("Submitted");
        alert("Customer Details Updated");
        console.log(this.state);
        this.props.onSubmitCustomer
        (
            {
                userId:this.state.userId,
                name: this.state.name,
                email: this.state.email,
                contactNo: this.state.contactNo,
                dob: this.state.dob,
                doorNo: this.state.address.doorNo,
                street: this.state.address.street,
                area: this.state.address.area,
                city: this.state.address.city,
                state: this.state.address.state,
                pinCode: this.state.address.pinCode,
            }
        );
        
    }

    render() {
        return (
            <Container className={classes.AddAppointment_root}>
         <div className="bg_Cus_image">
                <form onSubmit={this.onSubmit} >
               
                    <div>
                    <h2 style={{ textDecoration: "underline" }}> CUSTOMER DETAILS :</h2> <br/>
                    </div>

                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="Customer ID" color="secondary"  placeholder="Enter Customer ID" variant="outlined"
                        value={this.state.userId} onChange={this.onCustomerIdChange} />
                        </FormControl>
                    <br /><br/>
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="Customer Name" color="secondary"  placeholder="Enter Customer Name" variant="outlined"
                        value={this.state.name} onChange={event => this.handleInputChangeCustomer(event, 'name')} />
                        </FormControl>
                        {this.displayValidationErrors('name')}
                    <br />
                    </Grid >
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="Customer E-mail" color="secondary"  placeholder="Enter Customer E-mail" variant="outlined"
                        value={this.state.email} onChange={this.onEmailChange} />
                        </FormControl><br/></Grid>
                        
                    <br />

                    <FormControl fullWidth>
                    <TextField
                        required id="standard-number" label="Contact Number" type="number"color="secondary"  variant="outlined" placeholder="Enter Customer Contact Number"
                        value={this.state.contactNo} onChange={event => this.handleInputChangeCustomer(event, 'contactNo')} />
                        </FormControl>
                        {this.displayValidationErrors('contactNo')}
                    <br />
                    
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="Date of Birth" type="date"color="secondary"  variant="outlined"
                        value={this.state.dob} onChange={event => this.handleInputChangeCustomer(event, 'dob')} InputLabelProps={{
                            shrink: true
                        }}  />
                        </FormControl>
                        {this.displayValidationErrors('dob')}
                    <br />

                    <div>
                    <h2 style={{ textDecoration: "underline" }}> ADDRESS DETAILS :</h2><br/>
                    </div>
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="Door Number" color="primary"variant="outlined"  placeholder= "Enter Customer Door No"
                        value={this.state.address.doorNo} onChange={this.onDoorNoChange} />
                        </FormControl>
                        </Grid>
                    <br />
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="Street" color="primary" variant="outlined" placeholder="Enter Street Name"
                        value={this.state.address.street} onChange={this.onStreetChange} />
                        </FormControl>
                        </Grid>
                    <br />
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="Area" color="primary" variant="outlined"placeholder="Enter Area Name"
                        value={this.state.address.area} onChange={this.onAreaChange} />
                        </FormControl>
                        </Grid>
                    <br />
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="City" color="primary" variant="outlined" placeholder ="Enter City Name"
                        value={this.state.address.city} onChange={this.onCityChange} />
                        </FormControl>
                        </Grid>
                    <br />
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="State" color="primary" variant="outlined" placeholder="Enter State Name"
                        value={this.state.address.state} onChange={this.onStateChange} />
                        </FormControl>
                        </Grid>
                    <br />
                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                         required id="standard-textarea" label="pinCode" type="number" color="primary" variant="outlined" placeholder="Enter PinCode "
                        value={this.state.address.pinCode} onChange={event => this.handleInputChange(event, 'pinCode')} />
                        </FormControl>
                        {this.displayValidationErrors('pinCode')}
                        </Grid>
                    <br />
                    <p> 
                         </p>
    
                    {this.state.error && <b className="m-1 text-danger">{this.state.error}</b>}
                    <div style={{display:"inline-flex"}}>
                    <Button  text="Update Customer" onClick={this.onSubmit}></Button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <Button text=" Cancel" onClick={this.onCancel}></Button>
                    <br/><br/><br/></div>
         


                </form>
                </div>
           
            </Container>
        )
    }

}
export default withRouter(CustomerUpdateForm);

const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    },
}));

const style = {
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    borderRadius: 3,
    border: 0,
    color: 'white',
    height: 48,
    padding: '0 30px',
    boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
    marginLeft: "10px",
};
const errorStyle = {
    color: 'red'
}