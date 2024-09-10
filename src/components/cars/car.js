import React, { useState , useEffect } from 'react';
import axios from 'axios'
import { connect, useDispatch } from 'react-redux';
import { useHistory, useParams } from 'react-router';
import {  getCarById ,deleteCarById} from "../../actions/car/CarActionType";
import {  Grid } from '@material-ui/core';
import {Link} from "react-router-dom"
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import Button from '../Buttons/Button'


// /**
// *Author: Shivam Dwivedi
// *Date  : 06-05-2021
// *Description:This is Car Container
// **/


const Car = () => {
    const {carId} = useParams();
    const dispatch = useDispatch();
    const history = useHistory();
    const [car,setCar] = useState({
      
            brand: "",
            model: "",
            variant: "",
            registrationYear: "",
            registrationState: "",
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
        loadCar();
    },[])

    const loadCar = async () => 
    {
        const result=await axios.get(`http://localhost:9082/api/cars24/getCar/${carId}`).catch((err) => { console.log("Error ", err); });
        dispatch(getCarById(result.data));
        setCar(result.data);
    }
    const  deleteCar = async (carId) => {
        await axios.delete(`http://localhost:9082/api/cars24/removeCar/${carId}`).catch((err) => {console.log("Error" , err);});
       dispatch(deleteCarById(carId));
       alert("Deleted Successfully");
       history.push('/car')
     }

    return (
        <div className={useStyles.root}>
        
        <Box color="white" bgcolor="#05716c" p={1}> <h2> Car Details</h2></Box>
        
        <Paper elevation={3} >
           
        <ul class="list-group-item">
            <li  class="list-group-item list-group-item-secondary"> <h3><b>CAR ID :</b>{carId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>BRAND : {car.brand}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>MODEL : {car.model}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>VARIANT : {car.variant}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>REGISTRATION YEAR : {car.registrationYear}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>REGISTRATION STATE:  {car.registrationState}</h3></li>
            {/* <li class="list-group-item list-group-item-secondary"><h3>User Id : {car.customer.userId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Name : {car.customer.name}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Email : {car.customer.email}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Contact No. : {car.customer.contactNo}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>DOB : {car.customer.dob}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Address Id : {car.customer.address.addressId}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Door No. : {car.customer.address.doorNo}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Street : {car.customer.address.street}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>Area : {car.customer.address.area}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>city : {car.customer.address.city}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>State : {car.customer.address.state}</h3> </li>
            <li class="list-group-item list-group-item-secondary"><h3>PinCode : {car.customer.address.pinCode}</h3> </li> */}
            
        </ul>
        </Paper>
    
        <Grid container spacing={3}>
        {/* <Grid item xs={3}>
        <Button text="Delete" onClick={ () => deleteCar(carId)}> </Button>
        </Grid> */}
        <Grid item xs={3}>
        
        </Grid>
        </Grid>
      <div style={{textAlign:"center"}}><Link to={`/car`}><Button text="Back To Home"> </Button ></Link></div>
        <br/><br/>
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


export default connect()(Car);