import React from 'react';
import { connect } from "react-redux";
import { addUser } from '../../actions/login/SignUpAction';
import SignUpComponents from "./SignUpComponents";
import Box from '@material-ui/core/Box';
import { Paper } from '@material-ui/core';


const SignUp = (props) => (
    <div style={{ backgroundColor: '#3edbf0' }}>
    <div >
         <Box color="white" bgcolor="black" p={1}> <h2 >Sign Up</h2></Box>
         <Paper elevation={3} >
        <SignUpComponents
             onSubmitUser={(state) => {
                 props.dispatch(addUser(state));
                 alert("Added Successfully");
                 props.history.push('/');
             }} />
             </Paper>
        
    </div>
    </div>
);
export default connect()(SignUp);