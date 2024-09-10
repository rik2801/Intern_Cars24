import React, { useState , useEffect } from 'react';
import axios from 'axios'
import { connect, useDispatch } from 'react-redux';
import { useHistory, useParams } from 'react-router';
import {  getPaymentById ,deletePaymentById} from "../../actions/payment/PaymentActionType";
import { Grid } from '@material-ui/core';
import {Link} from "react-router-dom"
import Box from '@material-ui/core/Box';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
import Button from '../Buttons/Button'

// /**
//  * Author: Avinash 
//  * Date:-06-05-2021 
//  * Description:This is Payment Container 
// **/

const Payment = () => {
    const {paymentId} = useParams();
    const dispatch = useDispatch();
    const history = useHistory();
    const [payment,setPayment] = useState({
        type:'',
        status:'',
        card:{
            id:'',
            cardName:'',
            cardNumber:'',
            cardExpiry:'',
            ccv:'',
        }
    });

    useEffect(() => {
        loadPayment();
    },)

    const loadPayment = async () => 
    {
        const result=await axios.get(`http://localhost:9082/api/cars24/getPaymentById/${paymentId}`).catch((err) => { console.log("Error ", err); });
        dispatch(getPaymentById(result.data));
        setPayment(result.data);
    }
    const  deletePayment = async (paymentId) => {
        await axios.delete(`http://localhost:9082/api/cars24/removePayment/${paymentId}`).catch((err) => {console.log("Error" , err);});
       dispatch(deletePaymentById(paymentId));
       alert("Deleted Successfully");
       history.push('/payment')
     }

    return (
        <div className={useStyles.root} >
           
         <Box color="white" bgcolor="#05716c" p={1}> <h2 >Payment Details</h2></Box>
         <Paper elevation={3} >
         <ul class="list-group-item">
            <li class="list-group-item list-group-item-secondary"> <h3>Payment Id : {paymentId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Payment Type : {payment.type}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Payment Status : {payment.status}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Card Id : {payment.card.id}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Card Name : {payment.card.cardName}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Card Number :  {payment.card.cardNumber}</h3></li>
            <li class="list-group-item list-group-item-secondary"><h3>Card Expiry : {payment.card.cardExpiry}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Card Cvv : {payment.card.cvv}</h3> </li>
        </ul>
        </Paper>
        <div style={{textAlign:"center"}}>
        <div style={{display:"inline-flex"}}>
        <Button text="Delete" onClick={ () => deletePayment(paymentId)}> </Button>&nbsp;&nbsp;&nbsp;&nbsp;
        <Link to={`/payment`}><Button text="Back To Home"> </Button ></Link><br/><br/><br/><br/>
        </div>
        </div>
        </div>
    )
}
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

export default connect()(Payment);