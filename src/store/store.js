import { createStore, applyMiddleware } from "redux";
import thunk from 'redux-thunk';
import rootReducer from "../reducers/RootReducer";

const store =  () => {
    return createStore(rootReducer , applyMiddleware(thunk));
}
export default store;
