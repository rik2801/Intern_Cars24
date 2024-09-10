import React from 'react';
import Button from '../Buttons/Button';
import { FormControl, TextField } from '@material-ui/core';
import Container from '@material-ui/core/Container';
import Grid from "@material-ui/core/Grid";
import { makeStyles } from '@material-ui/core/styles';
import { Link } from "react-router-dom";


// /**
// *Author: Shivam Dwivedi
// *Date  : 06-05-2021
// *Description:This is Car Container
// **/


export default class CarForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
    
                brand: "",
                model:  "",
                variant: "",
                registrationYear: "",
                registrationState: "",
                customer: {
                    userId:  "",
                }
                   
                         

        }
    }

onBrandChange = (e) => {
        this.setState({ brand: e.target.value });
    }

    onModelChange = (e) => {
        this.setState({ model: e.target.value });
    }

onVariantChange = (e) => {
        this.setState({variant: e.target.value });
    }

onRegistrationYearChange = (e) => {
        this.setState({  registrationYear: e.target.value });
    }

onRegistrationStateChange = (e) => {
        this.setState({  registrationState: e.target.value });
    }
    // onUserIdChange = (e) => {
    //     this.setState(state =>({customer: {...state.customer,userId:e.target.value}}));
    // }
    


    onSubmit = event => {

        console.log("Submitted");
        alert(this.state);
        console.log(this.state);
        event.preventDefault();
        this.props.onSubmitCar(
            {
                 brand: this.state.brand,
                model: this.state.model,
                variant: this.state.variant,
                registrationYear: this.state. registrationYear,
                registrationState: this.state. registrationState,                
                // userId: this.state.customer.userId,
               
               
            }

        );
 
    }


    render() {
        return (
            <div >
            <Container >
            <div className="bg_Car_image">
            <form onSubmit={this.onSubmit}  >
                        <div>
                            <h2 style={{ textDecoration: "underline" }}> CAR DETAILS :</h2>
                       </div>
                       <br />
                <Grid item spacing={3}>
                <FormControl fullWidth>  
                <TextField
                        
                         label="Brand" placeholder="Brand Name" color ="secondary" variant="outlined"
                        value={this.state.brand} onChange={this.onBrandChange}
                         />                         
                        </FormControl >
                        </Grid>
                    <br />

                    <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                       label="Model" placeholder="Model Name" color ="secondary" variant="outlined"
                        value={this.state.model} onChange={this.onModelChange}/>
                        </FormControl >
                        </Grid>
                    <br />

                                       <Grid item spacing={3}>
                    <FormControl fullWidth>
                    <TextField
                      label="Variant" placeholder="Variant Name" color ="secondary" variant="outlined"
                        value={this.state.variant} onChange={this.onVariantChange}      />                
  </FormControl >
                        </Grid>
                    <br />

<Grid>
         <FormControl fullWidth>
                    <TextField
                       label="RegistartionYear" placeholder="Registration Year" color ="secondary"                               variant="outlined"
                        value={this.state.registrationYear} onChange={this.onRegistrationYearChange}/>                
  </FormControl >
                        </Grid>
                    <br />
<Grid>
      <FormControl fullWidth>
                    <TextField
                       label="RegistartionState" placeholder="Registartion State" color ="secondary" variant="outlined"
                        value={this.state.registrationState} onChange={this.onRegistrationStateChange}/>                
  </FormControl >
                        </Grid>
                    <br />
                    {/* <Grid item spacing={3}>
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
                        </Grid> */}





                    
                    <br />

                        <br />
                        
                        {this.state.error && <b className="m-1 text-danger">{this.state.error}</b>}

                       
                  {  /* <Link to={`/order`}><Button style={style} > Cancel</Button></Link> */}
                <div style={{textAlign:"center"}}>  <div style={{display:"inline-flex"}}>
                  <Button  text="Add Car" onClick={this.onSubmit}></Button>&nbsp;&nbsp;&nbsp;&nbsp;
                    <Link to={`/car`}><Button text=" Cancel" ></Button></Link>
            </div></div>
                    </form>
               </div>
            </Container>
            </div>
        )
    }

}

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
    background:'linear-gradient(45deg,#EC407A 30%, #F06292 90%)',
    borderRadius: 3,
    border: 0,
    color: 'black',
    height: 48,
    padding: '0 30px',
    boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
    marginLeft: "300px",
};
const errorStyle = {
    color: 'red'
};