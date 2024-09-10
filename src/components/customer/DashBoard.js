import {
     Grid
} from '@material-ui/core';
import { useHistory } from 'react-router-dom';
import React from 'react';
import CustomerListing from './CustomerListing';
import Box from '@material-ui/core/Box';


import Button from '../Buttons/Button'


// /**
//  * Author : Monisha V
//  * Date   : 06-05-2021 
//  * Description : This is Customer Container
//  **/


function DashBoard() {

    const history = useHistory();
    return (
        <div>
                 <Box color="danger.main" component="div" display="inline"  > <h3><br/>CUSTOMER DETAILS:</h3></Box> <br/>
                <Grid   >
                    <CustomerListing />
                </Grid>
                <Box color="primary.main"  component="div" display="inline"  > 
                    <div style={{textAlign:"center"}}>
                    <h3> <Button text="Add Customer"  onClick={() => history.push("/addCustomer")}></Button></h3>
        </div>
                </Box>
                <br/>
 
        </div>

    );
}




export default DashBoard;