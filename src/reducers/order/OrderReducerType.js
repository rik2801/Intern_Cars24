
import { ActionType } from "../../actions/order/ActionType";

const initialState ={
    orders:[]
};

export const getOrdersReducer = (state = initialState,{type,payload}) => {
    switch (type) {
        case ActionType.GET_ORDERS:
            return {...state,orders:payload}
            
    
        default:
            return state;
            
    }
};

export const getOrderReducer = (state = {} ,{type,payload}) => {
    switch (type) {
        case ActionType.GET_ORDER:
            return {...state,...payload}
            
        default:
            return state;
    }
}

export const deleteOrderReducer = (state = {} , {type,payload}) => {
    switch (type){
        case ActionType.DELETE_ORDER:
            return {...state,orderId:payload}

        default :
            return state
    }
}



