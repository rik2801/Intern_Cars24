import React, { useEffect } from 'react'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux';
import { getCustomers } from "../../actions/customer/CustomerActionType";
import { Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import { Link } from "react-router-dom";
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';



// /**
//  * Author : Monisha V
//  * Date   : 06-05-2021 
//  * Description : This is Customer Container
//  **/

const CustomerListing = () => {
  const dispatch = useDispatch();
  const customers = useSelector((state) => state.allCustomers.customers);

  const fetchCustomers = async () => {
    const result = await axios.get('http://localhost:9082/api/cars24/getAllCustomers').catch((err) => { console.log("Error ", err); });
    dispatch(getCustomers(result.data))
  };


  useEffect(() => {
    fetchCustomers();
  }, []);

  console.log("Customers :", customers);

  return (
  
    <div className="">
      
      <Box mx="auto" bgcolor="background.paper" p={1}>
      <Grid>
        <TableContainer component={Paper}>
          <Table border="2" bgcolor="#fffff1" class="table  table-bordered table-hover">
            <TableHead className="thead-dark">
              <TableRow >
                <StyledTableCell>Customer Id</StyledTableCell>
                <StyledTableCell>NAME</StyledTableCell>
                <StyledTableCell>EMAIL ID</StyledTableCell>
                <StyledTableCell>CONTACT</StyledTableCell>
                <StyledTableCell>View</StyledTableCell>
                <StyledTableCell>Update</StyledTableCell>
                <StyledTableCell>Delete</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {
                customers.map((customer) => {
                  const { userId, name, email, contactNo } = customer;
                  return (
                    <StyledTableRow key={userId}>
                      <td align="center">{userId}</td>
                      <td align="center">{name}</td>
                      <td align="center">{email}</td>
                      <td align="center">{contactNo}</td>
                      <td align="center"><Link to={`/getCustomer/${userId}`}><Button style={style}>View </Button></Link></td>
                      <td align="center"><Link to={`/updateCustomer`}><Button Button style={style}> Update </Button></Link></td>
                      <td align="center"><Link to={`/getCustomer/${userId}`}><Button Button style={style}>Delete </Button></Link></td>
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
export default CustomerListing;

