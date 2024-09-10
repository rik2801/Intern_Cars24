import React from 'react';
import { updateCustomer } from '../../actions/customer/CustomerAction';
import UpdateCustomerForm from './UpdateCustomerForm';
import { connect } from 'react-redux';
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';


// /**
//  * Author : Monisha V
//  * Date   : 06-05-2021 
//  * Description : This is Customer Container
//  **/

const UpdateCustomer = (props) => (
    <div className={useStyles.root}>
     
        <Box color="white" bgcolor="#05716c" p={1}> <h2>Update Customer</h2></Box>
        <Paper elevation={3} >
        
        <UpdateCustomerForm 
            customer= {props.customer}
            onSubmitCustomer = {(customer) => {
                props.dispatch(updateCustomer(customer));
                props.history.push('/customer');
            }}
        />
        </Paper>
    </div>
)

const mapStateToProps = (state,props) => {
    return state.userId === props.match.params.userId
    
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

export default connect(mapStateToProps)(UpdateCustomer);