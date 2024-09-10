import {ActionType} from './ActionType'

export const getCars = (cars) =>{
    return {
        type: ActionType.GET_CARS ,
        payload: cars
    }
}

export const getCarById = (car) =>{
    return {
        type: ActionType.GET_CAR_ID ,
        payload: car
    }
}

export const deleteCarById = () => {
    return {
        type: ActionType.DELETE_CAR_ID
    }
}