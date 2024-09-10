import React from 'react';
import { connect } from "react-redux";
import { addCustomer } from '../../actions/customer/CustomerAction';
import CustomerForm from "./CustomerForm";
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';


// /**
//  * Author : Monisha V
//  * Date   : 06-05-2021 
//  * Description : This is Customer Container
//  **/

const AddCustomer = (props) => (
    <div>
       
        
        <p>

        </p>
        <Box color="white" bgcolor="#05716c" p={1}> <h2>Add Customer</h2></Box>
        <Paper elevation={3} ></Paper>  
        <p>

        </p> 
        <CustomerForm 
             onSubmitCustomer={(state) => {
                 props.dispatch(addCustomer(state));
                 props.history.push('/customer');
             }} />
        
    </div>
);

export default connect()(AddCustomer);