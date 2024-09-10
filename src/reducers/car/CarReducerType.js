import { ActionType } from "../../actions/car/ActionType";

const initialState ={
    cars:[]
};

export const getCarsReducer = (state = initialState,{type,payload}) => {
    switch (type) {
        case ActionType.GET_CARS:
            return {...state,cars:payload}
            
    
        default:
            return state;
            
    }
};

export const getCarReducer = (state = {} ,{type,payload}) => {
    switch (type) {
        case ActionType.GET_CAR_ID:
            return {...state,...payload}
            

        case ActionType.REMOVE_GET_CAR_ID:
            return{}
            
    
        default:
            return state;
    }
}