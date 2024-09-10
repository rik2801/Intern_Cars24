import React, { Component } from 'react'
import CarService from './services/CarServices';

class ViewCarComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            id:this.props.match.params.id,
            car:{}

        }
    }
    componentDidMount(){
        CarService.getCarById(this.state.id).then(res =>
            {
                this.setState({car:res.data});

            });

    }
    render() {
        return (
            <div>
                <div className="card col-md-6 offset-md-3">
                    <h3 className="text-center">View Car details</h3>
                         <div className="card-body">
                             <div className="row">
                                 <label>Brand:</label>
                                 <div>{this.state.car.Brand}</div>
                             </div>
                             <div className="row">
                                 <label>Model:</label>
                                 <div>{this.state.car.Model}</div>
                             </div>
                             <div className="row">
                                 <label>Variant:</label>
                                 <div>{this.state.car.Variant}</div>
                             </div>
                             <div className="row">
                                 <label>Registration year:</label>
                                 <div>{this.state.car.registrationYear}</div>
                             </div>
                             <div className="row">
                                 <label>Registration Year:</label>
                                 <div>{this.state.car.registrationState}</div>
                             </div>
                             </div>  
                </div>
                
            </div>
        )
    }
}
export default ViewCarComponent;