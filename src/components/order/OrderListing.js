import React, { useEffect } from 'react'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux';
import { getOrders } from "../../actions/order/OrderActionType";
import { Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import { Link } from "react-router-dom";
import Button from '@material-ui/core/Button';


// /**
// *Author: Dhivya
// *Date:06-05-2021
// *Description:This is Order Container
// **/

const OrderListing = () => {
  const dispatch = useDispatch();
  const orders = useSelector((state) => state.allOrders.orders);

  const fetchOrders = async () => {
    const result = await axios.get('http://localhost:9082/api/cars24/getAllOrder').then((response)=>dispatch(getOrders(response.data))).catch((err) => { console.log("Error ", err); });
    //console.log(result);
    //dispatch(getOrders(result.data));
  };

  useEffect(() => {
    fetchOrders();
  }, );

  console.log("Orders :", orders);

  return (
    <div  style={{margin:"10px"}}>
      <Grid>
        <TableContainer component={Paper}>
          <Table border="2" bgcolor="#fffff1" class="table  table-bordered table-hover">
            <TableHead className="thead-dark">
              <TableRow>
                <StyledTableCell>Order Id</StyledTableCell>
                <StyledTableCell>Amount</StyledTableCell>
                <StyledTableCell>Billing Date</StyledTableCell>
                <StyledTableCell>User Id</StyledTableCell>
                <StyledTableCell>View</StyledTableCell>
                <StyledTableCell>Delete</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {
                orders.map((order) => {
                  const { orderId,amount,billingDate,customer} = order;
                  return (
                    <StyledTableRow key={orderId}>
                      <td align="center">{orderId}</td>
                      <td align="center">{amount}</td>
                      <td align="center">{billingDate}</td>
                      <td align="center">{customer.userId}</td>
                   
                     
                      <td align="center"><Link to={`/getOrderById/${orderId}`}><Button style={style}>View </Button></Link></td>
                      <td align="center"><Link to={`/getOrderById/${orderId}`}><Button Button style={style}>Delete </Button></Link></td>
                    </StyledTableRow>
                  )
                })
              }
            </TableBody>
          </Table>
        </TableContainer>
      </Grid>

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

export default OrderListing;