import 'bootstrap/dist/css/bootstrap.min.css';

const orderReducerDefaultState = [];

const orderReducer =  (state = orderReducerDefaultState, action) => {
    switch (action.type) {
        case 'ADD_ORDER':
            return [
                ...state,
                action.order
            ];
            
           
            default:
                return state;
        }
    }
    
    export default orderReducer;