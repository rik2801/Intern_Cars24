import axios from "../../axios/axios";


const _addCar = (car) => ({
    type: 'ADD_CAR',
    car
});

export const addCar = (carData = {
    brand : "",
    model: "",
    variant: "",
    registrationYear: "",
    registrationState: "",
    userId: '',
    
}) => {
    return (dispatch) => {
        const car = {
            brand: carData.brand,
            model: carData.model,
            variant: carData.variant,
            registrationYear: carData.registrationYear,
            registrationState: carData.registrationState,
            customer:
            {
            userId: carData.userId,
            }
        };
        console.log(car);
        const result =  axios.post('/addCar', car);
        dispatch(_addCar(result.data));
    };
};

const _removeCar = ({carId} = {}) => ({
    type: 'REMOVE_CaR',
    carId
});

export const removeCar = ({carId} = {}) => {
    return (dispatch) =>  {
        return axios.delete(`/removeCar/${carId}`).then(() => {
            dispatch(_removeCar({carId}));
        });
    };
};

const _updateCar = (updatedCar) => ({
    type:"UPDATE_CAR",
    updatedCar
});

export const updateCar = (updatedCar) => {
   
 {
    return (dispatch) => {
        const car = {
            brand: updatedCar.brand,
            model: updatedCar.model,
            variant: updatedCar.variant,
            registrationYear: updatedCar.registrationYear,
            registrationState: updatedCar.registrationState,
            customers:{
                userId :updatedCar.userId
            }
        };
        console.log(car);
        return axios.put(`/updateCar`,car).then(() => {
            dispatch(_updateCar(car));
        }).catch(error => {
            throw (error);
        });
    }
};
}
