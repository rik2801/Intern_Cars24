import { ActionType } from "../../actions/customer/ActionType";

const initialState ={
    customers:[]
};

export const getCustomersReducer = (state = initialState,{type,payload}) => {
    switch (type) {
        case ActionType.GET_CUSTOMERS:
            return {...state,customers:payload}
            
    
        default:
            return state;
            
    }
};

export const getCustomerReducer = (state = {} ,{type,payload}) => {
    switch (type) {
        case ActionType.GET_CUSTOMER_ID:
            return {...state,...payload}
            

        case ActionType.REMOVE_GET_CUSTOMER_ID:
            return{}
            
    
        default:
            return state;
    }
}