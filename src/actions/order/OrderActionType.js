import {ActionType} from './ActionType'

export const getOrders = (orders) =>{
    return {
        type: ActionType.GET_ORDERS ,
        payload: orders
    }
}

export const getOrderById = (order) =>{
    return {
        type: ActionType.GET_ORDER,
        payload: order
    }
}

export const deleteOrderById = (orderId) => {
    return {
        type: ActionType.DELETE_ORDER,
        payload:orderId
        
    }
}