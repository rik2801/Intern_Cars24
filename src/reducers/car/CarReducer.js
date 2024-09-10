const carReducerDefaultState = [];

const carReducer =  (state = carReducerDefaultState, action) => {
    switch (action.type) {
        case 'ADD_CAR':
            return [
                ...state,
                action.car
            ];
            
        case 'GET_CARS':
            return [
                    ...state,
                    action.cars
                ];
        case 'GET_CAR':
            return [
                    ...state,
                    action.car
                ];
        default:
            return state;
    }
}

export default carReducer;