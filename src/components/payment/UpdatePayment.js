import React from 'react';
import { updatePayment } from '../../actions/payment/PaymentAction';
import UpdatePaymentForm from './UpdatePaymentForm';
import { connect } from 'react-redux';
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

// /**
//  * Author: Avinash 
//  * Date:-06-05-2021 
//  * Description:This is Payment Container 
// **/

const UpdatePayment = (props) => (
    <div className={useStyles.root}>

        <Box color="white" bgcolor="#05716c" p={1}> <h2  >Update Payment</h2></Box>
        <Paper elevation={3} >
        
        <UpdatePaymentForm 
            payment= {props.payment}
            onSubmitPayment = {(payment) => {
                props.dispatch(updatePayment(props.match.params.paymentId,payment));
                alert("Updated Successfully");
                props.history.push('/payment');
            }}
        /> 
        </Paper>
    </div>
   
)

const mapStateToProps = (state,props) => {
    return state.paymentId === props.match.params.paymentId
    
};

const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
      flexWrap: 'wrap',
      '& > *': {
        margin: theme.spacing(1),
        width: theme.spacing(16),
        height: theme.spacing(16),
      },
    },
  }));

export default connect(mapStateToProps)(UpdatePayment);