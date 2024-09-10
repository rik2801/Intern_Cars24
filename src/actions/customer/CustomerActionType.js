import {ActionType} from './ActionType'

export const getCustomers = (customers) =>{
    return {
        type: ActionType.GET_CUSTOMERS ,
        payload: customers
    }
}

export const getCustomerById = (customer) =>{
    return {
        type: ActionType.GET_CUSTOMER_ID ,
        payload: customer
    }
}

export const deleteCustomerById = () => {
    return {
        type: ActionType.DELETE_CUSTOMER_ID
    }
}