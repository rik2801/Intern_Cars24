import React from 'react';
import { connect } from "react-redux";
import { addPayment } from '../../actions/payment/PaymentAction';
import AddPaymentForm from "./AddPaymentForm";
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';


// /**
//  * Author: Avinash 
//  * Date:-06-05-2021 
//  * Description:This is Payment Container 
// **/

const AddPayment = (props) => (
  <div>
       
        
  <p>

  </p>
  <Box color="white" bgcolor="#05716c" p={1}> <h2>Add Payment</h2></Box>
  <Paper elevation={3} ></Paper>  
  <p>

  </p> 
  <AddPaymentForm 
       onSubmitPayment={(state) => {
           props.dispatch(addPayment(state));
           props.history.push('/payment');
       }} />
  
</div>
);

export default connect()(AddPayment);