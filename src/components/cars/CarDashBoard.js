import {
    Grid
} from '@material-ui/core';
import { useHistory } from 'react-router-dom';
import React from 'react';
import CarListing from './CarListing';
import Box from '@material-ui/core/Box';


import Button from '../Buttons/Button'

// /**
// *Author: Shivam Dwivedi
// *Date  : 06-05-2021
// *Description:This is Car Container
// **/


function CarDashBoard() {

   const history = useHistory();
   return (
       <div>
                <Box color="danger.main" component="div" display="inline"  > <h3><br/>CAR DETAILS:</h3></Box> <br/>
               <Grid   >
                   <CarListing />
               </Grid>
               <Box color="primary.main"  component="div" display="inline"  > 
                   <div style={{textAlign:"center"}}>
                   <h3> <Button text="Add Car"  onClick={() => history.push("/addCar")}></Button></h3>
       </div>
               </Box>
               <br/>
           
       </div>

   );
}




export default CarDashBoard;