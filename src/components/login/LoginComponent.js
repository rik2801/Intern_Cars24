import React from "react";
import axios from "axios";
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import { FormControl, TextField } from '@material-ui/core';

export default class Login extends React.Component{

    constructor(props){
        super(props);
        this.state={
                userId: "",
                userName: "",
                password: "",
                flag:false
        }
    }

    handleChange = event => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({...this.state,[nam]:val});
    }

    handleSubmit = (event) => {
        event.preventDefault();
        console.log(this.state);
        this.validateLogin(this.state);
    }
    goBack=()=>{
        if(this.state.flag==true){
        this.props.onButtonClick();}
        else{console.log("Login Unsuccessful")}
    }

    validateLogin = async (User) => {
        console.log("this is validateLogin()", User);
        let result = await axios.patch(`http://localhost:9082/api/cars24/validatelogin`, User)
        console.log("result data", result.data);
        console.log("result data", result);
        if(result.data === "Login Successful!"){
        alert("Login Successful! click back to Dashboard")
        this.setState({flag:true})
        }
             //this.props.history.push('/dashboard');
        else
        {
            alert("Login Failed! User Name and Password does not Match");
            //this.props.history.push('/');
        }
    }

    render() {
        return(
            <div>  
                <form onSubmit={(event)=>this.handleSubmit(event)} >
                        <div>
                            <Box color="primary.main" p={1}> <h2>Login :</h2></Box>
                        </div>
                        <br />
                        <FormControl fullWidth >
                            <TextField
                                required id="standard-number" label="User ID" placeholder="Enter User ID" type="number"
                                name="userId" onChange={event=>this.handleChange(event)} />
                        </FormControl>
                        <br />
                        <br />
                        <FormControl fullWidth >
                            <TextField
                                required id="standard-textarea" label="User Name" placeholder="Enter User Name"
                                name="userName" onChange={event=>this.handleChange(event)} />
                        </FormControl>
                        <br />
                        <br />
                        
                        <FormControl fullWidth >
                            <TextField
                            type="password"
                                required id="standard-textarea" label="Password" placeholder="Enter Pasword"
                                name="password" onChange={event=>this.handleChange(event)} />
                        </FormControl>

                        <br />
                        <br />
                        <br />
                        <br />
                        <Button type="submit" >Login</Button>

                    </form>
                    <Button onClick={this.goBack.bind(this)}>Back</Button>              
                     {/* <form onSubmit={(event)=>this.handleSubmit(event)}>
                    <div id="error">{this.state.error}</div>
                    <input type="number" name="userId" onChange={event=>this.handleChange(event)}/>
                    <input type="text" name="userName" onChange={event=>this.handleChange(event)}/>
                    <input type="password" name="password" onChange={event=>this.handleChange(event)}/>
                        <button type="submit">Login</button>  
                    OR
                    <Link to="/sign-up">
                        <button type="button">Sign UP</button>
                    </Link>            
                </form> */}
            </div>
        );
    };
}