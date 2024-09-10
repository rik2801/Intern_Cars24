import React, { useState , useEffect } from 'react';
import axios from 'axios'
import { connect, useDispatch } from 'react-redux';
import { useHistory, useParams } from 'react-router';
import {  getCustomerById ,deleteCustomerById} from "../../actions/customer/CustomerActionType";
import {Link} from "react-router-dom"
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import Button from '../Buttons/Button'



// /**
//  * Author : Monisha V
//  * Date   : 06-05-2021 
//  * Description : This is Customer Container
//  **/

const Customer = () => {
    const {userId} = useParams();
    const dispatch = useDispatch();
    const history = useHistory();
    const [customer,setCustomer] = useState({
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
    });

    useEffect(() => {
        loadCustomer();
    },[])

    const loadCustomer = async () => 
    {
        const result=await axios.get(`http://localhost:9082/api/cars24/getCustomer/${userId}`).catch((err) => { console.log("Error ", err); });
        dispatch(getCustomerById(result.data));
        setCustomer(result.data);
    }
    const  deleteCustomer = async (userId) => {
        await axios.delete(`http://localhost:9082/api/cars24/removeCustomer/${userId}`).then(()=>console.log("Deleted Successfully")).catch((err) => {console.log("Error" , err); alert("Cannot be deleted due to dependencies");});
       dispatch(deleteCustomerById(userId));
      
       history.push('/customer')
     }

    return (
        <div className={useStyles.root}>
        
        <Box color="white" bgcolor="#05716c" p={1}> <h2> Customer Details</h2></Box>
       
        <Paper elevation={3} >
           
        <ul class="list-group-item">
            <li  class="list-group-item list-group-item-secondary"> <h3><b>CUSTOMER ID :</b>{userId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>NAME : {customer.name}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>EMAIL ID : {customer.email}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>CONTACT NO : {customer.contactNo}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>DATE OF BIRTH : {customer.dob}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>DOOR NO:  {customer.address.doorNo}</h3></li>
            <li class="list-group-item list-group-item-secondary"><h3>STREET : {customer.address.street}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>AREA : {customer.address.area}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>CITY : {customer.address.city}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>STATE : {customer.address.state}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>PINCODE: {customer.address.pinCode}</h3> </li>
            
        </ul>
        </Paper>
    
        
        <div style={{textAlign:"center"}}>
            <div style={{display:"inline-flex"}}>
        <Button text="Delete" onClick={ () => deleteCustomer(userId)}> </Button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <Link to={`/customer`}><Button text="Back To Home"> </Button ></Link> 
        </div>
        <br/><br/>
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


export default connect()(Customer);