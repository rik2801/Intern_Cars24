import { ActionType } from "../../actions/payment/ActionType";

const initialState ={
    payments:[]
};

export const getPaymentsReducer = (state = initialState,{type,payload}) => {
    switch (type) {
        case ActionType.GET_PAYMENTS:
            return {...state,payments:payload}
            
    
        default:
            return state;
            
    }
};

export const getPaymentReducer = (state = {} ,{type,payload}) => {
    switch (type) {
        case ActionType.GET_PAYMENT_ID:
            return {...state,...payload}
            
        default:
            return state;
    }
}

export const deletePaymentReducer = (state = {} , {type,payload}) => {
    switch (type){
        case ActionType.DELETE_PAYMENT_ID:
            return {...state,paymentId:payload}

        default :
            return state
    }
}