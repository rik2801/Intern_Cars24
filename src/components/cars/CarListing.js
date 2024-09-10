import React, { useEffect } from 'react'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux';
import { getCars } from "../../actions/car/CarActionType";
import { Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import { Link } from "react-router-dom";
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';


// /**
// *Author: Shivam Dwivedi
// *Date  : 06-05-2021
// *Description:This is Car Container
// **/

const CarListing = () => {
  const dispatch = useDispatch();
  const cars = useSelector((state) => state.allCars.cars);
 

  const fetchCars = async () => {
    const result = await axios.get('http://localhost:9082/api/cars24/getAllCars').catch((err) => { console.log("Error ", err); });
    console.log(result)
    console.log(result.data)
    dispatch(getCars(result.data))
  };


  useEffect(() => {
    fetchCars();
  }, []);

  console.log("Cars :", cars);

  return (
  
    <div className="">
      
      <Box mx="auto" bgcolor="background.paper" p={1}>
      <Grid>
        <TableContainer component={Paper}>
          <Table border="2" bgcolor="#fffff1" class="table  table-bordered table-hover">
            <TableHead className="thead-dark">
              <TableRow >
                <StyledTableCell>carId</StyledTableCell>
                <StyledTableCell>brand</StyledTableCell>
                <StyledTableCell>model</StyledTableCell>
                <StyledTableCell>variant</StyledTableCell>
                <StyledTableCell>registrationYear</StyledTableCell>
                 <StyledTableCell>registrationState</StyledTableCell>
                 {/* <StyledTableCell>userId</StyledTableCell> */}
                <StyledTableCell>View</StyledTableCell>
                {/* <StyledTableCell>Update</StyledTableCell>
                <StyledTableCell>Delete</StyledTableCell> */}
              </TableRow>
            </TableHead>
            <TableBody>
              {
                cars.map((car) => {
                  const { carId, brand, model, variant, registrationYear, registrationState,customer } = car;
                  return (
                    <StyledTableRow key={carId}>
                      <td align="center">{carId}</td>
                      <td align="center">{brand}</td>
                      <td align="center">{model}</td>
                      <td align="center">{variant}</td>
                       <td align="center">{registrationYear}</td>
                      <td align="center">{registrationState}</td>
                      {/* <td align="center">{customer.userId}</td> */}

                      <td align="center"><Link to={`/getCar/${carId}`}><Button style={style}>View </Button></Link></td>
                     
                      {/* <td align="center"><Link to={`/getCar/${carId}`}><Button Button style={style}>Delete </Button></Link></td> */}
                    </StyledTableRow>
                  )
                })
              }
            </TableBody>
          </Table>
        </TableContainer>
      </Grid>
      </Box>
    </div>
  );
}

const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
    textAlign:"center"
  
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);

const style = {
  background: 'linear-gradient(45deg, #05716c 30%, #1fbfb8 90%)',
  borderRadius: 3,
  border: 0,
  color: 'white',
  height: 48,
  padding: '0 50px',
  boxShadow: '0 3px 5px 2px rgba(0, 0, 0, .3)',
  marginLeft: "20px",
};
export default CarListing;