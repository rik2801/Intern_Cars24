import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import PaymentDashBoard from '../components/payment/PaymentDashBoard';
import AddPayment from '../components/payment/AddPayment';
import UpdatePayment from '../components/payment/UpdatePayment';
import Payment from '../components/payment/Payment';
import AddCustomer from '../components/customer/AddCustomer';
import DashBoard from '../components/customer/DashBoard';
import UpdateCustomer from '../components/customer/UpdateCustomer';
import Customer from '../components/customer/Customer';
import OrderDashBoard from '../components/order/OrderDashBoard';
import AddOrder from "../components/order/AddOrder";
import Order from '../components/order/Order';
import LoginDisplay from '../components/login/LoginDisplay'
import SigUp from '../components/login/SignUp';
import HeaderComponent from '../components/car/HeaderComponent';
import AddAppointment from '../components/Appointment/AddAppointment';
import UpdateAppointment from '../components/Appointment/UpdateAppointment';
import DeleteAppointment from '../components/Appointment/DeleteAppointment';
import GetAllAppointments from '../components/Appointment/AllAppointmentList';
import GetOpenAppointments from '../components/Appointment/AllOpenAppointment';
import CarDashBoard from '../components/cars/CarDashBoard';
import AddCar from '../components/cars/AddCar';
import Car from '../components/cars/car';
import NotFound from '../components/customer/NotFound';
import Home from '../components/design/Home'
import AppointmentRoot from '../components/Appointment/AppointmentRoot';


const HomeRouter = () => (
    <Router>
         <div>
             <div>
             <HeaderComponent/>
    
             </div>
             <div>
             <Switch>
                 <Route path="/" component={LoginDisplay} exact={true}/>
                 <Route path="/dashboard" component={Home}exact={true}/>
                 <Route path={`/payment`} component={PaymentDashBoard}exact={true}/>
                 <Route path={`/addPayment`} component={AddPayment} exact={true}/>
                 <Route path={`/updatePayment/:paymentId`} component={UpdatePayment} exact={true}/>
                 <Route path={`/getPaymentById/:paymentId`} component={Payment} exact={true}/>      
                 <Route path={`/customer`} component={DashBoard}exact={true}/>
                 <Route path={`/addCustomer`} component={AddCustomer} exact={true}/>
                 <Route path={`/updateCustomer`} component={UpdateCustomer} exact={true}/>
                 <Route path={`/getCustomer/:userId`} component={Customer} exact={true}/>   
                 <Route path={`/order`} component={OrderDashBoard} exact={true}/>
                 <Route path={`/addOrder`} component={AddOrder} exact={true}/>
                 <Route path={`/getOrderById/:orderId`} component={Order} exact={true}/> 
                 <Route path={`/sign-up`} component={SigUp} exact={true}/>
                 <Route path="/Appointment" component={AppointmentRoot} exact={true}/>
                 <Route path='/Appointment/AddAppointment' component={AddAppointment} exact={true}/>
                <Route path='/Appointment/UpdateAppointment' component={UpdateAppointment} exact={true}/>
                <Route path='/Appointment/DeleteAppointment' component={DeleteAppointment} exact={true}/>
                <Route path='/Appointment/AllAppointments' component={GetAllAppointments} exact={true}/>
                <Route path='/Appointment/OpenAppointments' component={GetOpenAppointments} exact={true}/>
                <Route path={`/car`} component={CarDashBoard} exact={true}/>
                 <Route path={`/addCar`} component={AddCar} exact={true}/>
                 <Route path={`/getCar/:carId`} component={Car} exact={true}/>
                 <Route  component={NotFound}/>   
            
             </Switch>
          
         </div>
     
         </div>
    </Router>
);

export default HomeRouter;