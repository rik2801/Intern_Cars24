import {ActionType} from './ActionType'

export const getPayments = (payments) =>{
    return {
        type: ActionType.GET_PAYMENTS ,
        payload: payments
    }
}

export const getPaymentById = (payment) =>{
    return {
        type: ActionType.GET_PAYMENT_ID ,
        payload: payment
    }
}

export const deletePaymentById = (paymentId) => {
    return {
        type: ActionType.DELETE_PAYMENT_ID,
        payload: paymentId
    }
}