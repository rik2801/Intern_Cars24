import React, { useState , useEffect } from 'react';
import axios from 'axios'
import { connect, useDispatch } from 'react-redux';
import { useHistory, useParams } from 'react-router';
import {  getOrderById ,deleteOrderById} from "../../actions/order/OrderActionType";
import { Grid } from '@material-ui/core';
import {Link} from "react-router-dom"
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import Button from '../Buttons/Button'

// /**
// *Author: Dhivya
// *Date:06-05-2021
// *Description:This is Order Container
// **/

const Order = () => {
    const {orderId} = useParams();
    const dispatch = useDispatch();
    const history = useHistory();
    const [order,setOrder] = useState({
         amount: '',
         billingDate: '',
         customer: {
             userId: '',
             name: '',
             email: '',
             contactNo: '',
             dob:'' ,
             address: {
                 addressId:'' ,
                 doorNo:'' ,
                 street:'' ,
                 area:'' ,
                 city:'' ,
                 state:'' ,
                 pinCode: '',
             }
         }
    
     });
     

    useEffect(() => {
        loadOrder();
    },)

    const loadOrder = async () => 
    {
        const result=await axios.get(`http://localhost:9082/api/cars24/getOrder/${orderId}`).catch((err) => { console.log("Error ", err); });
        dispatch(getOrderById(result.data));
        setOrder(result.data);
    }
    const  deleteOrder = async (orderId) => {
        await axios.delete(`http://localhost:9082/api/cars24/removeOrder/${orderId}`).catch((err) => {console.log("Error" , err);});
       //dispatch(deleteOrderById(orderId));
       alert("Deleted Successfully");
       history.push('/order')
     }
     return (
        <div className={useStyles.root}>
        <Box color="white" bgcolor="#05716c" p={1}> <h2> Order Details</h2></Box>
        
        <Paper elevation={3} >
        <ul class="list-group-item">
            <li class="list-group-item list-group-item-secondary"> <h3>Order Id : {orderId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Amount : {order.amount}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Billing Date : {order.billingDate}</h3> </li>

            <li class="list-group-item list-group-item-secondary"><h3>User Id : {order.customer.userId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Name : {order.customer.name}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Email : {order.customer.email}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Contact No. : {order.customer.contactNo}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>DOB : {order.customer.dob}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Address Id : {order.customer.address.addressId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Door No. : {order.customer.address.doorNo}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Street : {order.customer.address.street}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Area : {order.customer.address.area}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>city : {order.customer.address.city}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>State : {order.customer.address.state}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>PinCode : {order.customer.address.pinCode}</h3> </li>
         
            
        </ul>
        </Paper>

        <Grid item xs={3}>
       
        
        </Grid>
        <div style={{textAlign:"center"}}>
        <div style={{display:"inline-flex"}}>
        <Button text="Delete" onClick={ () => deleteOrder(orderId)}> </Button>&nbsp;&nbsp;&nbsp;&nbsp;
        <Link to={`/order`}><Button text="Back To Home"> </Button ></Link><br/><br/><br/><br/>
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


export default connect()(Order)