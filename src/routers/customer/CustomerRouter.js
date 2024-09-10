import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import AddCustomer from '../../components/customer/AddCustomer';
import DashBoard from '../../components/customer/DashBoard';
import UpdateCustomer from '../../components/customer/UpdateCustomer';
import Customer from '../../components/customer/Customer';



const CustomerRouter = () => (
    <BrowserRouter>
         <div>
             <Switch>
                 <Route path={`/customer`} component={DashBoard}/>
                 <Route path={`/addCustomer`} component={AddCustomer}/>
                 <Route path={`/updateCustomer`} component={UpdateCustomer} />
                 <Route path={`/getCustomer/:userId`} component={Customer} />   

             </Switch>
           
         </div>
    </BrowserRouter>
);

export default CustomerRouter;