import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import AddCar from '../../components/cars/AddCar';
import CarDashBoard from '../../components/cars/CarDashBoard';

import Car from '../../components/cars/car';



const CarRouter = () => (
    <BrowserRouter>
         <div>
             <Switch>
                 <Route path={`/car`} component={CarDashBoard}/>
                 <Route path={`/addCar`} component={AddCar}/>
                 {/* <Route path={`/updateCar`} component={UpdateCar} /> */}
                 <Route path={`/getCar/:carId`} component={Car} />   

             </Switch>
           
         </div>
    </BrowserRouter>
);

export default CarRouter;