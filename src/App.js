import { Provider } from 'react-redux';
import store from './store/store'
import CustomerRouter from './routers/customer/CustomerRouter'
import PaymentRouter from './routers/payment/PaymentRouter';
import HomeRouter from './routers/HomeRouter'
import OrderRouter from './routers/order/OrderRouter'
import SignUpRouter from './routers/login/SignUpRouter'
import AppointmentRoot from './components/Appointment/AppointmentRoot'
import LoginComponent from './components/login/LoginComponent'
import CarRouter from './routers/car/CarRouter';


function App() {

    return (
        <div>
            <Provider store={store()}>
               {/* <CarRouter /> */}
               <HomeRouter/>
               


            </Provider>
        </div>

    );


}

export default App;