import React, { useEffect } from 'react'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux';
import { getPayments } from "../../actions/payment/PaymentActionType";
import { Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import { Link } from "react-router-dom";
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';

// /**
//  * Author: Avinash 
//  * Date:-06-05-2021 
//  * Description:This is Payment Container 
// **/

const PaymentListing = () => {
  const dispatch = useDispatch();
  const payments = useSelector((state) => state.allPayments.payments);
  console.log("hi")
  console.log(payments)

  const fetchPayments = async () => {
    const result = await axios.get('http://localhost:9082/api/cars24/getAllPayment').catch((err) => { console.log("Error ", err); });
    console.log(result);
    dispatch(getPayments(result.data));
  };

  useEffect(() => {
    fetchPayments();
  },);

  console.log("payments :", payments);

  return (
    
    <div className="">
      <Box mx="auto" bgcolor="background.paper" p={1}>
      <Grid >
        <TableContainer component={Paper}>
          <Table border="2" bgcolor="#fffff1" class="table  table-bordered table-hover">
            <TableHead className="thead-dark">
              <TableRow>
                <StyledTableCell align ="center">Payment Id</StyledTableCell>
                <StyledTableCell align ="center">Type</StyledTableCell>
                <StyledTableCell align ="center">Status</StyledTableCell>
                <StyledTableCell align ="center">Card Id</StyledTableCell>
                <StyledTableCell align ="center">View</StyledTableCell>
                <StyledTableCell align ="center">Update</StyledTableCell>
                <StyledTableCell align ="center">Delete</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {
                payments.map((payment) => {
                  const { paymentId, type, status, card } = payment;
                  return (
                    <StyledTableRow key={paymentId}>
                      <td align ="center">{paymentId}</td>
                      <td align ="center">{type}</td>
                      <td align ="center">{status}</td>
                      <td align ="center">{card.id}</td>
                      <td align ="center"><Link to={`/getPaymentById/${paymentId}`}><Button style={style} align ="center" color="info" variant="contained" className="btn btn-info">View </Button></Link></td>
                      <td align ="center"><Link to={`/updatePayment/${paymentId}`}><Button style={style} align ="center" color="primary" variant="contained" className="btn btn-info">Update </Button></Link></td>
                      <td align ="center"><Link to={`/getPaymentById/${paymentId}`}><Button style={style} align ="center" color="secondary" variant="contained" className="btn btn-secondary" >Delete </Button></Link> </td>
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

export default PaymentListing;