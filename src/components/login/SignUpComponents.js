import React, { Component } from "react";

export default class SignUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            role:""
        }
        };

        handleInputChange(event, inputPropName) {
            const newState = Object.assign({}, this.state);
            newState.card[inputPropName] = event.target.value;
            this.setState(newState);
            
        }
    
        onUsernameChange = (e) => {
            this.setState({ username: e.target.value });
        }
    
        onPasswordChange = (e) => {
            this.setState({ password: e.target.value });
        }
        onRoleChange = (e) => {
            this.setState({ role: e.target.value });
        }
    
        onSubmit = event => {

            console.log("Signed in");
            console.log(this.state);
            event.preventDefault();
            this.props.onSubmitUser(
                {
                    username: this.state.username,
                    password: this.state.password,
                    role: this.state.role,
                    
                }
    
            );
     
        }

    render() {
        return (
            <form onSubmit={event => this.onSubmit(event)}>
                <h3>Sign Up</h3>

                <div className="form-group">
                 
                </div>

                

                <div className="form-group">
                    <label>User Name</label>
                    <input type="text" className="form-control" placeholder="Enter User Name" 
                    onChange={this.onUsernameChange}/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter Password"
            onChange={this.onPasswordChange} />
                </div>

                <div className="form-group">
                    <label>Role</label>
                    <input type="text" className="form-control" placeholder="Enter your Role" 
                   onChange={this.onRoleChange}/>
                </div>

                <button type="submit" className="btn btn-primary btn-block">Sign Up</button>
            </form>
        );
    }
}