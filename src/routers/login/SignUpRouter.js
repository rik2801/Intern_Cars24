import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import SignUp from "../../components/login/SignUp";


const SignUpRouter = () => (
    <BrowserRouter>
         <div>
             <Switch>
                
                 <Route path={`/signup`} component={SignUp} />
                 
             </Switch>
         </div>
    </BrowserRouter>
);

export default SignUpRouter;