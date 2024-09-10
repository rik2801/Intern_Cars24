import React from 'react';
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './Login.css';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Login from "./LoginComponent";

function LoginDisplay(props) {



  return (
    <div className="App">
      <nav className="navbar navbar-expand-lg navbar-light fixed-top">
        <div className="container">
          <Link className="navbar-brand" to={"/sign-in"}>Cars24</Link>
          <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link className="nav-link" to={"/sign-up"}>Sign up</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="auth-wrapper">
        <div className="auth-inner">

        <Login onButtonClick={()=>{
          
          props.history.push("/dashboard")
      }}  />
        </div>
      </div>
    </div>
  );
}

export default LoginDisplay;