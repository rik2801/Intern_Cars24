import React from 'react';
import Button from '../Buttons/Button'
import { FormControl, TextField } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Container from '@material-ui/core/Container';
import PaymentValidation from './PaymentValidation';
import { withRouter } from "react-router-dom";
import classes from '../design/AppointmentComponent.module.css'
import { Link } from "react-router-dom";

// /**
//  * Author: Avinash 
//  * Date:-06-05-2021 
//  * Description:This is Payment Container 
// **/

 class AddPaymentForm extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            type: "",
            status: "",

            card: {
                cardName: "",
                cardNumber: '',
                cardExpiry: "",
                cvv: '',
            }
        };
        this.validators = PaymentValidation;
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

    isFormValid = () => {
        let status = true;
        Object.keys(this.validators).forEach((field) => {
            if (!this.validators[field].valid) {
                status = false;
            }
        });
        return status;
    }

    handleInputChange(event, inputPropName) {
        const newState = Object.assign({}, this.state);
        newState.card[inputPropName] = event.target.value;
        this.setState(newState);
        this.updateValidators(inputPropName, event.target.value);
    }

    onTypeChange = (e) => {
        this.setState({ type: e.target.value });
    }

    onStatusChange = (e) => {
        this.setState({ status: e.target.value });
    }

    onCancel = () => {
        this.props.history.push('/payment');
    }

    onSubmit = (e) => 
    {
        e.preventDefault();
        console.log("Submitted");
        alert("Payment Added Successfully");
        console.log(this.state);
        this.props.onSubmitPayment
        (
            {
                type: this.state.type,
                status: "Success",
                cardName: this.state.card.cardName,
                cardNumber: this.state.card.cardNumber,
                cardExpiry: this.state.card.cardExpiry,
                cvv: this.state.card.cvv,
            }
            );
            
        }



    render() {
        return (
            <Container className={classes.AddAppointment_root}>
                <div className="bg_Pay_image">

                <form onSubmit={this.onSubmit} >
                    <div>
                    <h2 style={{ textDecoration: "underline" }}> PAYMENT DETAILS :</h2> <br/>
                    </div>
                
                  <FormControl fullWidth>
                  <FormControl fullWidth ><h3 style={{textAlign:"left"}}>Payment Type</h3>
                            <RadioGroup  name="Payment Type" value={this.state.type} onChange={this.onTypeChange}>
                                <FormControlLabel value="Credit" control={<Radio color="primary" required={true} />} label="Credit" />
                                <FormControlLabel value="Debit" control={<Radio color="primary" required={true} />} label="Debit" />
                            </RadioGroup></FormControl>
                           
                            
                        </FormControl>
                        <br />
                        <br />
                        
                        <br />
                        <div>
                         <h2 style={{ textDecoration: "underline" }}> CARD DETAILS :</h2><br/>
                        </div>
                        <FormControl fullWidth >
                            <TextField
                                required id="standard-textarea" color="secondary" 
                                variant="outlined" label="Card Name" placeholder="Enter Name On The Card" color="primary"
                                value={this.state.card.cardName} onChange={event => this.handleInputChange(event, 'cardName')} />
                        </FormControl>
                        {this.displayValidationErrors('cardName')}
                        <br />
                        <br />

                        <FormControl fullWidth>
                            <TextField
                                required id="standard-number" label="Card Number" type="number"
                                variant="outlined"  placeholder="Enter 16 digit Number" color="primary"
                                value={this.state.card.cardNumber} onChange={event => this.handleInputChange(event, 'cardNumber')}
                                 />
                        </FormControl>
                        {this.displayValidationErrors('cardNumber')}
                        <br />
                        <br />
                        <FormControl fullWidth>
                            <TextField
                                required id="date"
                                label="Card Expiry"
                                type="date"
                                placeholder="Enter Expiry Date"
                                color="primary"
                                variant="outlined"
                                defaultValue="2021-05-06"
                                className={useStyles.textField}
                                value={this.state.card.cardExpiry}
                                onChange={event => this.handleInputChange(event, 'cardExpiry')}
                                InputLabelProps={{
                                    shrink: true
                                }} />
                        </FormControl>
                        {this.displayValidationErrors('cardExpiry')}
                        <br />
                        <br />

                        <FormControl fullWidth>
                            <TextField
                                required id="standard-number" label="Cvv" type="number" placeholder="Enter Cvv" variant="outlined" color="primary"
                                value={this.state.card.cvv} onChange={event => this.handleInputChange(event, 'cvv')}
                             />
                        </FormControl >
                        {this.displayValidationErrors('cvv')}
                        <br />
                        <br />
                        
                        {this.state.error && <b className="m-1 text-danger">{this.state.error}</b>}
                        <div style={{display:"inline-flex"}}>
                    <Button  text="Add Payment & Card" onClick={this.onSubmit}></Button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <Link to={`/payment`}><Button text=" Cancel" onClick={this.onCancel}></Button></Link>
                    </div>
                    </form>
                </div>
            </Container>
        )
    }

}
export default withRouter(AddPaymentForm);

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



const errorStyle = {
    color: 'red'
};


