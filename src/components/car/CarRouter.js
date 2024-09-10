import React from 'react';

import HeaderComponent from './HeaderComponent';
import ListCarComponent from './ListCarComponent';
import FooterComponent from './FooterComponent';
import './CarApp.css';
import {BrowserRouter as Router,Route,Switch}from 'react-router-dom'
import CreateCarComponent from './CreateCarComponent';

import ViewCarComponent from './ViewCarComponent';
function CarRouter() {
  return (
    <div>
    <Router>
    
         <HeaderComponent/>
           <div className="container">
             <Switch> 
              <Route path="/" exact component ={ListCarComponent}></Route>
              <Route path="/car" component ={ListCarComponent}></Route>
              <Route path="/add-car/:id" component ={CreateCarComponent}></Route>
              <Route path="/view-car/:id" component ={ViewCarComponent}></Route>
            
             {/* <Route path="/update-car/:id" component ={UpdateCarComponent}></Route>*/}
             </Switch>
           </div>
           <FooterComponent/>
             
      
        
          </Router>
          </div>
          
        
  );
}


export default CarRouter;
