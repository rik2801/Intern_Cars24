import React from 'react';
import { connect } from "react-redux";
import { addCar } from '../../actions/car/CarAction';
import CarForm from "./CarForm";
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';


// /**
// *Author: Shivam Dwivedi
// *Date  : 06-05-2021
// *Description:This is Car Container
// **/

const AddCar = (props) => (
    <div>
       
        
        <p>

        </p>
        <div style={{textAlign:"center"}}>
        <Box color="white" bgcolor="#05716c" p={1}> <h2>Add Car</h2></Box>
        <Paper elevation={3} ></Paper>  </div>
        <p>

        </p> 
        <CarForm 
             onSubmitCar={(state) => {
                 props.dispatch(addCar(state));
                 props.history.push('/car');
             }} />
        
    </div>
);

export default connect()(AddCar);