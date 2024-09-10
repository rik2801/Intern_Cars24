import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import DashBoard from '../../components/order/OrderDashBoard';
import AddOrder from "../../components/order/AddOrder";
import Order from '../../components/order/Order';

const OrderRouter = () => (
    <BrowserRouter>
         <div>
             <Switch>
                 <Route path={`/order`} component={DashBoard}/>
                 <Route path={`/addOrder`} component={AddOrder} />
                 <Route path={`/getOrderById/:orderId`} component={Order} />
             </Switch>
         </div>
    </BrowserRouter>
);

export default OrderRouter;